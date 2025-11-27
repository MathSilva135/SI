package com.devweb2.passatempo.repository;

import com.devweb2.passatempo.domain.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

    // Verifica se existe alguma locação para este item que ainda NÃO foi devolvida (dtDevolucaoEfetiva é null)
    @Query("SELECT COUNT(l) > 0 FROM Locacao l WHERE l.item.id = :itemId AND l.dtDevolucaoEfetiva IS NULL")
    boolean isItemAlugado(@Param("itemId") Long itemId);

    // Verifica se o cliente tem locações em aberto onde a data prevista já passou
    @Query("SELECT l FROM Locacao l WHERE l.cliente.id = :clienteId AND l.dtDevolucaoEfetiva IS NULL AND l.dtDevolucaoPrevista < :dataAtual")
    List<Locacao> findLocacoesEmAtraso(@Param("clienteId") Long clienteId, @Param("dataAtual") LocalDate dataAtual);

    // Busca uma locação ABERTA (dtDevolucaoEfetiva null) pelo Número de Série do Item
    @Query("SELECT l FROM Locacao l WHERE l.item.numSerie = :numSerie AND l.dtDevolucaoEfetiva IS NULL")
    Optional<Locacao> findLocacaoAbertaPorItem(@Param("numSerie") Long numSerie);

}