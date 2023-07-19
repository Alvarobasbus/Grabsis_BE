package com.Grabsis.services;

import com.Grabsis.models.Detalle;
import com.Grabsis.models.MetodoPago;

import java.util.List;

public interface MetodoPagoService {

    List<MetodoPago> obtenerTodos();

    MetodoPago obtenerPorId(Long id);

    MetodoPago crear(MetodoPago metodoPago);
}
