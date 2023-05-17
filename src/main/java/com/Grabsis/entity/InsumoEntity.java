package com.Grabsis.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;

@Data
@Entity
@Table(name = "insumo")
public class InsumoEntity extends Deleteable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInsumo;
    //@Column(unique=true)
    private String descripcion;
    private Integer cantidad;

}
