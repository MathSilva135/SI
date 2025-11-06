package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Dependente;
import com.devweb2.passatempo.dto.DependenteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DependenteMapper {

    // Mapeamento especial:
    // Pega dependente.getSocio().getId() e coloca em dependenteDTO.setSocioId()
    @Mapping(source = "socio.id", target = "socioId")
    DependenteDTO toDTO(Dependente dependente);

    // Mapeamento especial:
    // 1. Ignora o campo 'socio'.
    // A lógica de buscar o 'Socio' a partir do 'socioId' ficará no SERVICE.
    @Mapping(target = "socio", ignore = true)
    Dependente toEntity(DependenteDTO dependenteDTO);
}