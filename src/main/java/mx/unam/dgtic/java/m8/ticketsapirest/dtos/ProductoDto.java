package mx.unam.dgtic.java.m8.ticketsapirest.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductoDto {

    @Id
    private Long id_producto;

    @NotNull
    @NotBlank
    private String sku;
    @NotNull
    @NotBlank
    private String nombre;
    @NotNull
    @NotBlank
    private String serie;
    private String ubicacion;
}
