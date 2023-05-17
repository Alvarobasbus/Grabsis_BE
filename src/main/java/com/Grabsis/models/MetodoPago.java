package com.Grabsis.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetodoPago {
    private Long idMetodo;
    private String descripcion;
}
