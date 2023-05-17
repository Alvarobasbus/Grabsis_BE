package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "usuario")
public class UsuarioEntity extends Deleteable {
    @Id
    private Long documento;
    private String nombre;
    private String apellido;
    private String telefono;
    @Column(unique=true)
    private String email;
    private String domicilio;

    @ManyToOne
    @JoinColumn(name="idProvincia")
    private ProvinciaEntity provincia;
}
