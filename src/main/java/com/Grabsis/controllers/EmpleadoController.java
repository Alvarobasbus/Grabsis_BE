package com.Grabsis.controllers;


import com.Grabsis.entity.EmpleadoEntity;
import com.Grabsis.models.*;
import com.Grabsis.services.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @GetMapping()
    public List<Empleado> obtenerTodos(){
        List<Empleado> lista= empleadoService.obtenerTodos();
        return lista;
    }


    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable Long id){
        Empleado empleado= empleadoService.obtenerPorId(id);
        return empleado;
    }

    @GetMapping("/documento/{documento}")
    public Empleado obtenerPorDocumento(@PathVariable Integer documento){
        Empleado empleado= empleadoService.obtenerPorDocumento(documento);
        return empleado;
    }


    @PostMapping("/modificar")
    public Empleado modificarEmpleado(@RequestBody Empleado empleado){
        Empleado emp= empleadoService.obtenerPorId(empleado.getIdEmpleado());

        emp.setApellido(empleado.getApellido());
        emp.setNombre(empleado.getNombre());
        emp.setDocumento(empleado.getDocumento());
        emp.setRol(empleado.getRol());
        emp.setContrasenia(empleado.getContrasenia());

        return empleadoService.crearEmpleado(emp);
    }


    @PostMapping("/crear")
    public Empleado crearEmpleado(@RequestBody Empleado empleado){
        return empleadoService.crearEmpleado(empleado);
    }

    @GetMapping("/delete/{id}")
    public void isDeleted(@PathVariable Long id){
        empleadoService.isDeleted(id);
    }

    @GetMapping("/activar/{id}")
    public void activar(@PathVariable Long id){
        empleadoService.activate(id);
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Empleado empleado){
        if(empleado.getDocumento()==null || empleado.getContrasenia()==null){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Debe ingresar el documento y contraseña");
        }
        Empleado empl= empleadoService.obtenerPorDocumento(empleado.getDocumento());


        if(empl.getContrasenia().equals(empleado.getContrasenia())){
            return  ResponseEntity.ok(empl);
        }else {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Documento o Contraseña incorrectos.");
        }

    }

}
