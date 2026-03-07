package dev.ycosorio.forohub.controller;

import dev.ycosorio.forohub.domain.curso.CursoRepository;
import dev.ycosorio.forohub.domain.respuesta.DatosDetalleRespuesta;
import dev.ycosorio.forohub.domain.respuesta.RespuestaRepository;
import dev.ycosorio.forohub.domain.topico.*;
import dev.ycosorio.forohub.domain.usuario.Usuario;
import dev.ycosorio.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;


    @Transactional
    @PostMapping
    public ResponseEntity registrar(
            @RequestBody @Valid DatosCrearTopico datos,
            UriComponentsBuilder uriComponentsBuilder,
            Authentication authentication){

        var autor = (Usuario) authentication.getPrincipal();
        var curso = cursoRepository.getReferenceById(datos.idCurso());

        var topico = new Topico(datos, autor, curso);
        topicoRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity verTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopicos>> listar(
            @PageableDefault(size = 3, sort= {"titulo"})
            Pageable paginacion
    ){
        var page = topicoRepository.findAll(paginacion).map(DatosListaTopicos::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        var topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatos(datos);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id){
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        topicoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/respuestas")
    public ResponseEntity<List<DatosDetalleRespuesta>> listarRespuestasPorTopico(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        var topico = topicoRepository.getReferenceById(id);
        var respuestas = topico.getRespuestas().stream()
                .map(DatosDetalleRespuesta::new)
                .toList();

        return ResponseEntity.ok(respuestas);
    }





}
