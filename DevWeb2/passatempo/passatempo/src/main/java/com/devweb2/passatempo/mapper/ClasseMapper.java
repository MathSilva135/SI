package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Classe;
import com.devweb2.passatempo.dto.ClasseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClasseMapper {

    ClasseDTO toDTO(Classe classe);

    Classe toEntity(ClasseDTO classeDTO);
}