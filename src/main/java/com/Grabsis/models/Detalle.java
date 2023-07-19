package com.Grabsis.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detalle  {

    private Long idDetalle;
    private int cantidad;
    private double precio;
    private Orden orden;
    private Servicio servicio;
    private Turno turno;
    private Boolean isDeleted;
    private LocalDate fecha;

}
