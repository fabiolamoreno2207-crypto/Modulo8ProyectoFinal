package mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2;

import mx.unam.dgtic.java.m8.ticketsapirest.dtos.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientUsuarioDtoService {
    @Autowired
    @Qualifier("usuarioWebClient")
    private WebClient usuarioWebClient;


    // Obtener todos los usuarios
    public Flux<UsuarioDto> getTodosVista() {
        return usuarioWebClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(UsuarioDto.class);
    }

    // Obtener un usuario por ID
    public Mono<UsuarioDto> getUsuarioById(Long id) {
        return usuarioWebClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(UsuarioDto.class);
    }

    // Guardar nuevo usuario
    public Mono<UsuarioDto> guardarUsuario(UsuarioDto usuarioDto) {
        return usuarioWebClient.post()
                .uri("/")
                .bodyValue(usuarioDto)
                .retrieve()
                .bodyToMono(UsuarioDto.class);
    }

    // Actualizar usuario (PUT)
    public Mono<UsuarioDto> actualizarUsuario(Long id, UsuarioDto usuarioDto) {
        return usuarioWebClient.put()
                .uri("/{id}", id)
                .bodyValue(usuarioDto)
                .retrieve()
                .bodyToMono(UsuarioDto.class);
    }

    // Eliminar usuario (DELETE)
    public Mono<Void> eliminarUsuario(Long id) {
        return usuarioWebClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
