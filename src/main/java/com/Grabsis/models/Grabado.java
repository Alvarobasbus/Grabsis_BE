package com.Grabsis.models;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grabado {

    private Long idGrabado;
    private LocalDate fecha;
    private String descripcion;
    private Vehiculo vehiculo;
    private Empleado empleado;
    private Boolean isDeleted;
}
