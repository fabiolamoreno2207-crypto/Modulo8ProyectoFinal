package mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2;

import mx.unam.dgtic.java.m8.ticketsapirest.dtos.ProductoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientProductoDtoService {
    @Autowired
    @Qualifier("productoWebClient")
    private WebClient productoWebClient;

    // Obtener todos los productos
    public Flux<ProductoDto> getTodosVista() {
        return productoWebClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(ProductoDto.class);
    }

    // Obtener un producto por ID
    public Mono<ProductoDto> getProductoById(Long id) {
        return productoWebClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(ProductoDto.class);
    }

    // Guardar nuevo producto
    public Mono<ProductoDto> guardarProducto(ProductoDto productoDto) {
        return productoWebClient.post()
                .uri("/")
                .bodyValue(productoDto)
                .retrieve()
                .bodyToMono(ProductoDto.class);
    }

    // Actualizar producto (PUT)
    public Mono<ProductoDto> actualizarProducto(Long id, ProductoDto productoDto) {
        return productoWebClient.put()
                .uri("/{id}", id)
                .bodyValue(productoDto)
                .retrieve()
                .bodyToMono(ProductoDto.class);
    }

    // Eliminar producto (DELETE)
    public Mono<Void> eliminarProducto(Long id) {
        return productoWebClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
