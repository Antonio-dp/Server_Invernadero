package com.conservatory.server;

import Entidades.Sensor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.conservatory.logica.FachadaModelo;

import java.util.List;

@RestController
@RequestMapping("sensores")
public class SensorResource {
    FachadaModelo fm;

    public SensorResource() {
        fm= new FachadaModelo();
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<Sensor>> getSensores() {
        List<Sensor> sensores = fm.getSensores();
        if (sensores == null) {
            return ResponseEntity.notFound().build(); // devuelve 404 Not Found
        } else {
            System.out.println(sensores.get(0).getId());
            return ResponseEntity.ok(sensores); // devuelve 200 OK y el objeto Pedido
        }
    }

    /*@CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorID(@PathVariable("id") Integer id) {
        Cliente c = fm.getClienteById(id);
        if (c == null) {
            return ResponseEntity.notFound().build(); // devuelve 404 Not Found
        } else {
            return ResponseEntity.ok(c); // devuelve 200 OK y el objeto Pedido
        }
    }*/

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Sensor> addSensor(@RequestBody Sensor s){
        fm.addSensor(s);
        return ResponseEntity.status(201).body(s);
    }

    /*@CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deleteSensor(@PathVariable("id") int id) {
        Cliente c = fm.getClienteById(id);
        if (c == null) {
            return ResponseEntity.notFound().build(); // devuelve 404 Not Found
        } else {
            fm.deleteCliente(c);
            return ResponseEntity.noContent()   .build(); //
        }
    }*/

    @CrossOrigin(origins = "*")
    @PutMapping
    public ResponseEntity<Sensor> updateSensor(@RequestBody Sensor sensor) {
        /*Cliente c = fm.getClienteById(cliente.getId());
        if (c == null) {
            return ResponseEntity.notFound().build(); // devuelve 404 Not Found
        } else {
            fm.updateCliente(cliente);
            return ResponseEntity.noContent().build(); //
        }*/
        System.out.println(sensor.getId());

        return ResponseEntity.noContent().build();
    }
}
