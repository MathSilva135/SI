package com.devweb2.passatempo.service;

import com.devweb2.passatempo.domain.Item;
import com.devweb2.passatempo.domain.Titulo;
import com.devweb2.passatempo.dto.ItemDTO;
import com.devweb2.passatempo.mapper.ItemMapper;
import com.devweb2.passatempo.repository.ItemRepository;
import com.devweb2.passatempo.repository.TituloRepository;
import com.devweb2.passatempo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TituloRepository tituloRepository; // Precisamos para buscar o Título

    @Autowired
    private ItemMapper itemMapper;

    @Transactional(readOnly = true)
    public List<ItemDTO> findAll() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ItemDTO findById(Long id) {
        Item item = findByIdOrThrow(id);
        return itemMapper.toDTO(item);
    }

    @Transactional
    public ItemDTO create(ItemDTO itemDTO) {
        // Lógica de relacionamento: Busca o Título
        Titulo titulo = tituloRepository.findById(itemDTO.getTituloId())
                .orElseThrow(() -> new ResourceNotFoundException("Título não encontrado com ID: " + itemDTO.getTituloId()));

        Item item = itemMapper.toEntity(itemDTO);
        item.setId(null);
        item.setTitulo(titulo); // Associa o Título encontrado

        item = itemRepository.save(item);
        return itemMapper.toDTO(item);
    }

    @Transactional
    public ItemDTO update(Long id, ItemDTO itemDTO) {
        Item item = findByIdOrThrow(id);

        // Busca o Título (pode ter sido alterado)
        Titulo titulo = tituloRepository.findById(itemDTO.getTituloId())
                .orElseThrow(() -> new ResourceNotFoundException("Título não encontrado com ID: " + itemDTO.getTituloId()));

        // Atualiza campos
        item.setNumSerie(itemDTO.getNumSerie());
        item.setDtAquisicao(itemDTO.getDtAquisicao());
        item.setTipoItem(itemDTO.getTipoItem());
        item.setTitulo(titulo); // Atualiza a associação

        item = itemRepository.save(item);
        return itemMapper.toDTO(item);
    }

    @Transactional
    public void delete(Long id) {
        Item item = findByIdOrThrow(id);
        itemRepository.delete(item);
    }

    private Item findByIdOrThrow(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado com o ID: " + id));
    }
}