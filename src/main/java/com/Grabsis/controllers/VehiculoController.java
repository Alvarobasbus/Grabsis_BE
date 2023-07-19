package com.Grabsis.controllers;


import com.Grabsis.models.Vehiculo;
import com.Grabsis.services.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vehiculo")
@RequiredArgsConstructor
public class VehiculoController {

    private final VehiculoService vehiculoService;


    @PostMapping("/crear")
    public Vehiculo crearVehiculo(@RequestBody Vehiculo vehiculo){
        return vehiculoService.crearVehiculo(vehiculo);
    }

    @GetMapping("/{patente}")
    public Vehiculo obtenerPorId(@PathVariable String patente){
        Vehiculo vehiculo = vehiculoService.obtenerPorId(patente);

        return vehiculo;
    }

}
