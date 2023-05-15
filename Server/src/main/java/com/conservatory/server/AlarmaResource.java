package com.conservatory.server;

import Entidades.Alarma;
import com.conservatory.logica.FachadaModelo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alarmas")
public class AlarmaResource {
    FachadaModelo fm;

    public AlarmaResource() {
        this.fm = new FachadaModelo();
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<Alarma>> getAlarmas() {
        List<Alarma> alarmas = fm.getAlarmas();
        if (alarmas == null) {
            return ResponseEntity.notFound().build(); // devuelve 404 Not Found
        } else {
            return ResponseEntity.ok(alarmas); // devuelve 200 OK y el objeto Pedido
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Alarma> addAlarma(@RequestBody Alarma a){
        fm.addAlarma(a);
        return ResponseEntity.status(201).body(a);
    }

}
