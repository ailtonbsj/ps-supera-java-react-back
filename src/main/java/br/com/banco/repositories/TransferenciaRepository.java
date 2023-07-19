package br.com.banco.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.banco.entities.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>  {
    List<Transferencia> findAllByDataTransferenciaGreaterThanEqual(Date inicio);
    List<Transferencia> findAllByDataTransferenciaLessThanEqual(Date fim);
    List<Transferencia> findAllByDataTransferenciaBetween(Date inicio, Date fim);

    @Query("SELECT SUM(t.valor) FROM Transferencia t")
    BigDecimal saldo();
}
