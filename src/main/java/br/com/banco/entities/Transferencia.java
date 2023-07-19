package br.com.banco.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Date dataTransferencia;

    @Column(nullable = false, precision = 20, scale = 2)
    BigDecimal valor;

    @Column(nullable = false)
    String tipo;

    String nomeOperadorTransacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "conta_id")
    Conta conta;
}
