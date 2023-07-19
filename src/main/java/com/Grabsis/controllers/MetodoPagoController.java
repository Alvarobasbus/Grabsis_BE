package com.Grabsis.controllers;

import com.Grabsis.models.MetodoPago;
import com.Grabsis.models.Orden;
import com.Grabsis.services.MetodoPagoService;
import com.Grabsis.services.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/metodopago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    @GetMapping()
    public List<MetodoPago> obtenerTodas(){
        List<MetodoPago> lista= metodoPagoService.obtenerTodos();
        return lista;
    }

    @PostMapping("/crear")
    public MetodoPago crearMarca(@RequestBody MetodoPago metodoPago){
        return metodoPagoService.crear(metodoPago);
    }

    @GetMapping("/{id}")
    public MetodoPago obtenerPorId(@PathVariable Long id){
        MetodoPago metodoPago= metodoPagoService.obtenerPorId(id);
        return metodoPago;
    }
}
