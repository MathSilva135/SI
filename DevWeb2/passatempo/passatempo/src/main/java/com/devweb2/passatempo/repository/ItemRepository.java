package com.devweb2.passatempo.repository;

import com.devweb2.passatempo.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


    @Query("SELECT COUNT(i) FROM Item i " +
            "WHERE i.titulo.id = :tituloId " +
            "AND i.id NOT IN (" +
            "    SELECT l.item.id FROM Locacao l WHERE l.dtDevolucaoEfetiva IS NULL" +
            ")")
    Long contarItensDisponiveisPorTitulo(@Param("tituloId") Long tituloId);
}