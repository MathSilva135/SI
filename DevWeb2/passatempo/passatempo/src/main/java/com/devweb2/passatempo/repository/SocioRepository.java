package com.devweb2.passatempo.repository;

import com.devweb2.passatempo.domain.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório específico para a entidade Socio.
 */
@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {

    // Exemplo de busca customizada que podemos adicionar
    Optional<Socio> findByCpf(String cpf);
}