/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tonyd
 */
public class Server implements Runnable{

    private int puerto;
    private ServerSocket servidor;
    private static Server server;

    private Server(int puerto) {
        this.puerto = puerto;
    }

    public static Server getInstance() {
        if (server == null) {
            server = new Server(9000);
        }
        return server;
    }

    @Override
    public void run() {
        Socket sc = null;
        try {
            servidor = new ServerSocket(puerto); //Se crea el servidor
            System.out.println("Servidor Iniciado");
            while (true) { //De esta forma el servidor siempre va a estar escuchando peticiones     
                sc = servidor.accept();
                new Thread().start();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void cerrarServerSocket() {
        try {
            if (servidor != null) {
                servidor.close();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(Server.getInstance()).start();
    }

}
