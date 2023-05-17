package com.Grabsis.controllers;


import com.Grabsis.models.Turno;
import com.Grabsis.services.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/turno")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;


    @PostMapping("/crear")
    public Turno crearTurno(@RequestBody Turno turno){
        return turnoService.crear(turno);
    }

    @PostMapping("/confirmar")
    public Turno confirmarTurno(@RequestBody Turno turno){
        turno.setIngreso(true);

        return turnoService.crear(turno);
    }


    @GetMapping("/{fecha}")
    public List<Turno> obtenerPorFecha(@PathVariable LocalDate fecha){
      List<Turno> listaTurnos = turnoService.obtenerPorFecha(fecha);

      return listaTurnos;
    }
    @GetMapping("/delete/{id}")
    public void isDeleted(@PathVariable Long id){
        turnoService.isDeleted(id);
    }


}
