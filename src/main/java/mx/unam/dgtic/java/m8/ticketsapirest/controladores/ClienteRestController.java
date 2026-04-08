package mx.unam.dgtic.java.m8.ticketsapirest.controladores;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Cliente;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.ClienteRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteRestController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerClientePorId(id));
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<Cliente>> obtenerClientePorTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(clienteService.obtenerClientePorTicketId(ticketId));
    }

    @PostMapping("/")
    public ResponseEntity<Cliente> insertar(@RequestBody Cliente cliente) {
        Cliente nuevo = clienteService.guardar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id,
                                              @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizarCompleto(id, cliente));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> actualizarParcial(@PathVariable Long id,
                                                     @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizarParcial(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.eliminar(id));
    }
}
