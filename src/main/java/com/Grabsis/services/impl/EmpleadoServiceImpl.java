package com.Grabsis.services.impl;

import com.Grabsis.entity.EmpleadoEntity;
import com.Grabsis.entity.MarcaEntity;
import com.Grabsis.entity.ProvinciaEntity;
import com.Grabsis.entity.UsuarioEntity;
import com.Grabsis.models.Empleado;
import com.Grabsis.models.Marca;
import com.Grabsis.models.Provincia;
import com.Grabsis.models.Usuario;
import com.Grabsis.repositories.EmpleadoRepository;
import com.Grabsis.services.EmpleadoService;
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
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private final ModelMapper modelMapper;

    @Override
    public Empleado crearEmpleado(Empleado empleado) {
        EmpleadoEntity creado= modelMapper.map(empleado, EmpleadoEntity.class);
        creado.setIsDeleted(false);
        EmpleadoEntity created= null;
        Empleado response= null;

        if(creado !=null){
            try{
                created= empleadoRepository.save(creado);
            }catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al crear el usuario");
            }
            response= modelMapper.map(created, Empleado.class);

        }
        return response;
    }

    @Override
    public Empleado obtenerPorId(Long id) {
        Optional<EmpleadoEntity> opEmpl = empleadoRepository.findById(id);

        if(opEmpl.isPresent()) {
            Empleado empleado = modelMapper.map(opEmpl.get(), Empleado.class);

            return empleado;
        }
        throw new EntityNotFoundException("No existe el empleado con ese Id: " + id);
    }

    @Override
    public void isDeleted(Long id) {
        EmpleadoEntity empleado= empleadoRepository.findByIdEmpleado(id);

        try{
            empleado.delete();
            empleadoRepository.save(empleado);
        }catch (Exception e){
            throw new EntityNotFoundException("No se pudo borrar el empleado ");
        }

    }

    public void activate(Long id) {
        EmpleadoEntity empleado= empleadoRepository.findByIdEmpleado(id);

        try{
            empleado.activate();
            empleadoRepository.save(empleado);
        }catch (Exception e){
            throw new EntityNotFoundException("No se pudo activar el empleado ");
        }

    }

    @Override
    public Empleado obtenerPorDocumento(Integer documento) {
        EmpleadoEntity empleado= empleadoRepository.findByDocumento(documento);
        //Empleado response=null;

        if(empleado.getIsDeleted()==true){
            throw new EntityNotFoundException("El empleado fue eliminado ");
        }


        if(empleado != null){
            Empleado response = modelMapper.map(empleado, Empleado.class);

            return  response;
        }
        throw new EntityNotFoundException("No existe Empleado con el documento: " + documento);

    }

    @Override
    public List<Empleado> obtenerTodos() {
        List<EmpleadoEntity> lista = empleadoRepository.findAll();


        List<Empleado> listaEmpleados = lista.stream()
                .map(entity ->modelMapper.map(entity, Empleado.class))
                .collect(Collectors.toList());

        return listaEmpleados;

    }
}
