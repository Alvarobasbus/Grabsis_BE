package com.Grabsis.services;

import com.Grabsis.models.DetalleInsumo;


import java.util.List;

public interface DetalleInsumoService {


    List<DetalleInsumo> obtenerTodos();

    DetalleInsumo obtenerPorId(Long id);

    DetalleInsumo crear(DetalleInsumo detalleInsumo);
}
