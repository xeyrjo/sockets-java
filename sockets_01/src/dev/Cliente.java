package dev;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
	
    public static void main(String[] args) {

    	// Dirección IP o nombre de host del servidor
        final String HOST = "127.0.0.1";
     // Puerto en el que el servidor escucha
        final int PUERTO = 5000;

        try {
            //Creo el socket para conectarme con el cliente
            Socket clienteSocket = new Socket(HOST, PUERTO);

            // Flujo de entrada para recibir datos del servidor
            DataInputStream entrada = new DataInputStream(clienteSocket.getInputStream());
            
            // Flujo de salida para enviar datos al servidor
            DataOutputStream salida = new DataOutputStream(clienteSocket.getOutputStream());

            // Enviar mensaje al servidor
            salida.writeUTF("¡Hola mundo desde el cliente!");

            // Leer respuesta del servidor
            String mensaje = entrada.readUTF();

            System.out.println("Servidor dice: " + mensaje);
            
            // Cerrar conexión
            clienteSocket.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
