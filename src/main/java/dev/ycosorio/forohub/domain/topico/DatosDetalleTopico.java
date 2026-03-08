package dev.ycosorio.forohub.domain.topico;

import dev.ycosorio.forohub.domain.curso.DatosDetalleCurso;
import dev.ycosorio.forohub.domain.respuesta.DatosDetalleRespuesta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado status,
        String autor,
        DatosDetalleCurso curso,
        List<DatosDetalleRespuesta> respuestas
) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                new DatosDetalleCurso(topico.getCurso()),
                topico.getRespuestas().stream()
                        .map(DatosDetalleRespuesta::new)
                        .toList()
        );
    }
}
