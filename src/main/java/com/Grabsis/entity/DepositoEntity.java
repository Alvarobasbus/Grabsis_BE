package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "deposito")
public class DepositoEntity extends Deleteable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeposito;
    private LocalDate fecha;
    private double importe;
    private String banco;
    private String numeroTramite;

}
