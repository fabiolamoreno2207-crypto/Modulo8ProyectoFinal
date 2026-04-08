package mx.unam.dgtic.java.m8.ticketsapirest.entidades;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "parte")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "producto")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Parte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_parte;
    @Column(nullable = false, length = 20)
    private String sku;
    @Column(nullable = false, length = 100)
    private String descripcion;
    private String ubicacion;
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonBackReference
    private Producto producto;

}

