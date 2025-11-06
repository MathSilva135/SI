package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Classe;
import com.devweb2.passatempo.dto.ClasseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-05T23:11:46-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ClasseMapperImpl implements ClasseMapper {

    @Override
    public ClasseDTO toDTO(Classe classe) {
        if ( classe == null ) {
            return null;
        }

        ClasseDTO classeDTO = new ClasseDTO();

        classeDTO.setId( classe.getId() );
        classeDTO.setNome( classe.getNome() );
        classeDTO.setValor( classe.getValor() );
        classeDTO.setPrazoDevolucao( classe.getPrazoDevolucao() );

        return classeDTO;
    }

    @Override
    public Classe toEntity(ClasseDTO classeDTO) {
        if ( classeDTO == null ) {
            return null;
        }

        Classe classe = new Classe();

        classe.setId( classeDTO.getId() );
        classe.setNome( classeDTO.getNome() );
        classe.setValor( classeDTO.getValor() );
        classe.setPrazoDevolucao( classeDTO.getPrazoDevolucao() );

        return classe;
    }
}
