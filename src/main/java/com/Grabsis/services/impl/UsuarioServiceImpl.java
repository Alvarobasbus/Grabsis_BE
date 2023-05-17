package com.Grabsis.services.impl;

import com.Grabsis.entity.EmpleadoEntity;
import com.Grabsis.entity.UsuarioEntity;
import com.Grabsis.models.Usuario;
import com.Grabsis.repositories.UsuarioRepository;
import com.Grabsis.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper;

    @Override
    public Usuario crearUsuario(Usuario user) {
        UsuarioEntity creado= modelMapper.map(user, UsuarioEntity.class);
        UsuarioEntity created= null;
        Usuario response= null;

        if(creado !=null){
            try{
                created= usuarioRepository.save(creado);
            }catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al crear el usuario");
            }
            response= modelMapper.map(created, Usuario.class);

        }
        return response;
    }

    @Override
    public Usuario obtenerPorId(Long documento) {
        Optional<UsuarioEntity> opUser= usuarioRepository.findById(documento);

        if(opUser.isPresent()){
            Usuario user = modelMapper.map(opUser, Usuario.class);

            return  user;
        }
        throw new EntityNotFoundException("No existe Usuario con el documento: " + documento);
    }

    @Override
    public void isDeleted(Long dni) {
        UsuarioEntity usuario= usuarioRepository.findByDocumento(dni);

        try{
            usuario.delete();
            usuarioRepository.save(usuario);
        }catch (Exception e){
            throw new EntityNotFoundException("No se pudo borrar el usuario ");
        }
    }

    @Override
    public void activate(Long dni) {
        UsuarioEntity usuario= usuarioRepository.findByDocumento(dni);

        try{
            usuario.activate();
            usuarioRepository.save(usuario);
        }catch (Exception e){
            throw new EntityNotFoundException("No se pudo activar el usuario ");
        }

    }
}
