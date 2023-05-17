package com.Grabsis.controllers;

import com.Grabsis.models.DetalleInsumo;
import com.Grabsis.services.DetalleInsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/detalleinsumo")
@RequiredArgsConstructor
public class DetalleInsumoController {

    private final DetalleInsumoService detalleInsumoService;


    @GetMapping()
    public List<DetalleInsumo> obtenerTodas(){
        List<DetalleInsumo> lista= detalleInsumoService.obtenerTodos();
        return lista;
    }


    @PostMapping("/crear")
    public DetalleInsumo crear(@RequestBody DetalleInsumo detalleInsumo){
        return detalleInsumoService.crear(detalleInsumo);
    }


    @GetMapping("/{id}")
    public DetalleInsumo obtenerPorId(@PathVariable Long id){
        DetalleInsumo detalleInsumo= detalleInsumoService.obtenerPorId(id);
        return detalleInsumo;
    }
}
