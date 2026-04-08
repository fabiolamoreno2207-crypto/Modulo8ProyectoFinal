package mx.unam.dgtic.java.m8.ticketsapirest.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Bean
    public WebClient usuarioWebClient(){
        //URL BASE del API REST Usuario
        String urlBase ="http://localhost:8080/api/v2/usuario";
        return WebClient.builder()
                .baseUrl(urlBase)
                .build();
    }

    @Bean
    public WebClient parteWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080/api/v2/parte")
                .build();
    }

    @Bean
    public WebClient productoWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080/api/v2/producto")
                .build();
    }
}
