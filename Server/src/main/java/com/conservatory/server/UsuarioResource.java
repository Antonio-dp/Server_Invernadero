package com.conservatory.server;

import Entidades.Alarma;
import Entidades.Usuario;
import com.conservatory.filtrojwt.FiltroJWT;
import com.conservatory.logica.FachadaModelo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UsuarioResource {
    FiltroJWT jwt = new FiltroJWT();
    public UsuarioResource() {}
    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario a){
        String token = jwt.generateToken(a.getNombre());
        return ResponseEntity.status(200).header("auth", token).build();
    }
}