package com.Grabsis.services;

import com.Grabsis.models.Provincia;

import java.util.List;

public interface ProvinciaService {

    List<Provincia> obtenerTodas();

    Provincia obtenerPorId(Long id);
}
