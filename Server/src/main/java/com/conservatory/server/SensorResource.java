package com.conservatory.server;

import Entidades.Sensor;
import com.conservatory.negocio.FiltroJWT;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.conservatory.logica.FachadaModelo;

import java.util.List;

@RestController
@RequestMapping("sensores")
public class SensorResource {
    FachadaModelo fm;
    FiltroJWT jwt = new FiltroJWT();

    public SensorResource() {
        fm= new FachadaModelo();
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<Sensor>> getSensores(@RequestHeader("auth")  String token) {
        System.out.println("entro a getsensores");
        jwt.validateToken(token);
        List<Sensor> sensores = fm.getSensores();
        if (sensores == null) {
            return ResponseEntity.notFound().build(); // devuelve 404 Not Found
        } else {
            System.out.println(sensores.get(0).getId());
            return ResponseEntity.ok(sensores); // devuelve 200 OK y el objeto Pedido
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Sensor> addSensor(@RequestBody Sensor s, @RequestHeader("auth")  String token){
        jwt.validateToken(token);
        fm.addSensor(s);
        return ResponseEntity.status(201).body(s);
    }

}
