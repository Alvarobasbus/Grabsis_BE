package com.Grabsis.services.impl;


import com.Grabsis.entity.DetalleInsumoEntity;
import com.Grabsis.entity.InsumoEntity;
import com.Grabsis.models.DetalleInsumo;
import com.Grabsis.models.Insumo;
import com.Grabsis.repositories.DetalleInsumoRepository;
import com.Grabsis.services.DetalleInsumoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetalleInsumoServiceImpl implements DetalleInsumoService {

    @Autowired
    private DetalleInsumoRepository detalleInsumoRepository;

    private final ModelMapper modelMapper;


    @Override
    public List<DetalleInsumo> obtenerTodos() {
        List<DetalleInsumoEntity> lista = detalleInsumoRepository.findAll();

        List<DetalleInsumo> listaDInsumo = lista.stream()
                .map(entity ->modelMapper.map(entity, DetalleInsumo.class))
                .collect(Collectors.toList());

        return listaDInsumo;
    }

    @Override
    public DetalleInsumo obtenerPorId(Long id) {
        Optional<DetalleInsumoEntity> opDInsumo = detalleInsumoRepository.findById(id);

        if(opDInsumo.isPresent()) {
            DetalleInsumo dInsumo = modelMapper.map(opDInsumo.get(), DetalleInsumo.class);

            return dInsumo;
        }
        throw new EntityNotFoundException("No existe el detalle de insumo con ese Id: " + id);
    }

    @Override
    public DetalleInsumo crear(DetalleInsumo detalleInsumo) {
        DetalleInsumoEntity creado= modelMapper.map(detalleInsumo, DetalleInsumoEntity.class);
        DetalleInsumoEntity created= null;
        DetalleInsumo response= null;

        if(creado !=null){
            try{
                created= detalleInsumoRepository.save(creado);
            }catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al crear el detalle de insumo");
            }
            response= modelMapper.map(created, DetalleInsumo.class);

        }
        return response;
    }
}
