package com.conservatory.negocio;

import io.jsonwebtoken.Jwts;

public class ExternalToken {
    private String jwtSecret = "xhk1Elgzk92luHIBsx83fOJunMw+cSHW6FxpvCoHb5NlKWLl49wBIg9sYBesElpNmUPZEFzy9bqY3S8UWmSVUg==";

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Manejar la excepción en caso de token inválido
        }
        return false;
    }
}
