package com.Grabsis.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "detalle")
public class DetalleEntity extends Deleteable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;
    private int cantidad;
    private double precio;
    private LocalDate fecha;


    //@ManyToOne
    @JoinColumn(name = "idOrden", referencedColumnName = "idOrden")
    private Long idOrden;

    @ManyToOne
    @JoinColumn(name="idServicio")
    private ServicioEntity servicio;

    @ManyToOne
    @JoinColumn(name="idTurno")
    private TurnoEntity turno;

}
