package com.Grabsis.services;

import com.Grabsis.models.Turno;

import java.time.LocalDate;
import java.util.List;

public interface TurnoService {


    Turno crear(Turno turno);

    void isDeleted(Long id);

    Turno BuscarporId(Long Id);

    List<Turno> obtenerPorFecha(LocalDate fecha);





}
