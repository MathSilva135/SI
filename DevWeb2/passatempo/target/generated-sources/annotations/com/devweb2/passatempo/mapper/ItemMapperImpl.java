package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Item;
import com.devweb2.passatempo.domain.Titulo;
import com.devweb2.passatempo.dto.ItemDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T11:30:21-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemDTO toDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setTituloId( itemTituloId( item ) );
        itemDTO.setId( item.getId() );
        itemDTO.setNumSerie( item.getNumSerie() );
        itemDTO.setDtAquisicao( item.getDtAquisicao() );
        itemDTO.setTipoItem( item.getTipoItem() );

        return itemDTO;
    }

    @Override
    public Item toEntity(ItemDTO itemDTO) {
        if ( itemDTO == null ) {
            return null;
        }

        Item item = new Item();

        item.setId( itemDTO.getId() );
        item.setNumSerie( itemDTO.getNumSerie() );
        item.setDtAquisicao( itemDTO.getDtAquisicao() );
        item.setTipoItem( itemDTO.getTipoItem() );

        return item;
    }

    private Long itemTituloId(Item item) {
        if ( item == null ) {
            return null;
        }
        Titulo titulo = item.getTitulo();
        if ( titulo == null ) {
            return null;
        }
        Long id = titulo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
