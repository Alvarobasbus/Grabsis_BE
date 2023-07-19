package com.Grabsis.services.impl;

import com.Grabsis.entity.InsumoEntity;
import com.Grabsis.entity.ServicioEntity;
import com.Grabsis.models.Servicio;
import com.Grabsis.repositories.ServicioRepository;
import com.Grabsis.services.ServicioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    private final ModelMapper modelMapper;

    @Override
    public Servicio actualizar(Servicio servicio) {
        ServicioEntity creado = modelMapper.map(servicio, ServicioEntity.class);
        LocalDate date = LocalDate.now();
        creado.setFecha(date);
        ServicioEntity created=null;
        Servicio response=null;

        if(creado != null){
            try{
                created= servicioRepository.save(creado);
            } catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al crear el Servicio");
            }
            response= modelMapper.map(created, Servicio.class);

        }
        return response;
    }

    @Override
    public Servicio crear(Servicio servicio) {
        ServicioEntity creado = modelMapper.map(servicio, ServicioEntity.class);
        LocalDate date = LocalDate.now();
        creado.setFecha(date);
        ServicioEntity created=null;
        Servicio response=null;

        ServicioEntity consulta= servicioRepository.findByDescripcion(servicio.getDescripcion());
        if(consulta!=null){
            throw new IllegalArgumentException("Error al crear el servicio, ya existe un servicio con ese nombre");
        }
        if(creado != null){
            try{
                created= servicioRepository.save(creado);
            } catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al crear el Servicio");
            }
            response= modelMapper.map(created, Servicio.class);

        }
        return response;
    }

    @Override
    public Servicio BuscarporId(Long Id) {
        Optional<ServicioEntity> opServicio = servicioRepository.findById(Id);

        if(opServicio.isPresent()) {
            Servicio servicio = modelMapper.map(opServicio.get(), Servicio.class);

            return servicio;
        }
        throw new EntityNotFoundException("No existe la marca con ese Id: " + Id);
    }

    @Override
    public List<Servicio> obtenerTodos() {
        List<ServicioEntity> lista = servicioRepository.findAll();

        List<Servicio> listaServicio = lista.stream()
                .map(entity ->modelMapper.map(entity, Servicio.class))
                .collect(Collectors.toList());

        return listaServicio;
    }
}
