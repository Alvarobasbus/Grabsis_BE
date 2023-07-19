package com.Grabsis.services;

import com.Grabsis.entity.GrabadoEntity;
import com.Grabsis.models.Egreso;
import com.Grabsis.models.Grabado;
import com.Grabsis.models.InformeCristalesDTO;
import com.Grabsis.models.InformeServiciosDTO;

import java.time.LocalDate;
import java.util.List;

public interface GrabadoService {

    List<Grabado> obtenerTodos();

    Grabado obtenerPorId(Long id);

    void isDeleted(Long id);

    void borrarPorId(Long id);

    Grabado crear(Grabado grabado);

    List<Grabado> listadoporFechas(LocalDate fecha1, LocalDate fecha2);

    List<Grabado> listadoporPatente(String patente);

    InformeCristalesDTO cantidadCristales(LocalDate fecha1, LocalDate fecha2);

    InformeServiciosDTO cantidadAutopartes(LocalDate fecha1, LocalDate fecha2);
}
