package com.conservatory.server;

import Entidades.Registro;
import com.conservatory.logica.FachadaModelo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*End-point dedicado a exponer los
datos a sistemas de estadistica*/


@RestController
@RequestMapping("estadisticas")
public class EstadisticasResource {
    FachadaModelo fm;

    public EstadisticasResource() {
        fm = new FachadaModelo();
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<Registro>> getRegistros() {
        List<Registro> registros = fm.getRegistros();
        if (registros == null) {
            return ResponseEntity.notFound().build(); // devuelve 404 Not Found
        } else {
            return ResponseEntity.ok(registros); // devuelve 200 OK y el objeto Pedido
        }
    }

}
