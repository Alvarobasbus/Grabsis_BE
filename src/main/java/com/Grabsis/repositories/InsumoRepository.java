package com.Grabsis.repositories;

import com.Grabsis.entity.InsumoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsumoRepository extends JpaRepository<InsumoEntity, Long> {

    InsumoEntity findByDescripcion(String descripcion);

    InsumoEntity findByIdInsumo(Long id);


}
