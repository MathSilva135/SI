package com.devweb2.passatempo.service;

import com.devweb2.passatempo.domain.Cliente;
import com.devweb2.passatempo.dto.ClienteDTO;
import com.devweb2.passatempo.dto.ClienteResumoDTO;
import com.devweb2.passatempo.mapper.ClienteMapper;
import com.devweb2.passatempo.repository.ClienteRepository;
import com.devweb2.passatempo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devweb2.passatempo.service.exceptions.DataIntegrityException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    /**
     * Busca todos os clientes e os converte para DTO.
     */
    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca um cliente pelo ID.
     * Lança ResourceNotFoundException se não encontrar.
     */
    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        Cliente cliente = findByIdOrThrow(id);
        return clienteMapper.toDTO(cliente);
    }

    /**
     * Cria um novo cliente a partir de um DTO.
     * Este método agora TENTA associar um 'socio' se
     * um ID de sócio for fornecido no DTO.
     */
    @Transactional
    public ClienteDTO create(ClienteDTO clienteDTO) {
        // 1. Converte campos simples (Mapper ignora 'socio' e 'dependentes')
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente.setId(null); // Garante que é uma criação

        // 2. Lógica para associar o 'socio' (NOVO)
        if (clienteDTO.getSocio() != null && clienteDTO.getSocio().getId() != null) {
            // Busca a entidade 'Socio' que já deve existir
            Long socioId = clienteDTO.getSocio().getId();
            Cliente socio = findByIdOrThrow(socioId);

            // Define a associação
            cliente.setSocio(socio);
        }

        // 3. Salva o novo cliente (com ou sem sócio)
        cliente = clienteRepository.save(cliente);

        // 4. Retorna o DTO do cliente recém-criado
        return clienteMapper.toDTO(cliente);
    }

    /**
     * Atualiza um cliente existente.
     * Este método também sincroniza os relacionamentos 'socio' e 'dependentes'
     * com base nos IDs fornecidos nos DTOs.
     */
    @Transactional
    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        // 1. Busca a entidade principal que será atualizada
        Cliente cliente = findByIdOrThrow(id);

        // 2. Atualiza os campos simples (primitivos)
        cliente.setNome(clienteDTO.getNome());
        cliente.setNumInscricao(clienteDTO.getNumInscricao());
        cliente.setDtNascimento(clienteDTO.getDtNascimento());
        cliente.setSexo(clienteDTO.getSexo());
        cliente.setEstahAtivo(clienteDTO.getEstahAtivo());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setTel(clienteDTO.getTel());

        // 3. Atualiza o relacionamento 'socio' (ManyToOne)
        atualizarSocio(cliente, clienteDTO.getSocio());

        // 4. Atualiza o relacionamento 'dependentes' (OneToMany)
        atualizarDependentes(cliente, clienteDTO.getDependentes());

        // 5. Salva a entidade principal atualizada (o cascade cuidará das refs)
        cliente = clienteRepository.save(cliente);

        // 6. Retorna o DTO com todos os dados atualizados
        return clienteMapper.toDTO(cliente);
    }

    /**
     * Deleta um cliente.
     */
    @Transactional
    public void delete(Long id) {
        Cliente cliente = findByIdOrThrow(id); // Verifica se existe
        try {

            clienteRepository.delete(cliente);
            clienteRepository.flush();

        } catch (DataIntegrityViolationException e) {
            if(cliente.getSocio() != null){
                throw new DataIntegrityException(
                        "Não é possível excluir o cliente com ID " + id + ". " +
                                "Ele está vinculado a uma ou mais locações.");
            }
            throw new DataIntegrityException(
                    "Não é possível excluir o cliente com ID " + id + ". " +
                            "Ele está vinculado a um ou mais dependentes."
            );
        }
    }


    /**
     * Método auxiliar para atualizar a referência do Sócio.
     */
    private void atualizarSocio(Cliente cliente, ClienteResumoDTO socioDTO) {
        if (socioDTO != null && socioDTO.getId() != null) {
            // Se o ID do sócio for o mesmo do cliente, lança exceção
            if (cliente.getId().equals(socioDTO.getId())) {
                throw new IllegalArgumentException("Um cliente não pode ser sócio de si mesmo.");
            }
            // Busca a entidade Sócio pelo ID do DTO
            Cliente socio = findByIdOrThrow(socioDTO.getId());
            cliente.setSocio(socio);
        } else {
            // Se o DTO veio nulo, remove a associação
            cliente.setSocio(null);
        }
    }

    /**
     * Método auxiliar para sincronizar a lista de Dependentes.
     * Compara a lista atual com a nova lista do DTO (baseado em IDs).
     */
    private void atualizarDependentes(Cliente clienteSocio, List<ClienteResumoDTO> dependentesDTO) {

        if (dependentesDTO == null) {
            return;
        }

        Set<Long> idsDto = dependentesDTO.stream()
                .map(ClienteResumoDTO::getId)
                .collect(Collectors.toSet());

        List<Cliente> dependentesAtuais = clienteSocio.getDependentes();

        Iterator<Cliente> iterator = dependentesAtuais.iterator();
        while (iterator.hasNext()) {
            Cliente dependenteAtual = iterator.next();
            if (!idsDto.contains(dependenteAtual.getId())) {
                dependenteAtual.setSocio(null);
                iterator.remove();
            }
        }

        Set<Long> idsAtuais = dependentesAtuais.stream()
                .map(Cliente::getId)
                .collect(Collectors.toSet());

        for (Long idDto : idsDto) {
            if (!idsAtuais.contains(idDto)) {
                Cliente novoDependente = findByIdOrThrow(idDto);

                if (clienteSocio.getId().equals(novoDependente.getId())) {
                    throw new IllegalArgumentException("Um cliente não pode ser dependente de si mesmo.");
                }

                novoDependente.setSocio(clienteSocio);
                dependentesAtuais.add(novoDependente);
            }
        }
    }

    /**
     * Método auxiliar privado para buscar um Cliente ou lançar exceção.
     */
    private Cliente findByIdOrThrow(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + id));
    }
}