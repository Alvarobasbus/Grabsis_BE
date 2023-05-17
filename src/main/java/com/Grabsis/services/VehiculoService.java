package com.Grabsis.services;

import com.Grabsis.models.Vehiculo;

public interface VehiculoService {

    Vehiculo crearVehiculo(Vehiculo vehiculo);

    Vehiculo obtenerPorId(Long patente);
}
