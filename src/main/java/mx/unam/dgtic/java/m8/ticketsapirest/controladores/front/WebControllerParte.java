package mx.unam.dgtic.java.m8.ticketsapirest.controladores.front;

import jakarta.validation.Valid;
import mx.unam.dgtic.java.m8.ticketsapirest.dtos.ParteDto;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2.WebClientParteDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/parte")
public class WebControllerParte {
    @Autowired
    private WebClientParteDtoService webClientParteDtoService;

    // GET: lista de partes
    @GetMapping
    public String getTodasVista(Model modelo) {
        List<ParteDto> partes = webClientParteDtoService.getTodasVista().collectList().block();
        modelo.addAttribute("partes", partes);
        return "todosPartes";
    }

    // GET: detalle de una parte
    @GetMapping("/{id}")
    public String getDetalleParteVista(Model modelo, @PathVariable Long id) {
        ParteDto parteDto = webClientParteDtoService.getParteById(id).block();
        modelo.addAttribute("parte", parteDto);
        return "detalleParte";
    }

    // GET: formulario para nueva parte
    @GetMapping("/nueva")
    public String mostrarFormulario(Model modelo) {
        modelo.addAttribute("parte", new ParteDto());
        return "formularioParte";
    }

    // POST: guardar nueva parte
    @PostMapping("/nueva/parte")
    public String guardarParte(@Valid @ModelAttribute("parte") ParteDto parteDto) {
        webClientParteDtoService.guardarParte(parteDto).block();
        return "redirect:/web/parte";
    }

    // GET: formulario para editar parte
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(Model modelo, @PathVariable Long id) {
        ParteDto parte = webClientParteDtoService.getParteById(id).block();
        if (parte == null) {
            return "redirect:/web/parte";
        }
        modelo.addAttribute("parte", parte);
        return "actualizarParte";
    }

    // POST: actualizar parte
    @PostMapping("/actualizar")
    public String modificarParte(@ModelAttribute("parte") ParteDto parte) {
        webClientParteDtoService.actualizarParte(parte.getId_parte(), parte).block();
        return "redirect:/web/parte";
    }

    // GET: eliminar parte
    @GetMapping("/{id}/eliminar")
    public String eliminarParte(@PathVariable Long id) {
        webClientParteDtoService.eliminarParte(id).block();
        return "redirect:/web/parte";
    }
}
