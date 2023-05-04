package com.conservatory.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sensores")
public class SensorResource {

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<String> getSensores() {
        return ResponseEntity.ok("get-test"); // devuelve 200 OK
    }

}