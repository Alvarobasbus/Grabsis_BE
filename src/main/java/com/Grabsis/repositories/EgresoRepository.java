package com.Grabsis.repositories;

import com.Grabsis.entity.EgresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EgresoRepository extends JpaRepository<EgresoEntity, Long> {

    @Query(
            value ="SELECT * " +
                    "FROM egreso " +
                    "WHERE fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    List<EgresoEntity> listadoporFechas(LocalDate fecha1, LocalDate fecha2);

    EgresoEntity findByIdEgreso(Long id);


    @Query(
            value ="SELECT sum(importe) " +
                    "FROM egreso " +
                    "WHERE fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    Double egresoTotal(LocalDate fecha1, LocalDate fecha2);







}
