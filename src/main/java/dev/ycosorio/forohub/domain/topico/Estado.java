package dev.ycosorio.forohub.domain.topico;

public enum Estado {
    NO_RESPONDIDO, //El tópico recién se creó y no tiene comentarios.

    NO_SOLUCIONADO, //El tópico ya tiene respuestas de otros usuarios, pero el autor aún no ha marcado ninguna como la solución definitiva.

    SOLUCIONADO, //El autor del tópico seleccionó una de las respuestas como la correcta o definitiva.

    CERRADO //El tópico fue bloqueado y ya no admite nuevas respuestas (ya sea por un moderador o por inactividad).

}
