package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "egreso")
public class EgresoEntity extends Deleteable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEgreso;
    private LocalDate fecha;
    private double importe;
    private String concepto;
    private String numeroFactura;
    private String tipoFactura;

}
