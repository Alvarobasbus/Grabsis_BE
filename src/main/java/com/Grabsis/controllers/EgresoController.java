package com.Grabsis.controllers;

import com.Grabsis.models.DetalleInsumo;
import com.Grabsis.models.Egreso;
import com.Grabsis.services.EgresoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/egreso")
@RequiredArgsConstructor
public class EgresoController {

    private final EgresoService egresoService;

    @GetMapping()
    public List<Egreso> obtenerTodas(){
        List<Egreso> lista= egresoService.obtenerTodos();
        return lista;
    }


    @PostMapping("/crear")
    public Egreso crear(@RequestBody Egreso egreso){
        return egresoService.crear(egreso);
    }


    @GetMapping("/{id}")
    public Egreso obtenerPorId(@PathVariable Long id){
        Egreso egreso= egresoService.obtenerPorId(id);
        return egreso;
    }

    @GetMapping("/borrar/{id}")
    public void borrarPorId(@PathVariable Long id){
        egresoService.borrarPorId(id);
    }

    @GetMapping("/porFechas")
    public List<Egreso> obtenerPorfechas(@RequestParam(required = true) LocalDate desde,
                                         @RequestParam(required = true) LocalDate hasta){
        List<Egreso> lista= egresoService.listadoporFechas(desde,hasta);

        return lista;
    }


}
