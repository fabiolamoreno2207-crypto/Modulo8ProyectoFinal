package mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2;

import mx.unam.dgtic.java.m8.ticketsapirest.dtos.ParteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientParteDtoService {
    @Autowired
    @Qualifier("parteWebClient")
    private WebClient parteWebClient;

    // Obtener todas las partes
    public Flux<ParteDto> getTodasVista() {
        return parteWebClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(ParteDto.class);
    }

    // Obtener una parte por ID
    public Mono<ParteDto> getParteById(Long id) {
        return parteWebClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(ParteDto.class);
    }

    // Guardar nueva parte
    public Mono<ParteDto> guardarParte(ParteDto parteDto) {
        return parteWebClient.post()
                .uri("/")
                .bodyValue(parteDto)
                .retrieve()
                .bodyToMono(ParteDto.class);
    }

    // Actualizar parte (PUT)
    public Mono<ParteDto> actualizarParte(Long id, ParteDto parteDto) {
        return parteWebClient.put()
                .uri("/{id}", id)
                .bodyValue(parteDto)
                .retrieve()
                .bodyToMono(ParteDto.class);
    }

    // Eliminar parte (DELETE)
    public Mono<Void> eliminarParte(Long id) {
        return parteWebClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
