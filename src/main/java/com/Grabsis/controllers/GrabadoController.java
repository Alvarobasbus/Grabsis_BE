package com.Grabsis.controllers;


import com.Grabsis.models.Grabado;
import com.Grabsis.models.Turno;
import com.Grabsis.models.Vehiculo;
import com.Grabsis.services.GrabadoService;
import com.Grabsis.services.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/grabado")
@RequiredArgsConstructor
public class GrabadoController {

    private final GrabadoService grabadoService;
    private final VehiculoService vehiculoService;

    @GetMapping()
    public List<Grabado> obtenerTodas(){
        List<Grabado> lista= grabadoService.obtenerTodos();
        return lista;
    }


    @PostMapping("/crear")
    public Grabado crear(@RequestBody Grabado grabado){
        return grabadoService.crear(grabado);
    }

    @PostMapping("/autopartes")
    public Grabado crearAutopartes(@RequestBody Grabado grabado){
        LocalDate date = LocalDate.now();
        grabado.setFecha(date);
        grabado.setDescripcion("AUTOPARTES");
        grabado.setIsDeleted(false);

        Vehiculo v= vehiculoService.obtenerPorId(grabado.getVehiculo().getPatente());
        v.setAutopartes(true);
        vehiculoService.crearVehiculo(v);
        Grabado g= grabadoService.crear(grabado);
        return g;



    }

    @PostMapping("/cristales")
    public Grabado crearCristales(@RequestBody Grabado grabado){
        LocalDate date = LocalDate.now();
        grabado.setFecha(date);
        grabado.setDescripcion("CRISTALES");
        grabado.setIsDeleted(false);
        Vehiculo v= vehiculoService.obtenerPorId(grabado.getVehiculo().getPatente());
        v.setCristales(true);
        vehiculoService.crearVehiculo(v);
        Grabado g= grabadoService.crear(grabado);
        return g;

    }

    @GetMapping("/porPatente")
    public List<Grabado> obtenerPorfechas(@RequestParam(required = true) String patente){
        List<Grabado> lista= grabadoService.listadoporPatente(patente);

        return lista;
    }


    @GetMapping("/{id}")
    public Grabado obtenerPorId(@PathVariable Long id){
        Grabado grabado= grabadoService.obtenerPorId(id);
        return grabado;
    }

    @GetMapping("/borrar/{id}")
    public void borrarPorId(@PathVariable Long id){
        grabadoService.borrarPorId(id);
    }

    @GetMapping("/delete/{id}")
    public void isDeleted(@PathVariable Long id){
        grabadoService.isDeleted(id);
    }

    @GetMapping("/porFechas")
    public List<Grabado> obtenerPorfechas(@RequestParam(required = true) LocalDate desde,
                                         @RequestParam(required = true) LocalDate hasta){
        List<Grabado> lista= grabadoService.listadoporFechas(desde,hasta);

        return lista;
    }
}
