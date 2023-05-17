package com.Grabsis.services;

import com.Grabsis.models.Empleado;

import java.util.List;

public interface EmpleadoService {

    Empleado crearEmpleado(Empleado empleado);

    Empleado obtenerPorId(Long id);

    void isDeleted(Long id);

    void activate(Long id);

    Empleado obtenerPorDocumento(Integer documento);

    List<Empleado> obtenerTodos();

}
