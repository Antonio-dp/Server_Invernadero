package com.conservatory.server;

import Entidades.Usuario;
import com.conservatory.negocio.FiltroJWT;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UsuarioResource {
    FiltroJWT jwt = new FiltroJWT();
    public UsuarioResource() {}
    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Usuario> addUsuario(@RequestBody String correo, HttpServletResponse response){
        String token = jwt.generateToken(correo);
        return ResponseEntity.status(200).header("auth", token).build();
    }

}
