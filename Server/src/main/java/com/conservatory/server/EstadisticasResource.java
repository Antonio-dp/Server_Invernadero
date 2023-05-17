package com.conservatory.server;

import Entidades.Registro;
import com.conservatory.logica.Estadisticas;
import com.conservatory.logica.FachadaModelo;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*End-point dedicado a exponer los
datos a sistemas de estadistica*/

@RestController
@RequestMapping("estadisticas")
public class EstadisticasResource {
    Estadisticas est;

    public EstadisticasResource() {
        est = new Estadisticas();
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<Registro>> getRegistros() {
        List<Registro> registros = est.getRegistros();
        if (registros == null) {
            return ResponseEntity.notFound().build(); // devuelve 404 Not Found
        } else {
            return ResponseEntity.ok(registros); // devuelve 200 OK y el objeto Pedido
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/temperatura-media")
    public ResponseEntity<Double> getTempMedia() {
        return ResponseEntity.ok(est.calcularTempMedia());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/humedad-media")
    public ResponseEntity<Double> getHumMedia() {
        return ResponseEntity.ok(est.calcularHumMedia());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/temperatura-desv")
    public ResponseEntity<Double> getDesvTemp() {
        return ResponseEntity.ok(est.calcularDesvEstanTemp());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/humedad-desv")
    public ResponseEntity<Double> getDesvHum() {
        return ResponseEntity.ok(est.calcularDesvEstanHum());
    }

}
