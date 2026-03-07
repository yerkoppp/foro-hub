package dev.ycosorio.forohub.controller;

import dev.ycosorio.forohub.domain.usuario.DatosRegistroUsuario;
import dev.ycosorio.forohub.domain.usuario.Usuario;
import dev.ycosorio.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrar")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroUsuario datos) {
        var contrasenaEncriptada = passwordEncoder.encode(datos.contrasena());

        var usuario = new Usuario(datos.nombre(), datos.email(), contrasenaEncriptada);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
