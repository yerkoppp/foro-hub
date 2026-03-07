package dev.ycosorio.forohub.domain.topico;

import dev.ycosorio.forohub.domain.curso.DatosDetalleCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado status,
        Long idAutor,
        DatosDetalleCurso curso // <-- Aquí está el cambio "fluido"
) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getId(),
                new DatosDetalleCurso(topico.getCurso())
        );
    }
}
