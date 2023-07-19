package com.Grabsis.repositories;

import com.Grabsis.entity.ServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<ServicioEntity, Long> {

    ServicioEntity findByIdServicio(Long id);

    ServicioEntity findByDescripcion(String descripcion);
}
