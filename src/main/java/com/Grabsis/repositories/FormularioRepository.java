package com.Grabsis.repositories;

import com.Grabsis.entity.EgresoEntity;
import com.Grabsis.entity.FormularioEntity;
import com.Grabsis.models.InformeFormularioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FormularioRepository extends JpaRepository<FormularioEntity, Long> {

    @Query(
            value ="SELECT * " +
                    "FROM formulario " +
                    "WHERE fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    List<FormularioEntity> listadoporFechas(LocalDate fecha1, LocalDate fecha2);

    FormularioEntity findByIdFormulario(Long id);

    @Query(
            value ="SELECT count(numero_formulario) as formulario " +
                    "FROM formulario " +
                    "WHERE fecha " +
                    "BETWEEN ?1 AND ?2 ",
            nativeQuery = true
    )
    InformeFormularioDTO cantidadFormularios(LocalDate fecha1, LocalDate fecha2);


}
