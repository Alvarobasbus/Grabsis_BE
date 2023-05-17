package com.Grabsis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrdenDeVenta {

    private Long idOrden;
    private LocalDate fecha;
    private double total;
    private Turno turno;
    private Empleado empleado;
    private MetodoPago metodopago;


}
