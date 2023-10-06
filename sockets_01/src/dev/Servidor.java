package dev;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
	
    public static void main(String[] args) {

        //puerto de nuestro servidor
        final int PUERTO = 5000;

        try {
        	// Puerto en el que el servidor escuchará
        	ServerSocket servidorSocket = new ServerSocket(PUERTO);
        	System.out.println("Servidor esperando conexiones en el puerto " + PUERTO);

            //Siempre estara escuchando peticiones
            while (true) {

            	// Esperar a que un cliente se conecte
            	Socket clienteSocket = servidorSocket.accept();
                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress());
                
                // Flujo de entrada para recibir datos del cliente
                DataInputStream in = new DataInputStream(clienteSocket.getInputStream());
                
                // Flujo de salida para enviar datos al cliente
                DataOutputStream out = new DataOutputStream(clienteSocket.getOutputStream());

                // Leer datos del cliente
                String mensaje = in.readUTF();
                System.out.println("Cliente dice: " + mensaje);

                // Enviar respuesta al cliente
                out.writeUTF("¡Hola mundo desde el servidor!");

                // Cerrar conexiones
//                servidorSocket.close();
                clienteSocket.close();
                System.out.println("Cliente desconectado");

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
