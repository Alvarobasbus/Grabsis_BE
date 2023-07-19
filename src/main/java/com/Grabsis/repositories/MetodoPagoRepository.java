package com.Grabsis.repositories;

import com.Grabsis.entity.MetodoPagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetodoPagoRepository extends JpaRepository<MetodoPagoEntity, Long> {

    MetodoPagoEntity findByIdMetodo(Long id);
}
