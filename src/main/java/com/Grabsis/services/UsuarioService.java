package com.Grabsis.services;

import com.Grabsis.models.Usuario;

public interface UsuarioService {

    Usuario crearUsuario(Usuario user);

    Usuario obtenerPorId(Long documento);

    void isDeleted(Long dni);

    void activate(Long dni);

}
