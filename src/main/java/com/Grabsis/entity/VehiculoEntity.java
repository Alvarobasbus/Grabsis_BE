package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name= "vehiculo")
public class VehiculoEntity extends Deleteable {
    @Id
    private String patente;

    @ManyToOne
    @JoinColumn(name="idMarca")
    private MarcaEntity marca;
    private String modelo;
    private String motor;
    private String chasis;
    private String tipo;
    private Integer autopartes;
    private Integer cristales;
}
