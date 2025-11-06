package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Item;
import com.devweb2.passatempo.dto.ItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    // Mapeamento especial:
    // 1. Pega item.getTitulo().getId() e coloca em itemDTO.setTituloId()
    @Mapping(source = "titulo.id", target = "tituloId")
    ItemDTO toDTO(Item item);

    // Mapeamento especial:
    // 1. Ignora o campo 'titulo' ao converter DTO -> Entidade.
    // Por quê? O DTO só tem o 'tituloId' (um Long). A lógica de
    // buscar o 'Titulo' no banco a partir desse ID ficará no SERVICE.
    @Mapping(target = "titulo", ignore = true)
    Item toEntity(ItemDTO itemDTO);
}