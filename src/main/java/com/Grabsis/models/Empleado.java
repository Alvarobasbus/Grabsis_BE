package com.Grabsis.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado  {

    private Long idEmpleado;
    private Integer documento;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private Rol rol;
    private Boolean isDeleted;


}
