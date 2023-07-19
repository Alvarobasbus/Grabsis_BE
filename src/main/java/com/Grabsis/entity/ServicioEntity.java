package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name= "servicio")
public class ServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idServicio;
    private String descripcion;
    private double precio;
    private LocalDate fecha;

}
