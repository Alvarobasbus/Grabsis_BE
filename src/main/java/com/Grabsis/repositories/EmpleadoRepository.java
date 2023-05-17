package com.Grabsis.repositories;

import com.Grabsis.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {

    EmpleadoEntity findByDocumento(Integer documento);

    EmpleadoEntity findByIdEmpleado(Long id);
}
