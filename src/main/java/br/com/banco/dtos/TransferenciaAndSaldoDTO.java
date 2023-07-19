package br.com.banco.dtos;

import java.util.List;

import lombok.Data;

@Data
public class TransferenciaAndSaldoDTO {
    List<TransferenciaDTO> transferencias;
    String saldo;
    String saldoNoPeriodo;
}
