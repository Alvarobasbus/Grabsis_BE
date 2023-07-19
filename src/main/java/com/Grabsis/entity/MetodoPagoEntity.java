package com.Grabsis.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "metodopago")
public class MetodoPagoEntity extends Deleteable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMetodo;
    private String descripcion;
}
