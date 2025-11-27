package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Ator;
import com.devweb2.passatempo.dto.AtorDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AtorMapper {

    // Converte Ator (Entidade) -> AtorDTO (DTO)
    AtorDTO toDTO(Ator ator);

    // Converte AtorDTO (DTO) -> Ator (Entidade)
    Ator toEntity(AtorDTO atorDTO);
}