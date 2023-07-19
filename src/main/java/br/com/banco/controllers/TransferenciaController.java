package br.com.banco.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dtos.TransferenciaAndSaldoDTO;
import br.com.banco.dtos.TransferenciaDTO;
import br.com.banco.entities.Transferencia;
import br.com.banco.mappers.TransferenciaMapper;
import br.com.banco.services.TransferenciaService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/transferencia")
public class TransferenciaController {
    @Autowired
    TransferenciaService service;

    TransferenciaMapper mapper = Mappers.getMapper(TransferenciaMapper.class);

    @GetMapping
    public TransferenciaAndSaldoDTO filter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<Date> inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<Date> fim,
            @RequestParam Optional<String> operador) {
        List<Transferencia> list = service.filter(inicio, fim, operador);
        TransferenciaAndSaldoDTO ts = new TransferenciaAndSaldoDTO();
        ts.setTransferencias(mapper.toDTO(list));
        ts.setSaldo(service.getSaldo().toString());
        Optional<BigDecimal> saldo = list.stream().map(i -> i.getValor()).reduce((sum, val) -> sum.add(val));
        if (saldo.isPresent())
            ts.setSaldoNoPeriodo(saldo.get().toString());
        else
                ts.setSaldoNoPeriodo("0.00");
        return ts;
    }

    @GetMapping("all")
    public List<TransferenciaDTO> index() {
        return mapper.toDTO(service.findAll());
    }

    @GetMapping("{id}")
    public TransferenciaDTO show(@PathVariable Long id) {
        return mapper.toDTO(service.findById(id));
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping
    public Long store(@RequestBody @Valid  TransferenciaDTO transferenciaDTO) {
        transferenciaDTO.setId(null);
        return service.save(mapper.toEntity(transferenciaDTO));
    }

    @PutMapping
    public Long update(@RequestBody @Valid TransferenciaDTO transferenciaDTO) {
        return service.save(mapper.toEntity(transferenciaDTO));
    }

}
