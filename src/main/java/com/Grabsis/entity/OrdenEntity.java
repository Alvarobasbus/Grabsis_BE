package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "ordendeventa")
public class OrdenEntity extends Deleteable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;
    private LocalDate fecha;
    private double total;

    @ManyToOne
    @JoinColumn(name="idTurno")
    private TurnoEntity turno;

    @ManyToOne
    @JoinColumn(name="idEmpleado")
    private EmpleadoEntity empleado;

}
