package br.com.banco.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import br.com.banco.dtos.TransferenciaDTO;
import br.com.banco.entities.Transferencia;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransferenciaMapper {
    @Mapping(source = "conta.idConta", target = "conta")
    TransferenciaDTO toDTO(Transferencia transferencia);
    
    List<TransferenciaDTO> toDTO(List<Transferencia> list);
    
    @Mapping(source = "conta", target = "conta.idConta")
    Transferencia toEntity(TransferenciaDTO transferenciaDTO);
}
