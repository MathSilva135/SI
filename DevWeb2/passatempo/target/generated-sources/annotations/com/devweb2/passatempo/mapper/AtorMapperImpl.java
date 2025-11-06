package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Ator;
import com.devweb2.passatempo.dto.AtorDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T11:30:21-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class AtorMapperImpl implements AtorMapper {

    @Override
    public AtorDTO toDTO(Ator ator) {
        if ( ator == null ) {
            return null;
        }

        AtorDTO atorDTO = new AtorDTO();

        atorDTO.setId( ator.getId() );
        atorDTO.setNome( ator.getNome() );

        return atorDTO;
    }

    @Override
    public Ator toEntity(AtorDTO atorDTO) {
        if ( atorDTO == null ) {
            return null;
        }

        Ator ator = new Ator();

        ator.setId( atorDTO.getId() );
        ator.setNome( atorDTO.getNome() );

        return ator;
    }
}
