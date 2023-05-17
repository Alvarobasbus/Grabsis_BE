package com.Grabsis.repositories;

import com.Grabsis.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByDocumento(Long dni);
}
