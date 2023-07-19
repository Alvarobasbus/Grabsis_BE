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
        if(creado.getAutopartes()==null){
            creado.setAutopartes(false);
        }
        if(creado.getCristales()==null){
            creado.setCristales(false);
        }
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
    public Vehiculo obtenerPorId(String patente) {
        VehiculoEntity opVehiculo= vehiculoRepository.findByPatente(patente);


            Vehiculo vehiculo = modelMapper.map(opVehiculo, Vehiculo.class);

            return  vehiculo;

    }

    @Override
    public void grabadoCristales(Vehiculo vehiculo) {
        try{
            VehiculoEntity v= vehiculoRepository.findByPatente(vehiculo.getPatente());
            vehiculo.setCristales(true);
            vehiculoRepository.save(v);
        }catch (Exception e){
            throw new EntityNotFoundException("No es posible efectuar el grabado de cristales en el vehiculo ");
        }

    }

    @Override
    public void grabadoAutopartes(Vehiculo vehiculo) {

        try{
            VehiculoEntity v= vehiculoRepository.findByPatente(vehiculo.getPatente());
            vehiculo.setAutopartes(true);
            vehiculoRepository.save(v);


        }catch (Exception e){
            throw new EntityNotFoundException("No es posible efectuar el grabado de autopartes en el vehiculo ");
        }

    }
}
