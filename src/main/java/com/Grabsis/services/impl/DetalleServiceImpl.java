package com.Grabsis.services.impl;


import com.Grabsis.entity.DetalleEntity;
import com.Grabsis.entity.EgresoEntity;
import com.Grabsis.entity.TurnoEntity;
import com.Grabsis.models.Detalle;
import com.Grabsis.models.Egreso;
import com.Grabsis.repositories.DetalleRepository;
import com.Grabsis.services.DetalleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetalleServiceImpl implements DetalleService {

    @Autowired
    private DetalleRepository detalleRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Detalle> obtenerTodos() {
        List<DetalleEntity> lista = detalleRepository.findAll();


        List<Detalle> listaDetalle = lista.stream()
                .map(entity ->modelMapper.map(entity, Detalle.class))
                .collect(Collectors.toList());

        return listaDetalle;
    }

    @Override
    public void isDeleted(Long id) {
        DetalleEntity detalle= detalleRepository.findByIdDetalle(id);

        if(detalle.getIsDeleted()==false){
            try{
                detalle.delete();
                detalleRepository.save(detalle);
            }catch (Exception e){
                throw new EntityNotFoundException("No se pudo borrar el detalle ");
            }
        }else{
            throw new IllegalArgumentException("No se puede borrar el detalle ya que el mismo ya esta eliminado ");
        }

    }

    @Override
    public List<Detalle> listadoporFechas(LocalDate fecha1, LocalDate fecha2) {
        List<DetalleEntity> lista= detalleRepository.listadoporFechas(fecha1,fecha2);

        List<Detalle> listaDetalles = lista.stream()
                .map(entity ->modelMapper.map(entity, Detalle.class))
                .collect(Collectors.toList());

        return listaDetalles;

    }

    public void borrarPorId(Long id) {
        detalleRepository.deleteById(id);

    }

    @Override
    public Detalle obtenerPorId(Long id) {
        DetalleEntity detalle= detalleRepository.findByIdDetalle(id);


        if(detalle.getIsDeleted()==true){
            throw new EntityNotFoundException("El detalle fue eliminado ");
        }


        if(detalle != null){
            Detalle response = modelMapper.map(detalle, Detalle.class);

            return  response;
        }
        throw new EntityNotFoundException("No existe detalle con el id: " + id);
    }

    @Override
    public Detalle crear(Detalle detalle) {
        DetalleEntity creado= modelMapper.map(detalle, DetalleEntity.class);
        creado.setIsDeleted(false);
        DetalleEntity created= null;
        Detalle response= null;

        if(creado !=null){
            try{
                created= detalleRepository.save(creado);
            }catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al intentar crear un detalle de orden de trabajo");
            }
            response= modelMapper.map(created, Detalle.class);

        }
        return response;
    }
}
