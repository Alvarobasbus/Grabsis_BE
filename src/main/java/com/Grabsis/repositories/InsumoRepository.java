package com.Grabsis.repositories;

import com.Grabsis.entity.InsumoEntity;
import com.Grabsis.entity.TurnoEntity;
import com.Grabsis.models.InformeInsumos;
import com.Grabsis.models.InformeInsumosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface InsumoRepository extends JpaRepository<InsumoEntity, Long> {

    InsumoEntity findByDescripcion(String descripcion);

    InsumoEntity findByIdInsumo(Long id);

    @Query(
            value ="SELECT i.descripcion as Descripcion, sum(numero) as Cantidad " +
                    "FROM detalleinsumo d join insumo i " +
                    "on d.id_insumo=i.id_insumo " +
                    "WHERE d.alta=0 and fecha " +
                    "BETWEEN ?1 AND ?2 " +
                    "GROUP BY i.descripcion ",
            nativeQuery = true
    )
    List<InformeInsumosDTO> listadoBaja(LocalDate fecha1, LocalDate fecha2);

    @Query(
            value ="SELECT i.descripcion, sum(numero) " +
                    "FROM detalleinsumo d join insumo i " +
                    "on d.id_insumo=i.id_insumo " +
                    "WHERE d.alta=0 and fecha " +
                    "BETWEEN ?1 AND ?2 " +
                    "GROUP BY i.descripcion ",
            nativeQuery = true
    )
    List<InformeInsumosDTO> listadoAlta(LocalDate fecha1, LocalDate fecha2);


    @Query(
            value ="SELECT * " +
                    "FROM insumo " +
                    "WHERE cantidad<=5 ",
            nativeQuery = true
    )
    List<InsumoEntity> listadoReponer();






}
