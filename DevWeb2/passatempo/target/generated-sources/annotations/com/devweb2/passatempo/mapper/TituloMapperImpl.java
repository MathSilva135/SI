package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Ator;
import com.devweb2.passatempo.domain.Titulo;
import com.devweb2.passatempo.dto.AtorDTO;
import com.devweb2.passatempo.dto.TituloCreateDTO;
import com.devweb2.passatempo.dto.TituloResponseDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-05T23:11:46-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class TituloMapperImpl implements TituloMapper {

    @Autowired
    private ClasseMapper classeMapper;
    @Autowired
    private DiretorMapper diretorMapper;
    @Autowired
    private AtorMapper atorMapper;

    @Override
    public TituloResponseDTO toResponseDTO(Titulo titulo) {
        if ( titulo == null ) {
            return null;
        }

        TituloResponseDTO tituloResponseDTO = new TituloResponseDTO();

        tituloResponseDTO.setId( titulo.getId() );
        tituloResponseDTO.setNome( titulo.getNome() );
        tituloResponseDTO.setAno( titulo.getAno() );
        tituloResponseDTO.setSinopse( titulo.getSinopse() );
        tituloResponseDTO.setClasse( classeMapper.toDTO( titulo.getClasse() ) );
        tituloResponseDTO.setDiretor( diretorMapper.toDTO( titulo.getDiretor() ) );
        tituloResponseDTO.setAtores( atorSetToAtorDTOSet( titulo.getAtores() ) );

        return tituloResponseDTO;
    }

    @Override
    public Titulo toEntity(TituloCreateDTO createDTO) {
        if ( createDTO == null ) {
            return null;
        }

        Titulo titulo = new Titulo();

        titulo.setNome( createDTO.getNome() );
        titulo.setAno( createDTO.getAno() );
        titulo.setSinopse( createDTO.getSinopse() );
        titulo.setCategoria( createDTO.getCategoria() );

        return titulo;
    }

    protected Set<AtorDTO> atorSetToAtorDTOSet(Set<Ator> set) {
        if ( set == null ) {
            return null;
        }

        Set<AtorDTO> set1 = new LinkedHashSet<AtorDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Ator ator : set ) {
            set1.add( atorMapper.toDTO( ator ) );
        }

        return set1;
    }
}
