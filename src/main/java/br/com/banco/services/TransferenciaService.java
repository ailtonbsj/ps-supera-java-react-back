package br.com.banco.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;

@Service
public class TransferenciaService {
    @Autowired
    TransferenciaRepository repository;

    public BigDecimal getSaldo() {
        return repository.saldo();
    }

    public List<Transferencia> filter(Optional<Date> inicio, Optional<Date> fim, Optional<String> operador) {
        List<Transferencia> list;
        if (inicio.isPresent() && !fim.isPresent())
            list = repository.findAllByDataTransferenciaGreaterThanEqual(inicio.get());
        else if (!inicio.isPresent() && fim.isPresent())
            list = repository.findAllByDataTransferenciaLessThanEqual(fim.get());
        else if (inicio.isPresent() && fim.isPresent()) {
            // findAllByDataTransferenciaGreaterThanEqualAndDataTransferenciaLessThanEqual
            list = repository.findAllByDataTransferenciaBetween(inicio.get(), fim.get());
        } else
            list = repository.findAll();
        if (operador.isPresent() && !operador.get().equals("")) {
            return list.stream().filter(item -> {
                return item.getNomeOperadorTransacao() != null &&
                        item.getNomeOperadorTransacao().equals(operador.get());
            }).collect(Collectors.toList());
        } else
            return list;
    }

    public List<Transferencia> findAll() {
        return repository.findAll();
    }

    public Transferencia findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Long save(Transferencia transferencia) {
        return repository.save(transferencia).getId();
    }

}
