package com.Grabsis.services.impl;

import com.Grabsis.entity.EgresoEntity;
import com.Grabsis.models.Egreso;
import com.Grabsis.repositories.EgresoRepository;
import com.Grabsis.services.EgresoService;
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
public class EgresoServiceImpl implements EgresoService {

    @Autowired
    private EgresoRepository egresoRepository;

    private final ModelMapper modelMapper;


    @Override
    public List<Egreso> obtenerTodos() {
        List<EgresoEntity> lista = egresoRepository.findAll();


        List<Egreso> listaEgresos = lista.stream()
                .map(entity ->modelMapper.map(entity, Egreso.class))
                .collect(Collectors.toList());

        return listaEgresos;
    }

    @Override
    public Egreso obtenerPorId(Long id) {
        Optional<EgresoEntity> opEgreso = egresoRepository.findById(id);

        if(opEgreso.isPresent()) {
            Egreso egreso = modelMapper.map(opEgreso.get(), Egreso.class);

            return egreso;
        }
        throw new EntityNotFoundException("No existe el egreso con ese Id: " + id);
    }

    @Override
    public void isDeleted(Long id) {
        EgresoEntity egreso= egresoRepository.findByIdEgreso(id);

        try{
            egreso.delete();
            egresoRepository.save(egreso);
        }catch (Exception e){
            throw new EntityNotFoundException("No se pudo borrar el egreso ");
        }
    }

    @Override
    public void borrarPorId(Long id) {
        egresoRepository.deleteById(id);

    }

    @Override
    public Egreso crear(Egreso egreso) {
        EgresoEntity creado= modelMapper.map(egreso, EgresoEntity.class);
        creado.setIsDeleted(false);
        EgresoEntity created= null;
        Egreso response= null;

        if(creado !=null){
            try{
                created= egresoRepository.save(creado);
            }catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al generar el egreso");
            }
            response= modelMapper.map(created, Egreso.class);

        }
        return response;
    }

    @Override
    public List<Egreso> listadoporFechas(LocalDate fecha1, LocalDate fecha2) {
        List<EgresoEntity> lista= egresoRepository.listadoporFechas(fecha1,fecha2);

        List<Egreso> listaEgresos = lista.stream()
                .map(entity ->modelMapper.map(entity, Egreso.class))
                .collect(Collectors.toList());

        return listaEgresos;

    }

    @Override
    public Double egresoTotal(LocalDate fecha1, LocalDate fecha2) {
        Double egresoTotal= egresoRepository.egresoTotal(fecha1, fecha2);

        if(egresoTotal==null){
            egresoTotal= Double.valueOf(0);
        }
        return egresoTotal;
    }
}
