package com.devweb2.passatempo.service;

import com.devweb2.passatempo.domain.Dependente;
import com.devweb2.passatempo.domain.Socio;
import com.devweb2.passatempo.dto.DependenteDTO;
import com.devweb2.passatempo.mapper.DependenteMapper;
import com.devweb2.passatempo.repository.DependenteRepository;
import com.devweb2.passatempo.repository.SocioRepository;
import com.devweb2.passatempo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DependenteService {

    @Autowired
    private DependenteRepository dependenteRepository;

    @Autowired
    private SocioRepository socioRepository; // Precisamos para buscar o Sócio

    @Autowired
    private DependenteMapper dependenteMapper;

    @Transactional(readOnly = true)
    public List<DependenteDTO> findAll() {
        return dependenteRepository.findAll().stream()
                .map(dependenteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DependenteDTO findById(Long id) {
        Dependente dependente = findByIdOrThrow(id);
        return dependenteMapper.toDTO(dependente);
    }

    @Transactional
    public DependenteDTO create(DependenteDTO dependenteDTO) {
        // Lógica de relacionamento: Busca o Sócio
        Socio socio = socioRepository.findById(dependenteDTO.getSocioId())
                .orElseThrow(() -> new ResourceNotFoundException("Sócio não encontrado com ID: " + dependenteDTO.getSocioId()));

        Dependente dependente = dependenteMapper.toEntity(dependenteDTO);
        dependente.setId(null);
        dependente.setSocio(socio); // Associa o Sócio encontrado

        dependente = dependenteRepository.save(dependente);
        return dependenteMapper.toDTO(dependente);
    }

    @Transactional
    public DependenteDTO update(Long id, DependenteDTO dependenteDTO) {
        Dependente dependente = findByIdOrThrow(id);

        // Busca o Sócio (pode ter sido alterado)
        Socio socio = socioRepository.findById(dependenteDTO.getSocioId())
                .orElseThrow(() -> new ResourceNotFoundException("Sócio não encontrado com ID: " + dependenteDTO.getSocioId()));

        // Atualiza campos
        dependente.setNome(dependenteDTO.getNome());
        dependente.setNumInscricao(dependenteDTO.getNumInscricao());
        dependente.setDtNascimento(dependenteDTO.getDtNascimento());
        dependente.setSexo(dependenteDTO.getSexo());
        dependente.setEstahAtivo(dependenteDTO.getEstahAtivo());
        dependente.setSocio(socio); // Atualiza a associação

        dependente = dependenteRepository.save(dependente);
        return dependenteMapper.toDTO(dependente);
    }

    @Transactional
    public void delete(Long id) {
        Dependente dependente = findByIdOrThrow(id);
        dependenteRepository.delete(dependente);
    }

    private Dependente findByIdOrThrow(Long id) {
        return dependenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dependente não encontrado com o ID: " + id));
    }
}