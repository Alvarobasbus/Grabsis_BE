package com.Grabsis.repositories;

import com.Grabsis.entity.DepositoEntity;
import com.Grabsis.entity.EgresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DepositoRepository extends JpaRepository<DepositoEntity, Long> {

    @Query(
            value ="SELECT * " +
                    "FROM deposito " +
                    "WHERE fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    List<DepositoEntity> listadoporFechas(LocalDate fecha1, LocalDate fecha2);

    DepositoEntity findByIdDeposito(Long id);

    @Query(
            value ="SELECT sum(importe) " +
                    "FROM deposito " +
                    "WHERE fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    Double depositoTotal(LocalDate fecha1, LocalDate fecha2);
}
