package com.gamestock.client;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.gamestock.client.controllers.ControladorLogin;
import com.gamestock.client.serveis.ServeiClient;

/**
 * Classe principal de l'aplicació. És el punt d'entrada de l'aplicació client.
 * Aquesta classe inicialitza la connexió amb el servidor i llança la finestra de login.
 */
public class App {

    /**
     * Mètode principal que s'executa quan s'inicia l'aplicació.
     * Inicialitza la connexió amb el servidor i mostra la finestra de login.
     *
     * @param args Arguments de la línia de comandes (no s'utilitzen en aquest cas).
     */
    public static void main(String[] args) {
        // Inicia el servei client amb la URL del servidor
        ServeiClient.getInstance("http://127.0.0.1:9090");
        
        // Inicia el controlador de login
        new ControladorLogin();
    }
}