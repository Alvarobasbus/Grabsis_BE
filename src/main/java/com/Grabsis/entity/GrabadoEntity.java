package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "grabado")
public class GrabadoEntity  extends Deleteable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrabado;
    private LocalDate fecha;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name="patente")
    private VehiculoEntity vehiculo;

    @ManyToOne
    @JoinColumn(name="idEmpleado")
    private EmpleadoEntity empleado;
}
