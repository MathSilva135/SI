package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Locacao;
import com.devweb2.passatempo.dto.LocacaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class, ItemMapper.class})
public interface LocacaoMapper {

    // Converte Entidade -> DTO
    LocacaoDTO toDTO(Locacao locacao);

    // Converte DTO -> Entidade
    // Ignoramos item e cliente na conversão direta para evitar criar novos objetos
    // ou inconsistências. O Service vai buscar os objetos reais no banco pelo ID.
    @Mapping(target = "item", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    Locacao toEntity(LocacaoDTO locacaoDTO);
}