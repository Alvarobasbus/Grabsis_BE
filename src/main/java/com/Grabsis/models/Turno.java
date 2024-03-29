package com.Grabsis.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turno  {

    private Long idTurno;

    private LocalDate fecha;
    private String hora;
    private Boolean pagado;
    private Boolean ingreso;
    private Usuario usuario;
    private Empleado empleado;
    private Vehiculo vehiculo;
    private Formulario formulario;
    private Boolean isDeleted;


}







