package com.Grabsis.services.impl;

import com.Grabsis.entity.EmpleadoEntity;
import com.Grabsis.entity.InsumoEntity;
import com.Grabsis.entity.MarcaEntity;
import com.Grabsis.models.Empleado;
import com.Grabsis.models.Insumo;
import com.Grabsis.models.Marca;
import com.Grabsis.repositories.InsumoRepository;
import com.Grabsis.services.InsumoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InsumoServiceImpl implements InsumoService {

    @Autowired
    private InsumoRepository insumoRepository;

    private final ModelMapper modelMapper;


    @Override
    public List<Insumo> obtenerTodos() {
        List<InsumoEntity> lista = insumoRepository.findAll();

        List<Insumo> listaInsumo = lista.stream()
                .map(entity ->modelMapper.map(entity, Insumo.class))
                .collect(Collectors.toList());

        return listaInsumo;
    }

    @Override
    public Insumo obtenerPorId(Long id) {
        Optional<InsumoEntity> opInsumo = insumoRepository.findById(id);

        if(opInsumo.isPresent()) {
            Insumo insumo = modelMapper.map(opInsumo.get(), Insumo.class);

            return insumo;
        }
        throw new EntityNotFoundException("No existe el insumo con ese Id: " + id);
    }

    @Override
    public Insumo crear(Insumo insumo) {
        InsumoEntity creado= modelMapper.map(insumo, InsumoEntity.class);
        InsumoEntity created= null;
        Insumo response= null;

        InsumoEntity consulta= insumoRepository.findByDescripcion(insumo.getDescripcion());
        if(consulta!=null){
            throw new IllegalArgumentException("Error al crear el insumo, ya existe un insumo con ese nombre");
        }

        if(creado !=null){
            try{

                created= insumoRepository.save(creado);
            }catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al crear el insumo");
            }
            response= modelMapper.map(created, Insumo.class);

        }
        return response;
    }

    @Override
    public Insumo aumentar(Insumo insumo) {
        InsumoEntity creado= modelMapper.map(insumo, InsumoEntity.class);
        InsumoEntity created= null;
        Insumo response= null;


        if(creado !=null){
            try{

                created= insumoRepository.save(creado);
            }catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al modificar el insumo");
            }
            response= modelMapper.map(created, Insumo.class);

        }else{
            throw new IllegalArgumentException("Error al modificar el insumo");
        }

        return response;

    }


    @Override
    public Insumo restar(Insumo insumo) {
        InsumoEntity creado= modelMapper.map(insumo, InsumoEntity.class);
        InsumoEntity created= null;
        Insumo response= null;


        if(creado !=null){
                try{

                    created= insumoRepository.save(creado);
                }catch (DataIntegrityViolationException ex){
                    throw new IllegalArgumentException("Error al modificar el insumo");
                }
                response= modelMapper.map(created, Insumo.class);

        }


        return response;

    }
}
