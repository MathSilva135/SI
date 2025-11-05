package com.devweb2.passatempo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numSerie;
    private String dtAquisicao;
    private String tipoItem;
    private TituloDTO tituloDTO;

    public int getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDtAquisicao() {
        return dtAquisicao;
    }

    public void setDtAquisicao(String dtAquisicao) {
        this.dtAquisicao = dtAquisicao;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public TituloDTO getTituloDTO() {
        return tituloDTO;
    }

    public void setTituloDTO(TituloDTO tituloDTO) {
        this.tituloDTO = tituloDTO;
    }
}
