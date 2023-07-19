package com.Grabsis.services;

import com.Grabsis.entity.InsumoEntity;
import com.Grabsis.models.InformeInsumos;
import com.Grabsis.models.InformeInsumosDTO;
import com.Grabsis.models.Insumo;

import java.time.LocalDate;
import java.util.List;

public interface InsumoService {

    List<Insumo> obtenerTodos();

    Insumo obtenerPorId(Long id);

    Insumo crear(Insumo insumo);

    Insumo aumentar(Insumo insumo);

    Insumo restar(Insumo insumo);

    List<InformeInsumosDTO> listadoBaja(LocalDate fecha1, LocalDate fecha2);

    List<InformeInsumosDTO> listadoAlta(LocalDate fecha1, LocalDate fecha2);

    List<Insumo> listadoReponer();

}
