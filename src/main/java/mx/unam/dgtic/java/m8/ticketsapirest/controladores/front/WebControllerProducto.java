package mx.unam.dgtic.java.m8.ticketsapirest.controladores.front;

import jakarta.validation.Valid;
import mx.unam.dgtic.java.m8.ticketsapirest.dtos.ProductoDto;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2.WebClientProductoDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/producto")
public class WebControllerProducto {
    @Autowired
    private WebClientProductoDtoService webClientProductoDtoService;

    // GET: lista de productos
    @GetMapping
    public String getTodosVista(Model modelo) {
        List<ProductoDto> productos = webClientProductoDtoService.getTodosVista().collectList().block();
        modelo.addAttribute("productos", productos);
        return "todosProductos";
    }

    // GET: detalle de un producto
    @GetMapping("/{id}")
    public String getDetalleProductoVista(Model modelo, @PathVariable Long id) {
        ProductoDto productoDto = webClientProductoDtoService.getProductoById(id).block();
        modelo.addAttribute("producto", productoDto);
        return "detalleProducto";
    }

    // GET: formulario para nuevo producto
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model modelo) {
        modelo.addAttribute("producto", new ProductoDto());
        return "formularioProducto";
    }

    // POST: guardar nuevo producto
    @PostMapping("/nuevo/producto")
    public String guardarProducto(@Valid @ModelAttribute("producto") ProductoDto productoDto) {
        webClientProductoDtoService.guardarProducto(productoDto).block();
        return "redirect:/web/producto";
    }

    // GET: formulario para editar producto
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(Model modelo, @PathVariable Long id) {
        ProductoDto producto = webClientProductoDtoService.getProductoById(id).block();
        if (producto == null) {
            return "redirect:/web/producto";
        }
        modelo.addAttribute("producto", producto);
        return "actualizarProducto";
    }

    // POST: actualizar producto
    @PostMapping("/actualizar")
    public String modificarProducto(@ModelAttribute("producto") ProductoDto producto) {
        webClientProductoDtoService.actualizarProducto(producto.getId_producto(), producto).block();
        return "redirect:/web/producto";
    }

    // GET: eliminar producto
    @GetMapping("/{id}/eliminar")
    public String eliminarProducto(@PathVariable Long id) {
        webClientProductoDtoService.eliminarProducto(id).block();
        return "redirect:/web/producto";
    }
}
