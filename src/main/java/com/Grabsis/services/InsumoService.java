package com.Grabsis.services;

import com.Grabsis.models.Insumo;

import java.util.List;

public interface InsumoService {

    List<Insumo> obtenerTodos();

    Insumo obtenerPorId(Long id);

    Insumo crear(Insumo insumo);

    Insumo aumentar(Insumo insumo);

    Insumo restar(Insumo insumo);

}
