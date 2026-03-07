package dev.ycosorio.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCrearTopico(

        @NotBlank (message = "titulo.obligatorio")
        String titulo,
        @NotBlank (message = "mensaje.obligatorio")
        String mensaje,
        @NotNull Long idCurso
) {}
