package com.devweb2.passatempo.service;

import com.devweb2.passatempo.domain.Ator;
import com.devweb2.passatempo.domain.Classe;
import com.devweb2.passatempo.domain.Diretor;
import com.devweb2.passatempo.domain.Titulo;
import com.devweb2.passatempo.dto.TituloCreateDTO;
import com.devweb2.passatempo.dto.TituloResponseDTO;
import com.devweb2.passatempo.mapper.TituloMapper;
import com.devweb2.passatempo.repository.AtorRepository;
import com.devweb2.passatempo.repository.ClasseRepository;
import com.devweb2.passatempo.repository.DiretorRepository;
import com.devweb2.passatempo.repository.TituloRepository;
import com.devweb2.passatempo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TituloService {

    @Autowired
    private TituloRepository tituloRepository;
    @Autowired
    private ClasseRepository classeRepository; // Dependência para Classe
    @Autowired
    private DiretorRepository diretorRepository; // Dependência para Diretor
    @Autowired
    private AtorRepository atorRepository; // Dependência para Atores
    @Autowired
    private TituloMapper tituloMapper;

    @Transactional(readOnly = true)
    public List<TituloResponseDTO> findAll() {
        return tituloRepository.findAll().stream()
                .map(tituloMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TituloResponseDTO findById(Long id) {
        Titulo titulo = findByIdOrThrow(id);
        return tituloMapper.toResponseDTO(titulo);
    }

    @Transactional
    public TituloResponseDTO create(TituloCreateDTO createDTO) {
        // 1. Converte o DTO básico para a entidade
        Titulo titulo = tituloMapper.toEntity(createDTO);
        titulo.setId(null);

        // 2. Busca e associa os relacionamentos
        associarRelacionamentos(titulo, createDTO);

        // 3. Salva a entidade completa
        titulo = tituloRepository.save(titulo);

        // 4. Retorna o DTO de Resposta
        return tituloMapper.toResponseDTO(titulo);
    }

    @Transactional
    public TituloResponseDTO update(Long id, TituloCreateDTO createDTO) {
        // 1. Busca a entidade existente
        Titulo titulo = findByIdOrThrow(id);

        // 2. Atualiza os campos simples
        titulo.setNome(createDTO.getNome());
        titulo.setAno(createDTO.getAno());
        titulo.setSinopse(createDTO.getSinopse());
        titulo.setCategoria(createDTO.getCategoria());

        // 3. Busca e atualiza os relacionamentos
        associarRelacionamentos(titulo, createDTO);

        // 4. Salva a entidade atualizada
        titulo = tituloRepository.save(titulo);

        // 5. Retorna o DTO de Resposta
        return tituloMapper.toResponseDTO(titulo);
    }

    @Transactional
    public void delete(Long id) {
        Titulo titulo = findByIdOrThrow(id);
        tituloRepository.delete(titulo);
    }

    private Titulo findByIdOrThrow(Long id) {
        return tituloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Título não encontrado com o ID: " + id));
    }

    /**
     * Método auxiliar para buscar e associar Classe, Diretor e Atores.
     * Usado tanto no Create quanto no Update.
     */
    private void associarRelacionamentos(Titulo titulo, TituloCreateDTO dto) {
        // Busca Classe
        Classe classe = classeRepository.findById(dto.getClasseId())
                .orElseThrow(() -> new ResourceNotFoundException("Classe não encontrada com ID: " + dto.getClasseId()));

        // Busca Diretor
        Diretor diretor = diretorRepository.findById(dto.getDiretorId())
                .orElseThrow(() -> new ResourceNotFoundException("Diretor não encontrado com ID: " + dto.getDiretorId()));

        // Busca Atores
        Set<Ator> atores = new HashSet<>(atorRepository.findAllById(dto.getAtorIds()));
        if (atores.size() != dto.getAtorIds().size()) {
            // Lançar exceção se algum ator ID não foi encontrado (opcional, mas boa prática)
            throw new ResourceNotFoundException("Um ou mais Atores não encontrados.");
        }

        // Associa as entidades encontradas ao Título
        titulo.setClasse(classe);
        titulo.setDiretor(diretor);
        titulo.setAtores(atores);
    }
}