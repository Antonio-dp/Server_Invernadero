package com.conservatory.logica;

import Entidades.Registro;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.List;

public class Estadisticas {
    DescriptiveStatistics tempStats;
    DescriptiveStatistics humStats;
    List<Registro> registros;
    FachadaModelo fm = new FachadaModelo();

    public Estadisticas() {
        this.humStats = new DescriptiveStatistics();
        this.tempStats = new DescriptiveStatistics();
        registros = fm.getRegistros();
        this.obtenerDatosHumedad();
        this.obtenerDatosTemperatura();
    }

    public double calcularTempMedia() {
        return tempStats.getMean();
    }

    public double calcularDesvEstanTemp() {
        return tempStats.getStandardDeviation();
    }

    public double calcularHumMedia() {
        return humStats.getMean();
    }

    public double calcularDesvEstanHum() {
        return humStats.getStandardDeviation();
    }

    private void obtenerDatosTemperatura() {
        for (Registro registro : registros) {
            tempStats.addValue(registro.getTemperatura());
        }
    }

    private void obtenerDatosHumedad() {
        for (Registro registro : registros) {
            tempStats.addValue(registro.getHumedad());
        }
    }

}
