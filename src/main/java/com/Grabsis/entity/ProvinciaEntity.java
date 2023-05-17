package com.Grabsis.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "provincia")
public class ProvinciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProvincia;
    private String nombre;
}
