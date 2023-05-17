package com.Grabsis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Vehiculo implements Serializable {

    private String patente;
    private Marca marca;
    private String modelo;
    private String motor;
    private String chasis;
    private String tipo;
    private Integer autopartes;
    private Integer cristales;

}
