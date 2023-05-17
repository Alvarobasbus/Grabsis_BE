package com.Grabsis.controllers;
;
import com.Grabsis.models.Usuario;
import com.Grabsis.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario){

        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/{documento}")
    public Usuario obtenerPorId(@PathVariable Long documento){
        Usuario user = usuarioService.obtenerPorId(documento);

        return user;
    }

    @GetMapping("/delete/{id}")
    public void isDeleted(@PathVariable Long id){
        usuarioService.isDeleted(id);
    }

    @GetMapping("/activar/{id}")
    public void activar(@PathVariable Long id){
        usuarioService.activate(id);
    }
}
