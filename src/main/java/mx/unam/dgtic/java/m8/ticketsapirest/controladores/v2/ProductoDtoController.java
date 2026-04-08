package mx.unam.dgtic.java.m8.ticketsapirest.controladores.v2;

import jakarta.validation.Valid;
import mx.unam.dgtic.java.m8.ticketsapirest.dtos.ProductoDto;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Producto;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.ProductoService;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2.interfaces.ProductoDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/producto")
public class ProductoDtoController {
    @Autowired
    private ProductoDtoService productoDtoService;

    @GetMapping("/")
    public ResponseEntity<List<ProductoDto>> getProductos() {
        return ResponseEntity.ok(productoDtoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> getProductoById(@PathVariable Long id) {
        return ResponseEntity.ok(productoDtoService.obtenerPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<ProductoDto> createProducto(@Valid @RequestBody ProductoDto productoDto) {
        return ResponseEntity.ok(productoDtoService.guardar(productoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> updateProductoCompleto(@PathVariable Long id,
                                                              @Valid @RequestBody ProductoDto productoDto) {
        return ResponseEntity.ok(productoDtoService.actualizarCompleto(id, productoDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductoDto> updateProductoParcial(@PathVariable Long id,
                                                             @RequestBody ProductoDto productoDto) {
        return ResponseEntity.ok(productoDtoService.actualizarParcial(id, productoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoDtoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
