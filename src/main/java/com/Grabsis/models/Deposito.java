package com.Grabsis.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deposito {

    private Long idDeposito;
    private LocalDate fecha;
    private double importe;
    private String banco;
    private String numeroTramite;
}
