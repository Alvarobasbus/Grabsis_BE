package com.Grabsis.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformeTurnoDTO {


    private Integer cantidad;
    private Integer cancelados;
    private Integer pagado;
    private Integer registrados;
}
