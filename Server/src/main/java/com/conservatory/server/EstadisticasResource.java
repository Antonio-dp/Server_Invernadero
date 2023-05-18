package com.conservatory.server;

import Entidades.Registro;
import com.conservatory.logica.Estadisticas;
import com.conservatory.negocio.ExternalToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*End-point dedicado a exponer los
datos a sistemas de estadistica*/

@RestController
@RequestMapping("estadisticas")
public class EstadisticasResource {
    Estadisticas est;
    private ExternalToken tokenProvider = new ExternalToken();
    public EstadisticasResource() {
        est = new Estadisticas();
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<Registro>> getRegistros(HttpServletRequest request) {
        if (!verifyToken(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Devuelve 401 Unauthorized si el token no es válido
        }

        List<Registro> registros = est.getRegistros();
        if (registros == null) {
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found
        } else {
            return ResponseEntity.ok(registros); // Devuelve 200 OK y el objeto Pedido
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/temperatura-media")
    public ResponseEntity<Double> getTempMedia(HttpServletRequest request) {
        if (!verifyToken(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(est.calcularTempMedia());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/humedad-media")
    public ResponseEntity<Double> getHumMedia(HttpServletRequest request) {
        if (!verifyToken(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(est.calcularHumMedia());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/temperatura-desv")
    public ResponseEntity<Double> getDesvTemp(HttpServletRequest request) {
        if (!verifyToken(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(est.calcularDesvEstanTemp());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/humedad-desv")
    public ResponseEntity<Double> getDesvHum(HttpServletRequest request) {
        if (!verifyToken(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(est.calcularDesvEstanHum());
    }


    private boolean verifyToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println(authorizationHeader);

        if (authorizationHeader != null) {
            return tokenProvider.validateToken(authorizationHeader);
        }
        return false;
    }

}
