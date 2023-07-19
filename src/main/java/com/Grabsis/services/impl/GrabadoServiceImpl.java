package com.Grabsis.services.impl;


import com.Grabsis.entity.GrabadoEntity;
import com.Grabsis.entity.TurnoEntity;
import com.Grabsis.models.Grabado;
import com.Grabsis.models.InformeCristalesDTO;
import com.Grabsis.models.InformeServiciosDTO;
import com.Grabsis.models.Turno;
import com.Grabsis.repositories.GrabadoRepository;
import com.Grabsis.services.GrabadoService;
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
public class GrabadoServiceImpl implements GrabadoService {


    @Autowired
    private GrabadoRepository grabadoRepository;

    private final ModelMapper modelMapper;



    @Override
    public List<Grabado> obtenerTodos() {
        List<GrabadoEntity> lista = grabadoRepository.findAll();


        List<Grabado> listaGrabados = lista.stream()
                .map(entity ->modelMapper.map(entity, Grabado.class))
                .collect(Collectors.toList());

        return listaGrabados;
    }

    @Override
    public Grabado obtenerPorId(Long id) {
        Optional<GrabadoEntity> opGrabado = grabadoRepository.findById(id);

        if(opGrabado.isPresent()) {
            Grabado grabado = modelMapper.map(opGrabado.get(), Grabado.class);

            return grabado;
        }
        throw new EntityNotFoundException("No existe el Grabado con ese Id: " + id);
    }

    @Override
    public void isDeleted(Long id) {
        GrabadoEntity grabado= grabadoRepository.findByIdGrabado(id);

        try{
            grabado.delete();
            grabadoRepository.save(grabado);
        }catch (Exception e){
            throw new EntityNotFoundException("No se pudo borrar el grabado ");
        }
    }

    @Override
    public void borrarPorId(Long id) {
        grabadoRepository.deleteById(id);
    }

    @Override
    public Grabado crear(Grabado grabado) {
        GrabadoEntity creado= modelMapper.map(grabado, GrabadoEntity.class);
        creado.setIsDeleted(false);
        GrabadoEntity created= null;
        Grabado response= null;

        if(creado !=null){
            try{
                created= grabadoRepository.save(creado);
            }catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al generar el Grabado");
            }
            response= modelMapper.map(created, Grabado.class);

        }
        return response;
    }

    @Override
    public List<Grabado> listadoporFechas(LocalDate fecha1, LocalDate fecha2) {
        List<GrabadoEntity> lista= grabadoRepository.listadoporFechas(fecha1,fecha2);

        List<Grabado> listaGrabados = lista.stream()
                .map(entity ->modelMapper.map(entity, Grabado.class))
                .collect(Collectors.toList());

        return listaGrabados;
    }

    @Override
    public List<Grabado> listadoporPatente(String patente) {
        List<GrabadoEntity> lista= grabadoRepository.listadoporPatente(patente);

        List<Grabado> listaGrabados = lista.stream()
                .map(entity ->modelMapper.map(entity, Grabado.class))
                .collect(Collectors.toList());

        return listaGrabados;
    }

    @Override
    public InformeCristalesDTO cantidadCristales(LocalDate fecha1, LocalDate fecha2) {
        InformeCristalesDTO cantidad = grabadoRepository.cantidadCristales(fecha1,fecha2);

        return cantidad;
    }

    @Override
    public InformeServiciosDTO cantidadAutopartes(LocalDate fecha1, LocalDate fecha2) {
        InformeServiciosDTO cantidad = grabadoRepository.cantidadAutopartes(fecha1,fecha2);

        return cantidad;
    }
}
