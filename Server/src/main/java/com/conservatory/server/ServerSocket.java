package com.conservatory.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerSocket {
    private int port;

    public ServerSocket(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        System.out.println("Esperando datos: ");
        try (java.net.ServerSocket serverSocket = new java.net.ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = inputStream.read(buffer)) != -1) {
                            String data = new String(buffer, 0, len);
                            System.out.println("Datos recibidos: " + data);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

}
