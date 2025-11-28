package com.devweb2.passatempo.service;

import com.devweb2.passatempo.domain.Cliente;
import com.devweb2.passatempo.domain.Item;
import com.devweb2.passatempo.domain.Locacao;
import com.devweb2.passatempo.dto.DevolucaoDTO;
import com.devweb2.passatempo.dto.LocacaoDTO;
import com.devweb2.passatempo.mapper.LocacaoMapper;
import com.devweb2.passatempo.repository.ClienteRepository;
import com.devweb2.passatempo.repository.ItemRepository;
import com.devweb2.passatempo.repository.LocacaoRepository;
import com.devweb2.passatempo.service.exceptions.DataIntegrityException; // Nossa exceção personalizada
import com.devweb2.passatempo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private LocacaoMapper locacaoMapper;

    @Transactional(readOnly = true)
    public List<LocacaoDTO> findAll() {
        return locacaoRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(locacaoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LocacaoDTO findById(Long id) {
        Locacao locacao = locacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locação não encontrada. ID: " + id));
        return locacaoMapper.toDTO(locacao);
    }

    // --- CREATE (Efetuar Nova Locação) ---
    @Transactional
    public LocacaoDTO create(LocacaoDTO dto) {
        // 1. Validar Existência de Cliente e Item
        Cliente cliente = clienteRepository.findById(dto.getCliente().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        Item item = itemRepository.findById(dto.getItem().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado"));

        // 2. Regra: Cliente em Débito? (Curso Alternativo)
        List<Locacao> atrasos = locacaoRepository.findLocacoesEmAtraso(cliente.getId(), LocalDate.now());
        if (!atrasos.isEmpty()) {
            throw new DataIntegrityException("O cliente possui pendências. Locações em atraso: " + atrasos.size());
        }

        // 3. Regra: Item Disponível? (Restrição de Integridade / Curso Alternativo)
        if (locacaoRepository.isItemAlugado(item.getId())) {
            throw new DataIntegrityException("Este item já está alugado e não está disponível no momento.");
        }

        // 4. Instanciar Locação e Definir Data Corrente
        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);
        locacao.setItem(item);
        locacao.setDtLocacao(LocalDate.now()); // Data corrente

        // 5. Calcular Valor e Data Prevista (Baseado na Classe do Título)
        // Assumindo: Item -> Titulo -> Classe
        Double valorBase = item.getTitulo().getClasse().getValor();
        Integer prazoDias = item.getTitulo().getClasse().getPrazoDevolucao();

        // Define os valores calculados
        locacao.setValorCobrado(valorBase);
        locacao.setDtDevolucaoPrevista(locacao.getDtLocacao().plusDays(prazoDias));

        // 6. Override (Caso o funcionário tenha alterado manualmente no DTO)
        if (dto.getValorCobrado() != null) {
            locacao.setValorCobrado(dto.getValorCobrado());
        }
        if (dto.getDtDevolucaoPrevista() != null) {
            locacao.setDtDevolucaoPrevista(dto.getDtDevolucaoPrevista());
        }

        // 7. Validar Datas (Restrição de Integridade)
        validarDatas(locacao);

        locacao = locacaoRepository.save(locacao);
        return locacaoMapper.toDTO(locacao);
    }

    // --- UPDATE (Alterar Dados de Locação) ---
    @Transactional
    public LocacaoDTO update(Long id, LocacaoDTO dto) {
        Locacao locacao = locacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locação não encontrada"));

        // O funcionário pode alterar valor ou data prevista
        if (dto.getDtDevolucaoPrevista() != null) locacao.setDtDevolucaoPrevista(dto.getDtDevolucaoPrevista());
        if (dto.getValorCobrado() != null) locacao.setValorCobrado(dto.getValorCobrado());

        // Se estiver registrando a devolução efetiva
        if (dto.getDtDevolucaoEfetiva() != null) {
            locacao.setDtDevolucaoEfetiva(dto.getDtDevolucaoEfetiva());
            locacao.setMultaCobrada(dto.getMultaCobrada());
        }

        // Validar Datas novamente (Restrição de Integridade)
        validarDatas(locacao);

        locacao = locacaoRepository.save(locacao);
        return locacaoMapper.toDTO(locacao);
    }

    // --- DELETE (Cancelar Locação) ---
    @Transactional
    public void delete(Long id) {
        Locacao locacao = locacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locação não encontrada"));

        // Regra: Apenas locações NÃO PAGAS podem ser canceladas.
        // Como não temos entidade Pagamento no exemplo, vamos assumir que
        // se já houve devolução efetiva ou se existe um flag de pago, não pode cancelar.
        // Adaptando para o seu caso de uso:
        if (isLocacaoPaga(locacao)) {
            throw new DataIntegrityException("Locação paga! Não é possível cancelar uma locação que já possui pagamento.");
        }

        locacaoRepository.delete(locacao);
    }

    // --- Métodos Auxiliares ---

    private void validarDatas(Locacao locacao) {
        // Restrição: A data de devolução prevista tem de ser maior do que a data de locação.
        if (locacao.getDtDevolucaoPrevista() != null &&
                !locacao.getDtDevolucaoPrevista().isAfter(locacao.getDtLocacao()) &&
        !locacao.getDtDevolucaoPrevista().isEqual(locacao.getDtLocacao())) {
            throw new DataIntegrityException("Data de devolução prevista deve ser posterior à data de locação.");
        }

        // Restrição: A data de devolução efetiva tem de ser maior ou igual à data de locação.
        if (locacao.getDtDevolucaoEfetiva() != null &&
                locacao.getDtDevolucaoEfetiva().isBefore(locacao.getDtLocacao())) {
            throw new DataIntegrityException("Data de devolução efetiva não pode ser anterior à data de locação.");
        }
    }

    private boolean isLocacaoPaga(Locacao locacao) {
        // Lógica simulada: Se já devolveu (tem data efetiva), consideramos que o fluxo financeiro ocorreu.
        // Ou você pode adicionar um campo Boolean pago na entidade Locacao.
        return locacao.getDtDevolucaoEfetiva() != null;
    }

    @Transactional
    public LocacaoDTO realizarDevolucao(DevolucaoDTO devolucaoDTO) {
        // 1. Identifica a locação correspondente pelo Número de Série
        Locacao locacao = locacaoRepository.findLocacaoAbertaPorItem(devolucaoDTO.getNumSerieItem())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi encontrada uma locação vigente para o item com número de série: " + devolucaoDTO.getNumSerieItem()));

        // 2. Define a data corrente (Data de devolução efetiva)
        LocalDate dataDevolucao = LocalDate.now();

        // 3. Restrição de Integridade: Data Devolução >= Data Locação
        if (dataDevolucao.isBefore(locacao.getDtLocacao())) {
            throw new DataIntegrityException("A data de devolução não pode ser anterior à data da locação.");
        }

        locacao.setDtDevolucaoEfetiva(dataDevolucao);
        if ((locacao.getDtDevolucaoEfetiva().isAfter(locacao.getDtDevolucaoPrevista())) && devolucaoDTO.getMultaCobrada() == null){
            throw new DataIntegrityException("Locação está atrasada, deve-se informar o valor da multa.");
        }
        // 4. Lógica de Multa e Recálculo do Valor Final
        // Verifica se veio valor de multa no DTO
        if (devolucaoDTO.getMultaCobrada() != null && devolucaoDTO.getMultaCobrada() > 0) {

            Double valorMulta = devolucaoDTO.getMultaCobrada();
            locacao.setMultaCobrada(valorMulta);

            // Recalcula o valor total (Valor Original da Locação + Multa Informada)
            Double valorOriginal = locacao.getValorCobrado(); // Este valor foi definido na criação da locação

            // Proteção caso o valor original esteja nulo (improvável, mas seguro)
            if (valorOriginal == null) valorOriginal = 0.0;

            locacao.setValorCobrado(valorOriginal + valorMulta);
        }

        // 5. Salva a devolução atualizada
        locacao = locacaoRepository.save(locacao);

        return locacaoMapper.toDTO(locacao);
    }

    // FindAll e FindById permanecem iguais aos anteriores...
}