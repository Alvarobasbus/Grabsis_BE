package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "marca")
public class MarcaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMarca;
    @Column(unique=true)
    private String descripcion;

}
