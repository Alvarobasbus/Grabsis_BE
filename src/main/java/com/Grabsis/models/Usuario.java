package com.Grabsis.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario  {

    private Long documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String domicilio;
    private Provincia provincia;
    private Boolean isDeleted;
}
