package com.Grabsis.controllers;

import com.Grabsis.models.Insumo;
import com.Grabsis.models.Marca;
import com.Grabsis.services.InsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/insumo")
@RequiredArgsConstructor
public class InsumoController {

    private final InsumoService insumoService;


    @GetMapping()
    public List<Insumo> obtenerTodas(){
        List<Insumo> lista= insumoService.obtenerTodos();
        return lista;
    }


    @PostMapping("/crear")
    public Insumo crear(@RequestBody Insumo insumo){
        return insumoService.crear(insumo);
    }

    @PostMapping("/aumentar")
    public Insumo aumentar(@RequestBody Insumo insumo){
        return insumoService.aumentar(insumo);


    }

    @PostMapping("/restar")
    public Insumo restar(@RequestBody Insumo insumo){
        return insumoService.restar(insumo);
    }


    @GetMapping("/{id}")
    public Insumo obtenerPorId(@PathVariable Long id){
        Insumo insumo= insumoService.obtenerPorId(id);
        return insumo;
    }
}
