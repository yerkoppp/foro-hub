package dev.ycosorio.forohub.controller;

import dev.ycosorio.forohub.domain.curso.CursoRepository;
import dev.ycosorio.forohub.domain.curso.DatosListaCursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<Page<DatosListaCursos>> listarCursos(
            @PageableDefault(size = 10, sort = "categoria") Pageable paginacion) {
        var pagina = cursoRepository.findAll(paginacion).map(DatosListaCursos::new);
        return ResponseEntity.ok(pagina);
    }
}