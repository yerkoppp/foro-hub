package dev.ycosorio.forohub.controller;

import dev.ycosorio.forohub.domain.respuesta.DatosDetalleRespuesta;
import dev.ycosorio.forohub.domain.respuesta.DatosRegistroRespuesta;
import dev.ycosorio.forohub.domain.respuesta.RespuestaRepository;
import dev.ycosorio.forohub.domain.topico.TopicoRepository;
import dev.ycosorio.forohub.domain.respuesta.Respuesta;
import dev.ycosorio.forohub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(
            @RequestBody @Valid DatosRegistroRespuesta datos,
            Authentication authentication) {
        // Validamos primero si el tópico existe de verdad
        if (!topicoRepository.existsById(datos.idTopico())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: El tópico con ID " + datos.idTopico() + " no existe.");
        }
        var usuario = (Usuario) authentication.getPrincipal();
        var topico = topicoRepository.getReferenceById(datos.idTopico());

        var respuesta = new Respuesta(datos.mensaje(), topico, usuario);
        respuestaRepository.save(respuesta);

        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }
}
