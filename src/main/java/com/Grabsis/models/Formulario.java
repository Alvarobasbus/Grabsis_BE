package com.Grabsis.models;

import com.Grabsis.entity.EmpleadoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Formulario {

    private Long idFormulario;
    private LocalDate fecha;
    private String numeroFormulario;
    private Empleado empleado;
}
