package dev.ycosorio.forohub.domain.topico;

public record DatosListaTopicos(
        Long id,
        String titulo,
        String mensaje,
        Estado status,
        String curso
){


public DatosListaTopicos(Topico topico) {
        this(
        topico.getId(),
        topico.getTitulo(),
        topico.getMensaje(),
        topico.getStatus(),
        topico.getCurso().getNombre()
        );
}

}
