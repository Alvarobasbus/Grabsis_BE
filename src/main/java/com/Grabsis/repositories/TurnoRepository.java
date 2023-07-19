package com.Grabsis.repositories;


import com.Grabsis.entity.TurnoEntity;
import com.Grabsis.models.GetTurnoDTO;
import com.Grabsis.models.InformeServiciosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TurnoRepository extends JpaRepository<TurnoEntity, Long> {

   // List<TurnoEntity> findByFecha(LocalDate fecha);

    TurnoEntity findByIdTurno(Long id);

    @Query(
            value ="SELECT * " +
                    "FROM turno " +
                    "WHERE fecha = ?1 " +
                    "ORDER BY fecha, hora ",
            nativeQuery = true
    )
    List<TurnoEntity> findByFecha(LocalDate fecha1);

    //cantidad de turnos para el dia

    @Query(
            value ="SELECT * " +
                    "FROM turno " +
                    "WHERE fecha = ?1 " +
                    "ORDER BY fecha, hora ",
            nativeQuery = true
    )
    List<TurnoEntity> findBfyFecha(LocalDate fecha1);

    //select count(id_turno) from turno where ingreso=0 and is_deleted=0 and fecha = ?1

    @Query(
            value ="SELECT * " +
                    "FROM turno " +
                    "WHERE fecha " +
                    "BETWEEN ?1 AND ?2 " +
                    "ORDER BY fecha, hora ",
            nativeQuery = true
    )
    List<TurnoEntity> listadoporFechas(LocalDate fecha1, LocalDate fecha2);

    @Query(
            value ="SELECT t.* " +
                    "FROM turno t join vehiculo v  " +
                    "ON t.id_vehiculo = v.patente " +
                    "WHERE t.id_vehiculo = ?1  " +
                    "ORDER BY fecha, hora ",
            nativeQuery = true
    )
    List<TurnoEntity> listadoporPatente(String patente);


    @Query(
            value ="SELECT t.* " +
                    "FROM turno t join formulario f  " +
                    "ON t.id_formulario = f.id_formulario " +
                    "WHERE f.numero_formulario = ?1  " +
                    "ORDER BY fecha, hora ",
            nativeQuery = true
    )
    List<TurnoEntity> listadoporFormulario(String formulario);


    @Query(
            value ="SELECT t.* " +
                    "FROM turno t join usuario u " +
                    "ON t.id_usuario = u.documento " +
                    "WHERE t.id_usuario = ?1  " +
                    "ORDER BY fecha, hora ",
            nativeQuery = true
    )
    List<TurnoEntity> listadoporUsuario(Long documento);


    @Query(
            value ="SELECT count(id_turno) as Resultado " +
                    "FROM turno " +
                    "WHERE ingreso=0 and is_deleted=0 and fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    GetTurnoDTO cantidadDia(LocalDate fecha1, LocalDate fecha2);

    @Query(
            value ="SELECT count(id_turno) as Resultado " +
                    "FROM turno " +
                    "WHERE is_deleted=1 and fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    GetTurnoDTO cancelados(LocalDate fecha1, LocalDate fecha2);


    @Query(
            value ="SELECT count(id_turno) as Resultado " +
                    "FROM turno " +
                    "WHERE pagado=1  and fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    GetTurnoDTO pagados(LocalDate fecha1, LocalDate fecha2);

    @Query(
            value ="SELECT count(id_turno) as Resultado " +
                    "FROM turno " +
                    "WHERE fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    GetTurnoDTO registrados(LocalDate fecha1, LocalDate fecha2);


}
