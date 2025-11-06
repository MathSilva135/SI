package com.devweb2.passatempo.repository;

import com.devweb2.passatempo.domain.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório específico para a entidade Dependente.
 */
@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Long> {
}