package com.Grabsis.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Egreso {

    private Long idEgreso;
    private LocalDate fecha;
    private double importe;
    private String concepto;
    private String numeroFactura;
    private String tipoFactura;
    private Boolean isDeleted;
}
