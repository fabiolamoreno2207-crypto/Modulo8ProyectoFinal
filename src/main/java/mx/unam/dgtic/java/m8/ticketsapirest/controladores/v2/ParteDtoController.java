package mx.unam.dgtic.java.m8.ticketsapirest.controladores.v2;

import jakarta.validation.Valid;
import mx.unam.dgtic.java.m8.ticketsapirest.dtos.ParteDto;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Parte;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.ParteService;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2.interfaces.ParteDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/parte")
public class ParteDtoController {

    @Autowired
    private ParteDtoService parteDtoService;

    @GetMapping("/")
    public ResponseEntity<List<ParteDto>> getPartes() {
        return ResponseEntity.ok(parteDtoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParteDto> getParteById(@PathVariable Long id) {
        return ResponseEntity.ok(parteDtoService.obtenerPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<ParteDto> createParte(@Valid @RequestBody ParteDto parteDto) {
        return ResponseEntity.ok(parteDtoService.guardar(parteDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParteDto> updateParteCompleto(@PathVariable Long id,
                                                        @Valid @RequestBody ParteDto parteDto) {
        return ResponseEntity.ok(parteDtoService.actualizarCompleto(id, parteDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ParteDto> updateParteParcial(@PathVariable Long id,
                                                       @RequestBody ParteDto parteDto) {
        return ResponseEntity.ok(parteDtoService.actualizarParcial(id, parteDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParte(@PathVariable Long id) {
        parteDtoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
