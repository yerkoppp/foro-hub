package dev.ycosorio.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String tituloTopico,
        String mensaje,
        String autor,
        LocalDateTime fechaCreacion,
        Boolean solucion

) {
    public DatosDetalleRespuesta(Respuesta respuesta){
        this(
                respuesta.getId(),
                respuesta.getTopico().getTitulo(),
                respuesta.getMensaje(),
                respuesta.getAutor().getNombre(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion()
        );
    }

}
