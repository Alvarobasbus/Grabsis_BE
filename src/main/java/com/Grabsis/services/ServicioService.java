package com.Grabsis.services;

import com.Grabsis.models.Servicio;

import java.util.List;

public interface ServicioService {


    Servicio crear(Servicio servicio);

    Servicio actualizar(Servicio servicio);


    Servicio BuscarporId(Long Id);

    List<Servicio> obtenerTodos();


}
