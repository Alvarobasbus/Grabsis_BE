package com.Grabsis.controllers;


import com.Grabsis.models.Provincia;
import com.Grabsis.services.ProvinciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/provincia")
@RequiredArgsConstructor
public class ProvinciaController {

    private final ProvinciaService provinciaService;

    @GetMapping()
    public List<Provincia> obtenerTodas(){
        List<Provincia> lista= provinciaService.obtenerTodas();
        return lista;
    }

    @GetMapping("/{id}")
    public Provincia obtenerTodas(@PathVariable Long id){
        Provincia provincia= provinciaService.obtenerPorId(id);
        return provincia;
    }


}
