package com.Grabsis.controllers;


import com.Grabsis.models.Provincia;
import com.Grabsis.models.Rol;
import com.Grabsis.services.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rol")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping()
    public List<Rol> obtenerTodas(){
        List<Rol> lista= rolService.obtenerTodas();
        return lista;
    }

    @GetMapping("/{id}")
    public Rol obtenerTodas(@PathVariable Long id){
        Rol rol= rolService.obtenerPorId(id);
        return rol;
    }
}
