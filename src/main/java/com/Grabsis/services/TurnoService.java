package com.Grabsis.services;

import com.Grabsis.entity.TurnoEntity;
import com.Grabsis.models.Egreso;
import com.Grabsis.models.GetTurnoDTO;
import com.Grabsis.models.Turno;

import java.time.LocalDate;
import java.util.List;

public interface TurnoService {


    Turno crear(Turno turno);

    void isDeleted(Long id);

    Turno BuscarporId(Long Id);

    List<Turno> listadoporFechas(LocalDate fecha1, LocalDate fecha2);

    List<Turno> obtenerPorFecha(LocalDate fecha);

    List<Turno> listadoporPatente(String patente);

    List<Turno> listadoporFormulario(String formulario);

    List<Turno> listadoporUsuario(Long documento);
    GetTurnoDTO cantidadDia(LocalDate fecha1, LocalDate fecha2);

    GetTurnoDTO cancelados(LocalDate fecha1, LocalDate fecha2);

    GetTurnoDTO pagados(LocalDate fecha1, LocalDate fecha2);

    GetTurnoDTO registrados(LocalDate fecha1, LocalDate fecha2);





}
