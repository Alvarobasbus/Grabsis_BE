package com.Grabsis.services;

import com.Grabsis.models.Formulario;
import com.Grabsis.models.InformeFormularioDTO;


import java.time.LocalDate;
import java.util.List;

public interface FormularioService {

    List<Formulario> obtenerTodos();

    Formulario obtenerPorId(Long id);

    void borrarPorId(Long id);

    Formulario crear(Formulario formulario);

    List<Formulario> listadoporFechas(LocalDate fecha1, LocalDate fecha2);

    InformeFormularioDTO cantidadFormularios(LocalDate fecha1, LocalDate fecha2);
}
