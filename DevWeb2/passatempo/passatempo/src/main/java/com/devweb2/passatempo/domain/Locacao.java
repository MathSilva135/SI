package com.devweb2.passatempo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locacao")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dtLocacao;

    private LocalDate dtDevolucaoPrevista;

    private LocalDate dtDevolucaoEfetiva; // Pode ser nulo (ainda não devolveu)

    private Double valorCobrado;

    private Double multaCobrada;

    // --- Relacionamentos ---

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item; // Assume que a classe Item existe

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}