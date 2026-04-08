package mx.unam.dgtic.java.m8.ticketsapirest.controladores;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Producto;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.ProductoRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoRestController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public ResponseEntity<List<Producto>> getProductos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerProductoPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto productoNuevo = productoService.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id,
                                                   @RequestBody Producto productoNuevo) {
        return ResponseEntity.ok(productoService.actualizarCompleto(id, productoNuevo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Producto> updateParcialProducto(@PathVariable Long id,
                                                          @RequestBody Producto productoNuevo) {
        return ResponseEntity.ok(productoService.actualizarParcial(id, productoNuevo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);

        // Verificar si el producto tiene partes asociadas
        if (producto.getPartes() != null && !producto.getPartes().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el producto porque tiene partes asociadas.");
        }

        // Verificar si el producto está en tickets
        if (producto.getTickets() != null && !producto.getTickets().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el producto porque está asociado a tickets.");
        }

        // Si no tiene dependencias, se elimina
        Producto eliminado = productoService.eliminar(id);
        return ResponseEntity.ok(eliminado);
    }
}
