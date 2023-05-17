package com.Grabsis.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle")
public class DetalleEntity extends Deleteable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;
    private int cantidad;
    private double precio;

    @ManyToOne
    @JoinColumn(name="idOrden")
    private OrdenEntity orden;

    @ManyToOne
    @JoinColumn(name="idServicio")
    private ServicioEntity servicio;

    @ManyToOne
    @JoinColumn(name="idTurno")
    private TurnoEntity turno;

}
