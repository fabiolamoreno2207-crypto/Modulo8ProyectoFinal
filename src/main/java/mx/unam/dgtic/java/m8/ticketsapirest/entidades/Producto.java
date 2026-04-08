package mx.unam.dgtic.java.m8.ticketsapirest.entidades;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "partes")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;
    @Column(nullable = false, length = 20)
    private String sku;
    @Column(nullable = false, length = 100)
    private String nombre;
    private String serie;
    private String ubicacion;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Parte> partes;

    @ManyToMany(mappedBy = "productos")
    private List<Ticket> tickets;

}
