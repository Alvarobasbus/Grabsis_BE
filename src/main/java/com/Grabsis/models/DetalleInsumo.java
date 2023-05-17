package com.Grabsis.models;

import com.Grabsis.entity.EmpleadoEntity;
import com.Grabsis.entity.InsumoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleInsumo {

    private Long idDetalleInsumo;
    private Date fecha;
    private boolean alta=true;
    private Integer numero;
    private Insumo insumo;
    private Empleado empleado;
}
