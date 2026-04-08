package mx.unam.dgtic.java.m8.ticketsapirest.controladores;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Parte;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.ParteRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.ParteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parte")
public class ParteRestController {

    @Autowired
    private ParteService parteService;

    @GetMapping("/")
    public ResponseEntity<List<Parte>> obtenerPartes() {
        return ResponseEntity.ok(parteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parte> obtenerParte(@PathVariable Long id) {
        return ResponseEntity.ok(parteService.obtenerPartePorId(id));
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Parte>> obtenerPartesPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(parteService.obtenerPorProductoId(productoId));
    }

    @PostMapping("/producto/{productoId}")
    public ResponseEntity<Parte> insertar(@PathVariable Long productoId, @RequestBody Parte parte) {
        Parte nueva = parteService.guardar(productoId, parte);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parte> actualizar(@PathVariable Long id, @RequestBody Parte parte) {
        return ResponseEntity.ok(parteService.actualizarCompleto(id, parte));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Parte> actualizarParcial(@PathVariable Long id, @RequestBody Parte parte) {
        return ResponseEntity.ok(parteService.actualizarParcial(id, parte));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Parte> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(parteService.eliminar(id));
    }
}
