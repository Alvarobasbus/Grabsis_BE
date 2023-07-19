package com.Grabsis.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "ordendeventa")
public class OrdenEntity extends Deleteable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;
    private LocalDate fecha;
    private double total;

    @OneToOne
    @JoinColumn(name="idTurno")
    private TurnoEntity turno;

    //@OneToMany
    //@JoinColumn(name="idDetalle")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idOrden")
    @Fetch(FetchMode.SELECT)
    private List<DetalleEntity> detalle;



    @ManyToOne
    @JoinColumn(name="idEmpleado")
    private EmpleadoEntity empleado;

    @ManyToOne
    @JoinColumn(name="idMetodo")
    private MetodoPagoEntity metodoPago;

    public void agregarDetalle(DetalleEntity detalle){ this.detalle.add(detalle); }

}
