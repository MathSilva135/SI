package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Titulo;
import com.devweb2.passatempo.dto.TituloCreateDTO;
import com.devweb2.passatempo.dto.TituloResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClasseMapper.class, DiretorMapper.class, AtorMapper.class})
public interface TituloMapper {

    // Converte Titulo (Entidade) -> TituloResponseDTO (DTO de Resposta)
    // 'uses = ...' diz ao MapStruct para usar os outros mappers
    // para converter os objetos aninhados (Classe, Diretor, Ator).
    TituloResponseDTO toResponseDTO(Titulo titulo);

    // Converte TituloCreateDTO (DTO de Criação) -> Titulo (Entidade)
    // Ignoramos todos os relacionamentos, pois eles vêm como IDs no DTO.
    // A lógica de buscar as entidades (Classe, Diretor, Atores)
    // a partir dos IDs ficará no SERVICE.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "classe", ignore = true)
    @Mapping(target = "diretor", ignore = true)
    @Mapping(target = "atores", ignore = true)
    @Mapping(target = "itens", ignore = true)
    Titulo toEntity(TituloCreateDTO createDTO);
}