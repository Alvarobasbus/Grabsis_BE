package com.Grabsis.services.impl;


import com.Grabsis.entity.ProvinciaEntity;
import com.Grabsis.models.Provincia;
import com.Grabsis.repositories.ProvinciaRepository;
import com.Grabsis.services.ProvinciaService;
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
public class ProvinciaServiceImpl implements ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Provincia> obtenerTodas() {
        List<ProvinciaEntity> lista = provinciaRepository.findAll();

        List<Provincia> listaProvincias = lista.stream()
                .map(entity ->modelMapper.map(entity, Provincia.class))
                .collect(Collectors.toList());

        return listaProvincias;
    }

    @Override
    public Provincia obtenerPorId(Long id) {
        Optional<ProvinciaEntity> opProv = provinciaRepository.findById(id);

        if(opProv.isPresent()) {
            Provincia provincia = modelMapper.map(opProv.get(), Provincia.class);

            return provincia;
        }
        throw new EntityNotFoundException("No existe provincia con ese Id: " + id);
    }
}
