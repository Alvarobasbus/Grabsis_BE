package com.Grabsis.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformeCaja {

        private double saldoTotal=0;
        private double saldoCaja=0;
        private double deposito=0;
        private double efectivo=0;
        private double egreso=0;
        private double transferencia=0;
        private double debito=0;
        private double credito=0;
        private double mercadoPago=0;


}
