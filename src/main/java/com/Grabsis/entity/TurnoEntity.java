package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;


@Data
@Entity
@Table(name= "turno")
public class TurnoEntity extends Deleteable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTurno;

    private Date fecha;
    private String hora;
    private boolean pagado=false;
    private boolean ingreso=false;


    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name="idEmpleado")
    private EmpleadoEntity empleado;

    @ManyToOne
    @JoinColumn(name="idVehiculo")
    private VehiculoEntity vehiculo;

    private String formulario;

    public void pagado(){ this.pagado = true; }

    public void ingreso() {this.ingreso=true; }
}
