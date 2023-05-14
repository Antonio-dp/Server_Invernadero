package com.conservatory.logica;

import Entidades.Alarma;

public class NotificationSender {

    public void activarAlarma(Alarma alarma){
        System.out.println("PELIGRO: " + alarma.getTipo());
    }
}
