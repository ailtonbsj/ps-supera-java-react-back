package br.com.banco.dtos;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaDTO {
    Long id;

    @NotNull
    @JsonFormat(pattern="dd/MM/yyyy")
    Date dataTransferencia;

    @NotNull
    String valor;

    @NotNull
    String tipo;

    String nomeOperadorTransacao;

    @NotNull
    Long conta;
}
