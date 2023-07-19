package com.Grabsis.services.impl;

import com.Grabsis.entity.FormularioEntity;
import com.Grabsis.models.Formulario;
import com.Grabsis.models.InformeFormularioDTO;
import com.Grabsis.repositories.FormularioRepository;
import com.Grabsis.services.FormularioService;
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
public class FormularioServiceImpl implements FormularioService {

    @Autowired
    private FormularioRepository formularioRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Formulario> obtenerTodos() {
        List<FormularioEntity> lista = formularioRepository.findAll();


        List<Formulario> listaFormularios = lista.stream()
                .map(entity ->modelMapper.map(entity, Formulario.class))
                .collect(Collectors.toList());

        return listaFormularios;
    }

    @Override
    public Formulario obtenerPorId(Long id) {
        Optional<FormularioEntity> Formulario = formularioRepository.findById(id);

        if(Formulario.isPresent()) {
            Formulario form = modelMapper.map(Formulario.get(), Formulario.class);

            return form;
        }
        throw new EntityNotFoundException("No existe el Formulario con ese Id: " + id);
    }

    @Override
    public void borrarPorId(Long id) {
        formularioRepository.deleteById(id);
    }

    @Override
    public Formulario crear(Formulario formulario) {
        FormularioEntity creado= modelMapper.map(formulario, FormularioEntity.class);
        FormularioEntity created= null;
        Formulario response= null;

        if(creado !=null){
            try{
                created= formularioRepository.save(creado);
            }catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al generar el Formulario");
            }
            response= modelMapper.map(created, Formulario.class);

        }
        return response;
    }

    @Override
    public List<Formulario> listadoporFechas(LocalDate fecha1, LocalDate fecha2) {
        List<FormularioEntity> lista= formularioRepository.listadoporFechas(fecha1,fecha2);

        List<Formulario> listaFormularios = lista.stream()
                .map(entity ->modelMapper.map(entity, Formulario.class))
                .collect(Collectors.toList());

        return listaFormularios;
    }

    @Override
    public InformeFormularioDTO cantidadFormularios(LocalDate fecha1, LocalDate fecha2) {
        InformeFormularioDTO cantidad = formularioRepository.cantidadFormularios(fecha1,fecha2);
                return cantidad;
    }
}
