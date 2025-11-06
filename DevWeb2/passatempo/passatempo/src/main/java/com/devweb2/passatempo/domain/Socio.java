package com.devweb2.passatempo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "socio")
@PrimaryKeyJoinColumn(name = "cliente_id") // Chave estrangeira para o pai
public class Socio extends Cliente {

    @Column(unique = true)
    private String cpf;
    private String endereco;
    private String tel;

    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Dependente> dependentes = new HashSet<>();
}