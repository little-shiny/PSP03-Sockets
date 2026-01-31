package com.fpvirtualaragon.actividad31;

import java.io.*;
import java.net.*;
import java.util.Random;

public class ServidorAdivina {
    public static void main(String[] args) {
        try {
            // Abrimos el puerto 2000 para que el cliente se conecte
            ServerSocket socketServidor = new ServerSocket(2000);
            System.out.println("Esperando a que alguien se conecte para jugar...");

            Socket miCliente = socketServidor.accept();

            // Generamos el número secreto aquí para que no cambie durante la partida
            int secreto = new Random().nextInt(101);
            System.out.println("El número que tienen que adivinar es: " + secreto);

            // Canales para leer y escribir datos
            DataInputStream flujoEntrada = new DataInputStream(miCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(miCliente.getOutputStream());

            int numeroRecibido = -1;

            // El bucle sigue hasta que el cliente acierte
            while (numeroRecibido != secreto) {
                numeroRecibido = flujoEntrada.readInt();

                if (numeroRecibido < secreto) {
                    flujoSalida.writeUTF("Te has quedado corto, el número es mayor.");
                } else if (numeroRecibido > secreto) {
                    flujoSalida.writeUTF("Te has pasado, el número es menor.");
                } else {
                    flujoSalida.writeUTF("Ese era el número!!!!");
                }
            }

            // Cerramos al terminar
            miCliente.close();
            socketServidor.close();

        } catch (Exception e) {
            System.out.println("Ha habido un fallo en el servidor: " + e.getMessage());
        }
    }
}