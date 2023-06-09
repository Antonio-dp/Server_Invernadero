package com.conservatory.logica;

import DAOS.AlarmaDAO;
import DAOS.RegistrosDAO;
import DAOS.SensorDAO;
import DAOS.UsuariosDAO;
import Entidades.Alarma;
import Entidades.Registro;
import Entidades.Sensor;
import conexiones.ConexionBD;
import interfaces.IAlarmaDAO;
import interfaces.IRegistrosDAO;
import interfaces.ISensorDAO;
import interfaces.IUsuariosDAO;
import org.bson.types.ObjectId;

import java.util.List;

public class FachadaModelo {
    ConexionBD conn = new ConexionBD();
    ISensorDAO mSensor;
    IRegistrosDAO mRegistro;
    IAlarmaDAO mAlarma;

    IUsuariosDAO mUsuario;
    public FachadaModelo() {
        this.mSensor = new SensorDAO(conn);
        this.mRegistro = new RegistrosDAO(conn);
        this.mAlarma = new AlarmaDAO(conn);
        this.mUsuario = new UsuariosDAO(conn);
    }

    public List<Sensor> getSensores(){
        return mSensor.consultarTodos();
    }

    public Sensor getSensor(ObjectId id){
        return mSensor.consultarSensor(id);
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

    public List<Alarma> getAlarmas(){
        return mAlarma.consultarTodos();
    }

    public void addAlarma(Alarma alarma){
        mAlarma.agregarAlarma(alarma);
    }

}
