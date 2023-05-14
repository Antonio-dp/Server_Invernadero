package com.conservatory.server;

import Entidades.Registro;
import com.conservatory.logica.DataHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerSocket {
    private int port;
    private DataHandler dataHandler;

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
                        InputStream inputStream = socket.getInputStream();
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                        Registro registro = (Registro) objectInputStream.readObject();
                        System.out.println("Registro recibido: " + registro);

                        dataHandler.handleData(registro);

                        objectInputStream.close();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

}
