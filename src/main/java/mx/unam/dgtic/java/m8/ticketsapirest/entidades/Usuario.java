package mx.unam.dgtic.java.m8.ticketsapirest.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 100)
    @Email
    private String email;
    @Column(nullable = false, length = 20)
    private String user;
    @Column(nullable = false, length = 20)
    private String clave;

}
