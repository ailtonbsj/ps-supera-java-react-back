package br.com.banco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.banco.entities.Conta;
import br.com.banco.repositories.ContaRepository;

@Service
public class ContaService {
    @Autowired
    ContaRepository repository;

    public Long save(Conta conta) {
        return repository.save(conta).getIdConta();
    }

    public List<Conta> findAll() {
        return repository.findAll();
    }

    public Conta findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Ainda existem transferencias na conta.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
