package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Diretor;
import com.devweb2.passatempo.dto.DiretorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiretorMapper {

    DiretorDTO toDTO(Diretor diretor);

    Diretor toEntity(DiretorDTO diretorDTO);
}