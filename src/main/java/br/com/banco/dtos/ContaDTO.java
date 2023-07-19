package br.com.banco.dtos;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ContaDTO {
    Long idConta;

    @NotNull
    String nomeResponsavel;
}
