package com.Grabsis.services;

import com.Grabsis.models.Egreso;
import com.Grabsis.models.Orden;

import java.time.LocalDate;
import java.util.List;

public interface OrdenService {


    List<Orden> obtenerTodos();

    Orden obtenerPorId(Long id);

    void isDeleted(Long id);


    Orden crear(Orden orden);

    List<Orden> listadoporFechas(LocalDate fecha1, LocalDate fecha2);

    Double efectivoTotal(LocalDate fecha1, LocalDate fecha2);

    Double debitoTotal(LocalDate fecha1, LocalDate fecha2);

    Double creditoTotal(LocalDate fecha1, LocalDate fecha2);

    Double transferenciaTotal(LocalDate fecha1, LocalDate fecha2);

    Double mercadoPagoTotal(LocalDate fecha1, LocalDate fecha2);
}
