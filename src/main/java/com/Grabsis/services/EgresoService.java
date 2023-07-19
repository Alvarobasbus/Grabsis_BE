package com.Grabsis.services;

import com.Grabsis.entity.EgresoEntity;
import com.Grabsis.models.Detalle;
import com.Grabsis.models.Egreso;

import java.time.LocalDate;
import java.util.List;

public interface EgresoService {

    List<Egreso> obtenerTodos();

    Egreso obtenerPorId(Long id);

    void isDeleted(Long id);

    void borrarPorId(Long id);

    Egreso crear(Egreso egreso);

    List<Egreso> listadoporFechas(LocalDate fecha1, LocalDate fecha2);

    Double egresoTotal(LocalDate fecha1, LocalDate fecha2);
}
