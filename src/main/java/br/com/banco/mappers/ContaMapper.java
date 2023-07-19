package br.com.banco.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.banco.dtos.ContaDTO;
import br.com.banco.entities.Conta;

@Mapper
public interface ContaMapper {
    ContaDTO toDTO(Conta conta);
    List<ContaDTO> toDTO(List<Conta> list);
    Conta toEntity(ContaDTO contaDTO);
}
