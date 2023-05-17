package com.Grabsis.repositories;

import com.Grabsis.entity.TurnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TurnoRepository extends JpaRepository<TurnoEntity, Long> {

    List<TurnoEntity> findByFecha(LocalDate fecha);

    TurnoEntity findByIdTurno(Long id);


    //List<TurnoEntity> findByFechaBetweenAndFecha(LocalDate fecha1, LocalDate fecha2);

}
