package com.smarttask;

import java.util.Scanner;

/**
 * Clase encargada de iniciar la ejecución de la aplicación.
 * 
 * Crea las instancias necesarias para el funcionamiento del programa,
 * como Scanner, GestorTareas y Menu, y luego invoca al método menu()
 * que controla la interacción con el usuario.
 * 
 * @author Valeria Oliva
 * @version 1.0
 */

public class Main {
    /**
     * Punto de entrada de la aplicación.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       
        GestorTareas gestor = new GestorTareas();
        
        Menu menu = new Menu(true, sc, gestor);

        menu.menu();

        sc.close();
    }
}