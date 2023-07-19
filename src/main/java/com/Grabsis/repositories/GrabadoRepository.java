package com.Grabsis.repositories;


import com.Grabsis.entity.GrabadoEntity;
import com.Grabsis.entity.TurnoEntity;
import com.Grabsis.models.InformeCristalesDTO;
import com.Grabsis.models.InformeServiciosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface GrabadoRepository extends JpaRepository<GrabadoEntity, Long> {

    @Query(
            value ="SELECT * " +
                    "FROM grabado " +
                    "WHERE fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    List<GrabadoEntity> listadoporFechas(LocalDate fecha1, LocalDate fecha2);

    GrabadoEntity findByIdGrabado(Long id);

    @Query(
            value ="SELECT g.* " +
                    "FROM grabado g join vehiculo v  " +
                    "ON g.patente = v.patente " +
                    "WHERE g.patente = ?1  ",
            nativeQuery = true
    )
    List<GrabadoEntity> listadoporPatente(String patente);

    @Query(
            value ="SELECT count(descripcion) as Autopartes " +
                    "FROM grabado " +
                    "WHERE descripcion=\"AUTOPARTES\" and fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    InformeServiciosDTO cantidadAutopartes(LocalDate fecha1, LocalDate fecha2);

    @Query(
            value ="SELECT count(descripcion) as Cristales " +
                    "FROM grabado " +
                    "WHERE descripcion=\"CRISTALES\" and fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    InformeCristalesDTO cantidadCristales(LocalDate fecha1, LocalDate fecha2);


}
