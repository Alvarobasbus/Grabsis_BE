package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "formulario")
public class FormularioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormulario;
    private LocalDate fecha;
    private String numeroFormulario;
    @ManyToOne
    @JoinColumn(name="idEmpleado")
    private EmpleadoEntity empleado;


}
