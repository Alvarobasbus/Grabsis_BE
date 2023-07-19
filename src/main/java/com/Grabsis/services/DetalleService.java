package com.Grabsis.services;

import com.Grabsis.models.Detalle;
import com.Grabsis.models.Egreso;

import java.time.LocalDate;
import java.util.List;

public interface DetalleService {


    List<Detalle> obtenerTodos();

    Detalle obtenerPorId(Long id);

    Detalle crear(Detalle detalle);

    void isDeleted(Long id);


    void borrarPorId(Long id);

    List<Detalle> listadoporFechas(LocalDate fecha1, LocalDate fecha2);
}
