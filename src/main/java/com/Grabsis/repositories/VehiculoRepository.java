package com.Grabsis.repositories;

import com.Grabsis.entity.TurnoEntity;
import com.Grabsis.entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long> {

    //VehiculoEntity findByPatente(String patente);

    @Query(
            value ="SELECT * " +
                    "FROM vehiculo " +
                    "WHERE patente = ?1 ",

            nativeQuery = true
    )
    VehiculoEntity findByPatente(String patente);
}
