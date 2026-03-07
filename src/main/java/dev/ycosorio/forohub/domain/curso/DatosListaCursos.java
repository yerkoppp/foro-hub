package dev.ycosorio.forohub.domain.curso;

public record DatosListaCursos(
        Long id,
        String nombre,
        String categoria) {
    public DatosListaCursos(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria().toString());
    }
}
