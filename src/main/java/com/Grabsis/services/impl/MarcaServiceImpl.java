package com.Grabsis.services.impl;

import com.Grabsis.entity.MarcaEntity;
import com.Grabsis.models.Marca;
import com.Grabsis.repositories.MarcaRepository;
import com.Grabsis.services.MarcaService;
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
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Marca> obtenerTodas() {
        List<MarcaEntity> lista = marcaRepository.findAll();

        List<Marca> listaMarcas = lista.stream()
                .map(entity ->modelMapper.map(entity, Marca.class))
                .collect(Collectors.toList());

        return listaMarcas;
    }

    @Override
    public Marca obtenerPorId(Long id) {
        Optional<MarcaEntity> opMarca = marcaRepository.findById(id);

        if(opMarca.isPresent()) {
            Marca marca = modelMapper.map(opMarca.get(), Marca.class);

            return marca;
        }
        throw new EntityNotFoundException("No existe la marca con ese Id: " + id);
    }

    @Override
    public Marca crear(Marca marca) {
        MarcaEntity creado = modelMapper.map(marca, MarcaEntity.class);
        MarcaEntity created=null;
        Marca response=null;
        if(creado != null){
            try{
                created= marcaRepository.save(creado);
            } catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al crear la Marca");
            }
            response= modelMapper.map(created, Marca.class);

        }
        return response;
    }
}
