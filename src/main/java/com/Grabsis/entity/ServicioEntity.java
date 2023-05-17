package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "servicio")
public class ServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idServicio;
    private String descripcion;
    private double precio;

}
