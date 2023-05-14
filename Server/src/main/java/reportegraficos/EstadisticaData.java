/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportegraficos;

import Entidades.Registro;
import com.conservatory.logica.FachadaModelo;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author tonyd
 */
public class EstadisticaData {

    DescriptiveStatistics tempStats;
    DescriptiveStatistics humStats;
    List<Registro> registros;
    FachadaModelo fm = new FachadaModelo();

    public EstadisticaData() {
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
