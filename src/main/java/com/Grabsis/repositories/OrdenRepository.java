package com.Grabsis.repositories;

import com.Grabsis.entity.OrdenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrdenRepository extends JpaRepository<OrdenEntity, Long> {

    @Query(
            value ="SELECT * " +
                    "FROM ordendeventa " +
                    "WHERE fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    List<OrdenEntity> listadoporFechas(LocalDate fecha1, LocalDate fecha2);


    @Query(
            value ="SELECT sum(o.total) " +
                    "FROM ordendeventa o " +
                    "join metodopago m " +
                    "on o.id_metodo= m.id_metodo " +
                    "WHERE m.descripcion='EFECTIVO' and o.fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    Double efectivoTotal(LocalDate fecha1, LocalDate fecha2);


    @Query(
            value ="SELECT sum(o.total) " +
                    "FROM ordendeventa o " +
                    "join metodopago m " +
                    "on o.id_metodo= m.id_metodo " +
                    "WHERE m.descripcion='TRANSFERENCIA' and o.fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    Double transferenciaTotal(LocalDate fecha1, LocalDate fecha2);

    @Query(
            value ="SELECT sum(o.total) " +
                    "FROM ordendeventa o " +
                    "join metodopago m " +
                    "on o.id_metodo= m.id_metodo " +
                    "WHERE m.descripcion='TARJETA DE DEBITO' and o.fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    Double debitoTotal(LocalDate fecha1, LocalDate fecha2);

    @Query(
            value ="SELECT sum(o.total) " +
                    "FROM ordendeventa o " +
                    "join metodopago m " +
                    "on o.id_metodo= m.id_metodo " +
                    "WHERE m.descripcion='TARJETA DE CREDITO' and o.fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    Double creditoTotal(LocalDate fecha1, LocalDate fecha2);

    @Query(
            value ="SELECT sum(o.total) " +
                    "FROM ordendeventa o " +
                    "join metodopago m " +
                    "on o.id_metodo= m.id_metodo " +
                    "WHERE m.descripcion='MERCADO PAGO' and o.fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    Double mercadoPagoTotal(LocalDate fecha1, LocalDate fecha2);

    OrdenEntity findByIdOrden(Long id);
}
