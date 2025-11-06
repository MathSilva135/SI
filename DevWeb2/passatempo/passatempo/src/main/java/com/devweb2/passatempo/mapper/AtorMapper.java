package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Ator;
import com.devweb2.passatempo.dto.AtorDTO;
import org.mapstruct.Mapper;

// A anotação @Mapper diz ao MapStruct para processar esta interface.
// componentModel="spring" faz o MapStruct criar um Bean do Spring,
// assim podemos injetá-lo (@Autowired) em nossos Services.
@Mapper(componentModel = "spring")
public interface AtorMapper {

    // Converte Ator (Entidade) -> AtorDTO (DTO)
    AtorDTO toDTO(Ator ator);

    // Converte AtorDTO (DTO) -> Ator (Entidade)
    Ator toEntity(AtorDTO atorDTO);
}