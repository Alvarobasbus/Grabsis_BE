package com.Grabsis.controllers;

import com.Grabsis.models.Deposito;
import com.Grabsis.models.Egreso;
import com.Grabsis.services.DepositoService;
import com.Grabsis.services.EgresoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/deposito")
@RequiredArgsConstructor
public class DepositoController {

    private final DepositoService depositoService;

    @GetMapping()
    public List<Deposito> obtenerTodas(){
        List<Deposito> lista= depositoService.obtenerTodos();
        return lista;
    }

    @GetMapping("/borrar/{id}")
    public void borrarPorId(@PathVariable Long id){
        depositoService.borrarPorId(id);
    }


    @PostMapping("/crear")
    public Deposito crear(@RequestBody Deposito deposito){
        return depositoService.crear(deposito);
    }


    @GetMapping("/{id}")
    public Deposito obtenerPorId(@PathVariable Long id){
        Deposito deposito= depositoService.obtenerPorId(id);
        return deposito;
    }

    @GetMapping("/porFechas")
    public List<Deposito> obtenerPorfechas(@RequestParam(required = true) LocalDate desde,
                                         @RequestParam(required = true) LocalDate hasta){
        List<Deposito> lista= depositoService.listadoporFechas(desde,hasta);

        return lista;
    }
}
