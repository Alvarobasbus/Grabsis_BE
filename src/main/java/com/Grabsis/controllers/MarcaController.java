package com.Grabsis.controllers;


import com.Grabsis.models.Marca;
import com.Grabsis.services.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marca")
@RequiredArgsConstructor
public class MarcaController {


    private  final MarcaService marcaService;

    @GetMapping()
    public List<Marca> obtenerTodas(){
        List<Marca> lista= marcaService.obtenerTodas();
        return lista;
    }

    @PostMapping("/crear")
    public Marca crearMarca(@RequestBody Marca marca){
        return marcaService.crear(marca);
    }

    @GetMapping("/{id}")
    public Marca obtenerTodas(@PathVariable Long id){
        Marca marca= marcaService.obtenerPorId(id);
        return marca;
    }
}


