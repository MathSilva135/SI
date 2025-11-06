package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Dependente;
import com.devweb2.passatempo.domain.Socio;
import com.devweb2.passatempo.dto.DependenteDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T11:30:21-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class DependenteMapperImpl implements DependenteMapper {

    @Override
    public DependenteDTO toDTO(Dependente dependente) {
        if ( dependente == null ) {
            return null;
        }

        DependenteDTO dependenteDTO = new DependenteDTO();

        dependenteDTO.setSocioId( dependenteSocioId( dependente ) );
        dependenteDTO.setId( dependente.getId() );
        dependenteDTO.setNumInscricao( dependente.getNumInscricao() );
        dependenteDTO.setNome( dependente.getNome() );
        dependenteDTO.setDtNascimento( dependente.getDtNascimento() );
        dependenteDTO.setSexo( dependente.getSexo() );
        dependenteDTO.setEstahAtivo( dependente.getEstahAtivo() );

        return dependenteDTO;
    }

    @Override
    public Dependente toEntity(DependenteDTO dependenteDTO) {
        if ( dependenteDTO == null ) {
            return null;
        }

        Dependente dependente = new Dependente();

        dependente.setId( dependenteDTO.getId() );
        dependente.setNumInscricao( dependenteDTO.getNumInscricao() );
        dependente.setNome( dependenteDTO.getNome() );
        dependente.setDtNascimento( dependenteDTO.getDtNascimento() );
        dependente.setSexo( dependenteDTO.getSexo() );
        dependente.setEstahAtivo( dependenteDTO.getEstahAtivo() );

        return dependente;
    }

    private Long dependenteSocioId(Dependente dependente) {
        if ( dependente == null ) {
            return null;
        }
        Socio socio = dependente.getSocio();
        if ( socio == null ) {
            return null;
        }
        Long id = socio.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
