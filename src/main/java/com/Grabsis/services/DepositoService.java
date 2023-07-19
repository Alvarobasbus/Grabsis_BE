package com.Grabsis.services;

import com.Grabsis.models.Deposito;
import com.Grabsis.models.Egreso;

import java.time.LocalDate;
import java.util.List;

public interface DepositoService {

    List<Deposito> obtenerTodos();

    Deposito obtenerPorId(Long id);

    void isDeleted(Long id);

    void borrarPorId(Long id);

    Deposito crear(Deposito deposito);

    List<Deposito> listadoporFechas(LocalDate fecha1, LocalDate fecha2);

    Double depositoTotal(LocalDate fecha1, LocalDate fecha2);
}
