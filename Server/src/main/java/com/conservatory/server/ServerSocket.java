package com.conservatory.server;

import Entidades.Registro;
import Entidades.Sensor;
import com.conservatory.logica.DataHandler;
import com.conservatory.logica.FachadaModelo;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

public class ServerSocket {
    private int port;
    private DataHandler dataHandler;
    FachadaModelo fm = new FachadaModelo();

    public ServerSocket(int port) {
        this.port = port;
        this.dataHandler = new DataHandler();
    }

    public void start() throws IOException {
        System.out.println("Esperando datos: ");
        try (java.net.ServerSocket serverSocket = new java.net.ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        dataHandler.setAlarmas(fm.getAlarmas());
                        InputStream inputStream = socket.getInputStream();
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = inputStream.read(buffer)) != -1) {
                            String data = new String(buffer, 0, len);
                            //System.out.println("Datos recibidos: " + data);

                            String[] values = data.split(",");
                            float humedad = Float.parseFloat(values[0]);
                            float temperatura = Float.parseFloat(values[1]);
                            long fechaMillis = Long.parseLong(values[2]);
                            String sensorId = values[3];

                            ObjectId id = new ObjectId(sensorId);
                            Sensor sensor = fm.getSensor(id);

                            //Crear el objeto Registro
                            Registro registro = new Registro();
                            registro.setTemperatura(temperatura);
                            registro.setHumedad(humedad);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis(fechaMillis);
                            Date fecha = calendar.getTime();
                            registro.setFecha(fecha);
                            registro.setSensor(sensor);

                            System.out.println("Registro: temperatura: " + registro.getTemperatura() + ". Humedad: " + registro.getHumedad() + ". Sensor: " + registro.getSensor().getId());

                            dataHandler.handleData(registro);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

}
