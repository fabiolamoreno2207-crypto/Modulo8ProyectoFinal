package mx.unam.dgtic.java.m8.ticketsapirest.controladores.front;

import jakarta.validation.Valid;
import mx.unam.dgtic.java.m8.ticketsapirest.dtos.UsuarioDto;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2.WebClientUsuarioDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/web/usuario")
public class WebController {

    @Autowired
    private WebClientUsuarioDtoService webClientUsuarioDtoService;

    // GET: lista de usuarios
    @GetMapping
    public String getTodosVista(Model modelo) {
        List<UsuarioDto> usuarios = webClientUsuarioDtoService
                .getTodosVista()
                .collectList().block();
        modelo.addAttribute("usuarios", usuarios);
        return "todosUsuarios"; // asegúrate que el template exista en /templates/
    }

    // GET: detalle de un usuario
    @GetMapping("/{id}")
    public String getDetalleUsuarioVista(Model modelo, @PathVariable Long id) {
        UsuarioDto usuarioDto = webClientUsuarioDtoService.getUsuarioById(id).block();
        modelo.addAttribute("usuario", usuarioDto);
        return "detalleUsuario";
    }

    // GET: formulario para nuevo usuario
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model modelo) {
        modelo.addAttribute("usuario", new UsuarioDto());
        return "formularioUsuario";
    }

    // POST: guardar nuevo usuario
    @PostMapping("/nuevo/usuario")
    public String guardarUsuario(@Valid @ModelAttribute("usuario") UsuarioDto usuarioDto) {
        webClientUsuarioDtoService.guardarUsuario(usuarioDto).block();
        return "redirect:/web/usuario";
    }

    // GET: formulario para editar usuario
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(Model modelo, @PathVariable Long id) {
        UsuarioDto usuario = webClientUsuarioDtoService.getUsuarioById(id).block();
        if (usuario == null) {
            return "redirect:/web/usuario";
        }
        modelo.addAttribute("usuario", usuario);
        return "actualizarUsuario";
    }

    // POST: actualizar usuario
    @PostMapping("/actualizar")
    public String modificarUsuario(@ModelAttribute("usuario") UsuarioDto usuario) {
        webClientUsuarioDtoService.actualizarUsuario(usuario.getId(), usuario).block();
        return "redirect:/web/usuario";
    }

    // GET: eliminar usuario
    @GetMapping("/{id}/eliminar")
    public String eliminarUsuario(@PathVariable Long id) {
        webClientUsuarioDtoService.eliminarUsuario(id).block();
        return "redirect:/web/usuario";
    }
}
