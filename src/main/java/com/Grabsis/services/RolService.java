package com.Grabsis.services;


import com.Grabsis.models.Rol;

import java.util.List;

public interface RolService {


    List<Rol> obtenerTodas();

    Rol obtenerPorId(Long id);
}
