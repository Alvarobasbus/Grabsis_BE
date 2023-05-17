package com.Grabsis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detalle  {

    private Long idDetalle;
    private int cantidad;
    private double precio;
    private OrdenDeVenta orden;
    private Servicio servicio;
    private Turno turno;

}
