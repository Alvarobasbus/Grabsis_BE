package com.Grabsis.repositories;

import com.Grabsis.entity.DetalleEntity;
import com.Grabsis.entity.EgresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DetalleRepository extends JpaRepository<DetalleEntity, Long> {

    @Query(
            value ="SELECT * " +
                    "FROM detalle " +
                    "WHERE fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    List<DetalleEntity> listadoporFechas(LocalDate fecha1, LocalDate fecha2);

    DetalleEntity findByIdDetalle(Long id);



}
