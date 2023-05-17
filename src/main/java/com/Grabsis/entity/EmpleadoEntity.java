package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "empleado")
public class EmpleadoEntity extends Deleteable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleado;
    @Column(unique=true)
    private Integer documento;
    private String nombre;
    private String apellido;
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name="idRol")
    private RolEntity rol;
}
