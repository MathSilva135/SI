package com.devweb2.passatempo.service;

import com.devweb2.passatempo.domain.Ator;
import com.devweb2.passatempo.dto.AtorDTO;
import com.devweb2.passatempo.mapper.AtorMapper;
import com.devweb2.passatempo.repository.AtorRepository;
import com.devweb2.passatempo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devweb2.passatempo.service.exceptions.DataIntegrityException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.stream.Collectors;

@Service // Marca a classe como um Serviço do Spring
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    @Autowired
    private AtorMapper atorMapper; // Injetamos nosso "tradutor"

    /**
     * Busca todos os atores e os converte para DTO.
     */
    @Transactional(readOnly = true) // Boa prática para operações de leitura
    public List<AtorDTO> findAll() {
        return atorRepository.findAll().stream()
                .map(atorMapper::toDTO) // Converte cada Ator para AtorDTO
                .collect(Collectors.toList());
    }

    /**
     * Busca um ator pelo ID.
     * Lança ResourceNotFoundException se não encontrar.
     */
    @Transactional(readOnly = true)
    public AtorDTO findById(Long id) {
        Ator ator = findByIdOrThrow(id); // Usa nosso método auxiliar
        return atorMapper.toDTO(ator);
    }

    /**
     * Cria um novo ator a partir de um DTO.
     */
    @Transactional // Operação de escrita
    public AtorDTO create(AtorDTO atorDTO) {
        Ator ator = atorMapper.toEntity(atorDTO);
        ator.setId(null); // Garante que é uma criação (ID nulo)
        ator = atorRepository.save(ator);
        return atorMapper.toDTO(ator);
    }

    /**
     * Atualiza um ator existente.
     */
    @Transactional
    public AtorDTO update(Long id, AtorDTO atorDTO) {
        Ator ator = findByIdOrThrow(id); // Verifica se o ator existe

        // Atualiza os dados da entidade existente
        ator.setNome(atorDTO.getNome());

        ator = atorRepository.save(ator);
        return atorMapper.toDTO(ator);
    }

    /**
     * Deleta um ator.
     */
    @Transactional
    public void delete(Long id) {
        Ator ator = findByIdOrThrow(id);
        try {

            atorRepository.delete(ator);
            atorRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(
                    "Não é possível excluir o ator com ID " + id + ". " +
                            "Ele está referênciado em um ou mais títulos."
            );
        }
    }
    /**
     * Método auxiliar privado para buscar um Ator ou lançar exceção.
     * Isso evita repetição de código (DRY - Don't Repeat Yourself).
     */
    private Ator findByIdOrThrow(Long id) {
        return atorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ator não encontrado com o ID: " + id));
    }
}