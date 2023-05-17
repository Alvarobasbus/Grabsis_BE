package com.Grabsis.services.impl;

import com.Grabsis.entity.UsuarioEntity;
import com.Grabsis.entity.VehiculoEntity;
import com.Grabsis.models.Usuario;
import com.Grabsis.models.Vehiculo;
import com.Grabsis.repositories.VehiculoRepository;
import com.Grabsis.services.VehiculoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    private final ModelMapper modelMapper;

    @Override
    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        VehiculoEntity creado= modelMapper.map(vehiculo, VehiculoEntity.class);
        VehiculoEntity created= null;
        Vehiculo response= null;

        if(creado !=null){
            try{
                created= vehiculoRepository.save(creado);
            }catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al crear el Vehiculo");
            }
            response= modelMapper.map(created, Vehiculo.class);

        }
        return response;
    }

    @Override
    public Vehiculo obtenerPorId(Long patente) {
        Optional<VehiculoEntity> opVehiculo= vehiculoRepository.findById(patente);

        if(opVehiculo.isPresent()){
            Vehiculo vehiculo = modelMapper.map(opVehiculo, Vehiculo.class);

            return  vehiculo;
        }
        throw new EntityNotFoundException("No existe Vehiculo con la patente: " + patente);
    }
}
