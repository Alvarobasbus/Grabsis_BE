package com.Grabsis.controllers;


import com.Grabsis.models.Detalle;
import com.Grabsis.models.Egreso;
import com.Grabsis.services.DetalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/detalle")
@RequiredArgsConstructor
public class DetalleController {

    private final DetalleService detalleService;

    @GetMapping()
    public List<Detalle> obtenerTodas(){
        List<Detalle> lista= detalleService.obtenerTodos();
        return lista;
    }

    @GetMapping("/borrar/{id}")
    public void borrarPorId(@PathVariable Long id){
        detalleService.borrarPorId(id);
    }

    @PostMapping("/crear")
    public Detalle crearMarca(@RequestBody Detalle detalle){
        return detalleService.crear(detalle);
    }

    @GetMapping("/{id}")
    public Detalle obtenerPorId(@PathVariable Long id){
        Detalle detalle= detalleService.obtenerPorId(id);
        return detalle;
    }

    @GetMapping("/porFechas")
    public List<Detalle> obtenerPorfechas(@RequestParam(required = true) LocalDate desde,
                                         @RequestParam(required = true) LocalDate hasta){
        List<Detalle> lista= detalleService.listadoporFechas(desde,hasta);

        return lista;
    }
}
