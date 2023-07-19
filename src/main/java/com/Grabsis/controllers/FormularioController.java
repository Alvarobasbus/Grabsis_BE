package com.Grabsis.controllers;

import com.Grabsis.models.Formulario;
import com.Grabsis.services.FormularioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/formulario")
@RequiredArgsConstructor
public class FormularioController {

    private final FormularioService formularioService;

    @GetMapping()
    public List<Formulario> obtenerTodas(){
        List<Formulario> lista= formularioService.obtenerTodos();
        return lista;
    }


    @PostMapping("/crear")
    public Formulario crear(@RequestBody Formulario formulario){
        return formularioService.crear(formulario);
    }


    @GetMapping("/{id}")
    public Formulario obtenerPorId(@PathVariable Long id){
        Formulario formulario= formularioService.obtenerPorId(id);
        return formulario;
    }

    @GetMapping("/borrar/{id}")
    public void borrarPorId(@PathVariable Long id){
        formularioService.borrarPorId(id);
    }

    @GetMapping("/porFechas")
    public List<Formulario> obtenerPorfechas(@RequestParam(required = true) LocalDate desde,
                                         @RequestParam(required = true) LocalDate hasta){
        List<Formulario> lista= formularioService.listadoporFechas(desde,hasta);

        return lista;
    }
}
