package mx.unam.dgtic.java.m8.ticketsapirest.controladores.v2;


import jakarta.validation.Valid;
import mx.unam.dgtic.java.m8.ticketsapirest.dtos.UsuarioDto;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Usuario;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.UsuarioService;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2.interfaces.UsuarioDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/usuario")
public class UsuarioDtoController {
    @Autowired
    private UsuarioDtoService usuarioDtoService;

    @GetMapping("/")
    public ResponseEntity<List<UsuarioDto>> getUsuarios() {
        return ResponseEntity.ok(usuarioDtoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioDtoService.obtenerPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioDto> createUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.ok(usuarioDtoService.guardar(usuarioDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuarioCompleto(@PathVariable Long id,
                                                            @Valid @RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.ok(usuarioDtoService.actualizarCompleto(id, usuarioDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuarioParcial(@PathVariable Long id,
                                                           @RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.ok(usuarioDtoService.actualizarParcial(id, usuarioDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioDtoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
