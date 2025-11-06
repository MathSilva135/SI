package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Socio;
import com.devweb2.passatempo.dto.SocioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocioMapper {

    // O MapStruct é inteligente o suficiente para mapear
    // os campos da classe pai (Cliente) automaticamente.
    SocioDTO toDTO(Socio socio);

    Socio toEntity(SocioDTO socioDTO);
}