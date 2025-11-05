package com.devweb2.passatempo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int numInscricao;
    private String dtNascimento;
    private String sexo;
    private boolean estahAtivo;
    private String cpf;
    private String endereco;
    private String telefone;
    private List <ClienteDTO> dependentes;
    private ClienteDto socio;

    // Construtores, Getters e Setters

    public Cliente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumInscricao() {
        return numInscricao;
    }

    public void setNumInscricao(int numInscricao) {
        this.numInscricao = numInscricao;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isEstahAtivo() {
        return estahAtivo;
    }

    public void setEstahAtivo(boolean estahAtivo) {
        this.estahAtivo = estahAtivo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ClienteDto getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDto clienteDTO) {
        this.clienteDTO = clienteDTO;
    }
}
