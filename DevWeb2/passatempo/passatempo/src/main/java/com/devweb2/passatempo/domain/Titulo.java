package com.devweb2.passatempo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
@Entity
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int ano;
    private String sinopse;
    private ClasseDTO classeDTO;
    private DiretorDTO diretorDTO;
    private List <AtorDTO> atorDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public ClasseDTO getClasseDTO() {
        return classeDTO;
    }

    public void setClasseDTO(ClasseDTO classeDTO) {
        this.classeDTO = classeDTO;
    }

    public DiretorDTO getDiretorDTO() {
        return diretorDTO;
    }

    public void setDiretorDTO(DiretorDTO diretorDTO) {
        this.diretorDTO = diretorDTO;
    }

    public List<AtorDTO> getAtorDTO() {
        return atorDTO;
    }

    public void setAtorDTO(List<AtorDTO> atorDTO) {
        this.atorDTO = atorDTO;
    }
}
