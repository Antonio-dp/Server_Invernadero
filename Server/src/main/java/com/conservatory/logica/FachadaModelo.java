package com.conservatory.logica;

import DAOS.RegistrosDAO;
import DAOS.SensorDAO;
import Entidades.Registro;
import Entidades.Sensor;
import conexiones.ConexionBD;
import interfaces.IRegistrosDAO;
import interfaces.ISensorDAO;

import java.util.List;

public class FachadaModelo {
    ConexionBD conn = new ConexionBD();
    ISensorDAO mSensor;
    IRegistrosDAO mRegistro;
    public FachadaModelo() {
        this.mSensor = new SensorDAO(conn);
        this.mRegistro = new RegistrosDAO(conn);
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

    public void addRegistro(Registro r){
        mRegistro.agregarRegistro(r);
    }

    public List<Registro> getRegistros(){
        return mRegistro.consultarTodos();
    }

}
