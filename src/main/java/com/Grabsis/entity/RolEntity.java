package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="rol")
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

}
