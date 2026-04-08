package mx.unam.dgtic.java.m8.ticketsapirest.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioDto {

    private Long id;
    @NotNull(message = "No puede quedar nulo")
    @NotBlank(message = "no puede ser cadena vacía")
    @Size(min=3, message = "Debe tener al menos 3 caracteres")
    private String nombre;

    @Email
    @NotNull(message = "No puede quedar nulo")
    private String email;

    @NotBlank(message = "No puede estar en blanco el usuario")
    @Size(message = "El usuario debe ser de al menos 5 caracteres", min = 5)
    private String user;

    @NotBlank(message = "No puede estar en blanco la clave")
    @Size(message = "La clave debe ser de al menos 4 caracteres", min = 4)
    private String clave;

}
