package com.devweb2.passatempo.service;

import com.devweb2.passatempo.domain.Socio;
import com.devweb2.passatempo.dto.SocioDTO;
import com.devweb2.passatempo.mapper.SocioMapper;
import com.devweb2.passatempo.repository.SocioRepository;
import com.devweb2.passatempo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocioService {

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private SocioMapper socioMapper;

    @Transactional(readOnly = true)
    public List<SocioDTO> findAll() {
        return socioRepository.findAll().stream()
                .map(socioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SocioDTO findById(Long id) {
        Socio socio = findByIdOrThrow(id);
        return socioMapper.toDTO(socio);
    }

    @Transactional
    public SocioDTO create(SocioDTO socioDTO) {
        Socio socio = socioMapper.toEntity(socioDTO);
        socio.setId(null); // Garante criação
        socio = socioRepository.save(socio);
        return socioMapper.toDTO(socio);
    }

    @Transactional
    public SocioDTO update(Long id, SocioDTO socioDTO) {
        Socio socio = findByIdOrThrow(id);

        // Atualiza todos os campos (da classe pai Cliente e da própria Socio)
        socio.setNome(socioDTO.getNome());
        socio.setNumInscricao(socioDTO.getNumInscricao());
        socio.setDtNascimento(socioDTO.getDtNascimento());
        socio.setSexo(socioDTO.getSexo());
        socio.setEstahAtivo(socioDTO.getEstahAtivo());
        socio.setCpf(socioDTO.getCpf());
        socio.setEndereco(socioDTO.getEndereco());
        socio.setTel(socioDTO.getTel());

        socio = socioRepository.save(socio);
        return socioMapper.toDTO(socio);
    }

    @Transactional
    public void delete(Long id) {
        Socio socio = findByIdOrThrow(id);
        socioRepository.delete(socio);
    }

    private Socio findByIdOrThrow(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sócio não encontrado com o ID: " + id));
    }
}