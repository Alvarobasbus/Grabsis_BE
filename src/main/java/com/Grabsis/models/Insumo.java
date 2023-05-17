package com.Grabsis.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insumo {

    private Long idInsumo;
    private String descripcion;
    private Integer cantidad;
}

