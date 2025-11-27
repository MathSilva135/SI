package com.devweb2.passatempo.service;

import com.devweb2.passatempo.domain.Classe;
import com.devweb2.passatempo.dto.ClasseDTO;
import com.devweb2.passatempo.mapper.ClasseMapper;
import com.devweb2.passatempo.repository.ClasseRepository;
import com.devweb2.passatempo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devweb2.passatempo.service.exceptions.DataIntegrityException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private ClasseMapper classeMapper;

    @Transactional(readOnly = true)
    public List<ClasseDTO> findAll() {
        return classeRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(classeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClasseDTO findById(Long id) {
        Classe classe = findByIdOrThrow(id);
        return classeMapper.toDTO(classe);
    }

    @Transactional
    public ClasseDTO create(ClasseDTO classeDTO) {
        Classe classe = classeMapper.toEntity(classeDTO);
        classe.setId(null);
        classe = classeRepository.save(classe);
        return classeMapper.toDTO(classe);
    }

    @Transactional
    public ClasseDTO update(Long id, ClasseDTO classeDTO) {
        Classe classe = findByIdOrThrow(id);

        // Atualiza os campos
        classe.setNome(classeDTO.getNome());
        classe.setValor(classeDTO.getValor());
        classe.setPrazoDevolucao(classeDTO.getPrazoDevolucao());

        classe = classeRepository.save(classe);
        return classeMapper.toDTO(classe);
    }

    @Transactional
    public void delete(Long id) {
        Classe classe = findByIdOrThrow(id);
        try {

            classeRepository.delete(classe);
            classeRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(
                    "Não é possível excluir a classe com ID " + id + ". " +
                            "Ela está referênciada em um ou mais títulos."
            );
        }
    }

    private Classe findByIdOrThrow(Long id) {
        return classeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classe não encontrada com o ID: " + id));
    }
}