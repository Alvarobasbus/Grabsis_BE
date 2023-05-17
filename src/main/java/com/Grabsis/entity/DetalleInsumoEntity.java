package com.Grabsis.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "detalleinsumo")
public class DetalleInsumoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleInsumo;
    private LocalDate fecha= LocalDate.now();
    private boolean alta=true;
    private Integer numero;

    @ManyToOne
    @JoinColumn(name="idInsumo")
    private InsumoEntity insumo;

    @ManyToOne
    @JoinColumn(name="idEmpleado")
    private EmpleadoEntity empleado;

}
