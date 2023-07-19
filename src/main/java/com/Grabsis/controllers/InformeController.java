package com.Grabsis.controllers;

import com.Grabsis.models.*;
import com.Grabsis.services.FormularioService;
import com.Grabsis.services.GrabadoService;
import com.Grabsis.services.InsumoService;
import com.Grabsis.services.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/informe")
@RequiredArgsConstructor
public class InformeController {

    private final FormularioService formularioService;
    private final GrabadoService grabadoService;
    private final TurnoService turnoService;
    private final InsumoService insumoService;

    @GetMapping("/insumosBaja")
    public ResponseEntity<List<InformeInsumosDTO>> informeInsumoBaja(@RequestParam(required = true) LocalDate desde,
                                                                     @RequestParam(required = true) LocalDate hasta){

        List<InformeInsumosDTO> informe = insumoService.listadoBaja(desde,hasta);

        return ResponseEntity.ok(informe);
    }

    @GetMapping("/insumosAlta")
    public ResponseEntity<List<InformeInsumosDTO>> informeInsumoAlta(@RequestParam(required = true) LocalDate desde,
                                                                  @RequestParam(required = true) LocalDate hasta){

        List<InformeInsumosDTO> informe = insumoService.listadoAlta(desde,hasta);

        return ResponseEntity.ok(informe);
    }

    @GetMapping("/informeServicios")
    public ResponseEntity<InformeServicios> informeServicios(@RequestParam(required = true) LocalDate desde,
                                                                  @RequestParam(required = true) LocalDate hasta){

        InformeServicios informe =new InformeServicios();
        InformeCristalesDTO cristales= grabadoService.cantidadCristales(desde, hasta);
        InformeFormularioDTO formularios=formularioService.cantidadFormularios(desde, hasta);
        InformeServiciosDTO autopartes= grabadoService.cantidadAutopartes(desde, hasta);

        informe.setCristales(cristales.getCristales());
        informe.setAutopartes(autopartes.getAutopartes());
        informe.setFormularios(formularios.getFormulario());

        return ResponseEntity.ok(informe);
    }

    @GetMapping("/informeTurnos")
    public ResponseEntity<InformeTurnoDTO> informeTurnos(@RequestParam(required = true) LocalDate desde,
                                                                     @RequestParam(required = true) LocalDate hasta){

        InformeTurnoDTO informe = new InformeTurnoDTO();

        GetTurnoDTO cancelados= turnoService.cancelados(desde, hasta);
        GetTurnoDTO pagados= turnoService.pagados(desde, hasta);
        GetTurnoDTO cantidad= turnoService.cantidadDia(desde, hasta);
        GetTurnoDTO registrados= turnoService.registrados(desde, hasta);

        informe.setCancelados(cancelados.getResultado());
        informe.setCantidad(cantidad.getResultado());
        informe.setPagado(pagados.getResultado());
        informe.setRegistrados(registrados.getResultado());

        return ResponseEntity.ok(informe);
    }

}
