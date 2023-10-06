package dev;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
	
    public static void main(String[] args) {

    	// Puerto en el que el servidor escucha
        final int PUERTO_SERVIDOR = 5000;
        //buffer donde se almacenara los mensajes
        byte[] buffer = new byte[1024];

        try {
            // Obtengo la localizacion de localhost
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            // Creo el socket de UDP
            DatagramSocket clienteSocket = new DatagramSocket();

            String mensaje = "¡Hola mundo desde el cliente UDP!";

            // Convierto el mensaje a bytes
            buffer = mensaje.getBytes();

            // Creo un datagrama para enviar al servidor
            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);

            // Lo envio con send - Enviar el paquete al servidor
            System.out.println("Envio el datagrama");
            clienteSocket.send(pregunta);

            // Preparo la respuesta
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

            // Recibo la respuesta del servidor
            clienteSocket.receive(peticion);
            System.out.println("Recibo la peticion");

            // Recibo los datos y lo muestro
            mensaje = new String(peticion.getData());
            System.out.println("Servidor dice: " + mensaje);

            //cierro el socket
            clienteSocket.close();

        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
