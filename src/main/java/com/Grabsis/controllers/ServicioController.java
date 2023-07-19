package com.Grabsis.controllers;


import com.Grabsis.models.Marca;
import com.Grabsis.models.Servicio;
import com.Grabsis.services.ServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/servicio")
@RequiredArgsConstructor
public class ServicioController {


    private final ServicioService servicioService;

    @GetMapping()
    public List<Servicio> obtenerTodos(){
        List<Servicio> lista= servicioService.obtenerTodos();
        return lista;
    }

    @PostMapping("/crear")
    public Servicio crearServicio(@RequestBody Servicio servicio){
        return servicioService.crear(servicio);
    }

    @PostMapping("/actualizar")
    public Servicio actualizar(@RequestBody Servicio servicio){
        return servicioService.actualizar(servicio);
    }

    @GetMapping("/{id}")
    public Servicio obtenerTodas(@PathVariable Long id){
        Servicio servicio= servicioService.BuscarporId(id);
        return servicio;
    }
}
