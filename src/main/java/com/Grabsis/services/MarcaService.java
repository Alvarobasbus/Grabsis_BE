package com.Grabsis.services;

import com.Grabsis.models.Marca;



import java.util.List;

public interface MarcaService {

    List<Marca> obtenerTodas();

    Marca obtenerPorId(Long id);

    Marca crear(Marca marca);
}
