package com.devweb2.passatempo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private Integer numInscricao;

    private LocalDate dtNascimento; // Mudei de String para LocalDate (melhor prática)
    private String sexo;
    private Boolean estahAtivo;

    @Column(unique = true)
    private String cpf;

    private String endereco;
    private String tel;

    // --- Relacionamentos ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socio_id")
    private Cliente socio;

    @OneToMany(
            mappedBy = "socio",
            fetch = FetchType.LAZY
    )
    private List<Cliente> dependentes = new ArrayList<>();
}