package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;


@Data
@Entity
@Table(name= "turno")
public class TurnoEntity extends Deleteable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTurno;

    private LocalDate fecha;
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

    @OneToOne
    @JoinColumn(name="idFormulario")
    private FormularioEntity formulario;

    public void pagado(){ this.pagado = true; }

    public void ingreso() {this.ingreso=true; }
}
