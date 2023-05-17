package com.Grabsis.services.impl;


import com.Grabsis.entity.RolEntity;
import com.Grabsis.models.Rol;
import com.Grabsis.repositories.RolRepository;
import com.Grabsis.services.RolService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Rol> obtenerTodas() {
        List<RolEntity> lista = rolRepository.findAll();

        List<Rol> listaRoles = lista.stream()
                .map(entity ->modelMapper.map(entity, Rol.class))
                .collect(Collectors.toList());

        return listaRoles;
    }

    @Override
    public Rol obtenerPorId(Long id) {
        Optional<RolEntity> opRol = rolRepository.findById(id);

        if(opRol.isPresent()) {
            Rol rol = modelMapper.map(opRol.get(), Rol.class);

            return rol;
        }
        throw new EntityNotFoundException("No existe rol con ese Id: " + id);
    }
}
