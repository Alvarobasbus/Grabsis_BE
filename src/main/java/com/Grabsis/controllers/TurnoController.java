package com.Grabsis.controllers;


import com.Grabsis.models.Egreso;
import com.Grabsis.models.Formulario;
import com.Grabsis.models.Turno;
import com.Grabsis.services.FormularioService;
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
    private final FormularioService formularioService;


    @PostMapping("/crear")
    public Turno crearTurno(@RequestBody Turno turno){
        return turnoService.crear(turno);
    }

    @PostMapping("/registrarFormulario")
    public Turno registrarFormulario(@RequestBody Turno turno){
            Formulario form= turno.getFormulario();
            LocalDate date = LocalDate.now();
            form.setFecha(date);
            turno.setFormulario(formularioService.crear(form));

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

    @GetMapping("/ListadoHoy")
    public List<Turno> obtenerHoy(){
        LocalDate date = LocalDate.now();
        List<Turno> listaTurnos = turnoService.obtenerPorFecha(date);

        return listaTurnos;
    }

    @GetMapping("/id/{id}")
    public Turno obtenerPorId(@PathVariable Long id){
        Turno turno= turnoService.BuscarporId(id);
        return turno;
    }

    @GetMapping("/delete/{id}")
    public void isDeleted(@PathVariable Long id){
        turnoService.isDeleted(id);
    }


    @GetMapping("/porFechas")
    public List<Turno> obtenerPorfechas(@RequestParam(required = true) LocalDate desde,
                                         @RequestParam(required = true) LocalDate hasta){
        List<Turno> lista= turnoService.listadoporFechas(desde,hasta);

        return lista;
    }

    @GetMapping("/porPatente")
    public List<Turno> obtenerPorfechas(@RequestParam(required = true) String patente){
        List<Turno> lista= turnoService.listadoporPatente(patente);

        return lista;
    }

    @GetMapping("/porUsuario")
    public List<Turno> obtenerPorUsuario(@RequestParam(required = true) Long documento){
        List<Turno> lista= turnoService.listadoporUsuario(documento);

        return lista;
    }

    @GetMapping("/porFormulario")
    public List<Turno> obtenerPorformulario(@RequestParam(required = true) String formulario){
        List<Turno> lista= turnoService.listadoporFormulario(formulario);

        return lista;
    }


}
