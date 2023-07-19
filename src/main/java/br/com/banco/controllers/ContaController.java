package br.com.banco.controllers;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dtos.ContaDTO;
import br.com.banco.mappers.ContaMapper;
import br.com.banco.services.ContaService;

@RestController
@RequestMapping("/api/conta")
public class ContaController {
    @Autowired
    ContaService service;

    ContaMapper mapper = Mappers.getMapper(ContaMapper.class);

    @PostMapping
    public Long store(@RequestBody @Valid ContaDTO contaDTO){
        contaDTO.setIdConta(null);
        return service.save(mapper.toEntity(contaDTO));
    }

    @PutMapping
    public Long update(@RequestBody @Valid ContaDTO contaDTO) {
        return service.save(mapper.toEntity(contaDTO));
    }

    @GetMapping
    public List<ContaDTO> index() {
        return mapper.toDTO(service.findAll());
    }

    @GetMapping("{id}")
    public ContaDTO show(@PathVariable Long id) {
        return mapper.toDTO(service.findById(id));
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        service.deleteById(id);
    }
}
