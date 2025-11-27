package com.devweb2.passatempo.mapper;

import com.devweb2.passatempo.domain.Cliente;
import com.devweb2.passatempo.dto.ClienteDTO;
import com.devweb2.passatempo.dto.ClienteResumoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    // --- De Entidade para DTO ---

    /**
     * Converte a entidade Cliente completa para o ClienteDTO.
     * O MapStruct é inteligente e verá que os campos 'socio' e 'dependentes'
     * precisam ser mapeados usando o método 'toResumoDTO' abaixo.
     */
    ClienteDTO toDTO(Cliente cliente);

    /**
     * Converte a entidade Cliente para o DTO de Resumo.
     */
    ClienteResumoDTO toResumoDTO(Cliente cliente);


    // --- De DTO para Entidade ---

    /**
     * Converte o ClienteDTO para a Entidade Cliente.
     * IGNORAMOS os campos de relacionamento, pois eles não devem
     * ser definidos diretamente por este DTO (especialmente
     * ao criar ou atualizar a entidade principal).
     */
    @Mapping(target = "socio", ignore = true)
    @Mapping(target = "dependentes", ignore = true)
    Cliente toEntity(ClienteDTO clienteDTO);
}