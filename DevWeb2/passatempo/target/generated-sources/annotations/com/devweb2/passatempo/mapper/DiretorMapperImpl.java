package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Diretor;
import com.devweb2.passatempo.dto.DiretorDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-05T23:11:46-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class DiretorMapperImpl implements DiretorMapper {

    @Override
    public DiretorDTO toDTO(Diretor diretor) {
        if ( diretor == null ) {
            return null;
        }

        DiretorDTO diretorDTO = new DiretorDTO();

        diretorDTO.setId( diretor.getId() );
        diretorDTO.setNome( diretor.getNome() );

        return diretorDTO;
    }

    @Override
    public Diretor toEntity(DiretorDTO diretorDTO) {
        if ( diretorDTO == null ) {
            return null;
        }

        Diretor diretor = new Diretor();

        diretor.setId( diretorDTO.getId() );
        diretor.setNome( diretorDTO.getNome() );

        return diretor;
    }
}
