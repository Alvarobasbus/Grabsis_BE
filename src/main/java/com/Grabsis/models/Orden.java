package com.Grabsis.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Orden {

    private Long idOrden;
    private LocalDate fecha;
    private double total;
    private Turno turno;
    private Empleado empleado;
    private List<Detalle> detalle;
    private MetodoPago metodoPago;
    private Boolean isDeleted;


}
