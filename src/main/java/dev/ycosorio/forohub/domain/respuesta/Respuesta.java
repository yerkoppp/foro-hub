package dev.ycosorio.forohub.domain.respuesta;

import dev.ycosorio.forohub.domain.topico.Topico;
import dev.ycosorio.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Respuesta")
@Table(name = "respuestas")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private Boolean solucion = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario autor;

    public Respuesta(String mensaje, Topico topico, Usuario autor) {
        this.mensaje = mensaje;
        this.topico = topico;
        this.autor = autor;
    }
}
