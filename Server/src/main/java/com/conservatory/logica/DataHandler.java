package com.conservatory.logica;

import Entidades.Alarma;
import Entidades.Registro;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private Registro threshold;
    private NotificationSender notificaciones;
    List<Alarma> alarmas;
    FachadaModelo fm;

    public DataHandler(){
        this.fm= new FachadaModelo();
        this.alarmas = new ArrayList<>();
        this.notificaciones = new NotificationSender();
    }

    public void setAlarmas(List<Alarma> alarmas){
        this.alarmas = alarmas;
    }

    public void handleData(Registro r) {
        System.out.println("Entro al data handler");
        for (Alarma alarma : alarmas) {
            if(alarma.getSensor().equals(r.getSensor().getId())){
                switch(alarma.getTipo()){
                    case "HUMEDAD":
                        System.out.println("entro a humedad");
                        System.out.println("humedad: " + r.getHumedad());
                        System.out.println("limites: " + alarma.getLimiteInferior() + "-" + alarma.getLimiteSuperior());

                        if (r.getHumedad() < alarma.getLimiteInferior() || r.getHumedad() > alarma.getLimiteSuperior()) {
                            notificaciones.activarAlarma(alarma);
                        }
                        break;
                    case "TEMPERATURA":
                        if (r.getTemperatura() < alarma.getLimiteInferior() || r.getTemperatura() > alarma.getLimiteSuperior()) {
                            notificaciones.activarAlarma(alarma);
                        }
                        break;
                }
            }
        }
        //nuevoRegistro(r);
    }

    private void nuevoRegistro(Registro r){
        fm.addRegistro(r);
    }

}
