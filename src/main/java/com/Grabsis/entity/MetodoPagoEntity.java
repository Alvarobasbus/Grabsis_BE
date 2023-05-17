package com.Grabsis.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "metodopago")
public class MetodoPagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMetodo;
    private String descripcion;
}
