package mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2;

import mx.unam.dgtic.java.m8.ticketsapirest.dtos.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientUsuarioDtoService {
    @Autowired
    private WebClient webClient;

    // Obtener todos los usuarios
    public Flux<UsuarioDto> getTodosVista() {
        return webClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(UsuarioDto.class);
    }

    // Obtener un usuario por ID
    public Mono<UsuarioDto> getUsuarioById(Long id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(UsuarioDto.class);
    }

    // Guardar nuevo usuario
    public Mono<UsuarioDto> guardarUsuario(UsuarioDto usuarioDto) {
        return webClient.post()
                .uri("/")
                .bodyValue(usuarioDto)
                .retrieve()
                .bodyToMono(UsuarioDto.class);
    }

    // Actualizar usuario (PUT)
    public Mono<UsuarioDto> actualizarUsuario(Long id, UsuarioDto usuarioDto) {
        return webClient.put()
                .uri("/{id}", id)
                .bodyValue(usuarioDto)
                .retrieve()
                .bodyToMono(UsuarioDto.class);
    }

    // Eliminar usuario (DELETE)
    public Mono<Void> eliminarUsuario(Long id) {
        return webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
