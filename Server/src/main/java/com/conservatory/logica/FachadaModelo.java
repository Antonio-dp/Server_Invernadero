package com.conservatory.logica;

import DAOS.SensorDAO;
import Entidades.Sensor;
import conexiones.ConexionBD;
import interfaces.ISensorDAO;

import java.util.List;

public class FachadaModelo {
    ConexionBD conn = new ConexionBD();
    ISensorDAO mSensor;
    public FachadaModelo() {
        this.mSensor = new SensorDAO(conn);
    }

    public List<Sensor> getSensores(){
        return mSensor.consultarTodos();
    }

    public void addSensor(Sensor s){
        mSensor.agregarSensor(s);
    }

    public void updateSensor(Sensor s){
        mSensor.actualizarSensor(s);
    }

}
