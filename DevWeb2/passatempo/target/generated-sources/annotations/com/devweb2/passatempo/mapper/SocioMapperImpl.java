package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Socio;
import com.devweb2.passatempo.dto.SocioDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-05T23:11:46-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class SocioMapperImpl implements SocioMapper {

    @Override
    public SocioDTO toDTO(Socio socio) {
        if ( socio == null ) {
            return null;
        }

        SocioDTO socioDTO = new SocioDTO();

        socioDTO.setId( socio.getId() );
        socioDTO.setNumInscricao( socio.getNumInscricao() );
        socioDTO.setNome( socio.getNome() );
        socioDTO.setDtNascimento( socio.getDtNascimento() );
        socioDTO.setSexo( socio.getSexo() );
        socioDTO.setEstahAtivo( socio.getEstahAtivo() );
        socioDTO.setCpf( socio.getCpf() );
        socioDTO.setEndereco( socio.getEndereco() );
        socioDTO.setTel( socio.getTel() );

        return socioDTO;
    }

    @Override
    public Socio toEntity(SocioDTO socioDTO) {
        if ( socioDTO == null ) {
            return null;
        }

        Socio socio = new Socio();

        socio.setId( socioDTO.getId() );
        socio.setNumInscricao( socioDTO.getNumInscricao() );
        socio.setNome( socioDTO.getNome() );
        socio.setDtNascimento( socioDTO.getDtNascimento() );
        socio.setSexo( socioDTO.getSexo() );
        socio.setEstahAtivo( socioDTO.getEstahAtivo() );
        socio.setCpf( socioDTO.getCpf() );
        socio.setEndereco( socioDTO.getEndereco() );
        socio.setTel( socioDTO.getTel() );

        return socio;
    }
}
