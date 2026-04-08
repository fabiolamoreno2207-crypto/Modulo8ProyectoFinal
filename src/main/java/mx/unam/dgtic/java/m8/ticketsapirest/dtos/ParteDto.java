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
public class ParteDto {

    @Id
    private Long id_parte;
    @NotNull
    @NotBlank
    private String sku;
    private String descripcion;
    private String ubicacion;
    private Integer stock;
}
