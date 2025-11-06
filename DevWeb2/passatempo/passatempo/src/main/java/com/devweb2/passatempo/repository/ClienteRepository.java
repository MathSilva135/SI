package com.devweb2.passatempo.repository;

import com.devweb2.passatempo.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Este repositório pode ser usado para buscar Clientes de qualquer tipo
 * (Socio ou Dependente).
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Método de busca customizado que o Spring Data JPA implementará para nós
    // Útil para buscar um cliente pelo número de inscrição, sem saber se é Sócio ou Dependente.
    Optional<Cliente> findByNumInscricao(String numInscricao);
}