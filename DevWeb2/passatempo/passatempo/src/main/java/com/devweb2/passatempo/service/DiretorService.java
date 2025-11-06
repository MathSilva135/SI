package com.devweb2.passatempo.service;

import com.devweb2.passatempo.domain.Diretor;
import com.devweb2.passatempo.dto.DiretorDTO;
import com.devweb2.passatempo.mapper.DiretorMapper;
import com.devweb2.passatempo.repository.DiretorRepository;
import com.devweb2.passatempo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepository;

    @Autowired
    private DiretorMapper diretorMapper;

    @Transactional(readOnly = true)
    public List<DiretorDTO> findAll() {
        return diretorRepository.findAll().stream()
                .map(diretorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DiretorDTO findById(Long id) {
        Diretor diretor = findByIdOrThrow(id);
        return diretorMapper.toDTO(diretor);
    }

    @Transactional
    public DiretorDTO create(DiretorDTO diretorDTO) {
        Diretor diretor = diretorMapper.toEntity(diretorDTO);
        diretor.setId(null);
        diretor = diretorRepository.save(diretor);
        return diretorMapper.toDTO(diretor);
    }

    @Transactional
    public DiretorDTO update(Long id, DiretorDTO diretorDTO) {
        Diretor diretor = findByIdOrThrow(id);
        diretor.setNome(diretorDTO.getNome());
        diretor = diretorRepository.save(diretor);
        return diretorMapper.toDTO(diretor);
    }

    @Transactional
    public void delete(Long id) {
        Diretor diretor = findByIdOrThrow(id);
        diretorRepository.delete(diretor);
    }

    private Diretor findByIdOrThrow(Long id) {
        return diretorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diretor não encontrado com o ID: " + id));
    }
}