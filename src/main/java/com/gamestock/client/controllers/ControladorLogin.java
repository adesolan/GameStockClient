package com.gamestock.client.controllers;

import com.gamestock.client.serveis.ServeiClient;
import com.gamestock.client.views.PantallaLogin;

import javax.swing.*;

/**
 * Controlador per a la pantalla de login de l'aplicació.
 * S'encarrega de gestionar la interacció de l'usuari amb la pantalla de login
 * i validar les credencials d'accés.
 */
public class ControladorLogin {
    private final PantallaLogin pantallaLogin;
    private final ServeiClient conexioServidor;

    /**
     * Constructor del controlador de login.
     * Inicialitza la pantalla de login i defineix el comportament en intentar fer login.
     */
    public ControladorLogin() {
        //Crea la pantalla per fer login
        pantallaLogin = new PantallaLogin();
        pantallaLogin.setContentPane(pantallaLogin.panelLogin);
        pantallaLogin.setTitle("Login");
        pantallaLogin.setSize(300, 250);
        pantallaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantallaLogin.setLocationRelativeTo(null); // Centra la finestra al centre de la pantalla
        
        //Obté la connexió amb el servidor
        this.conexioServidor = ServeiClient.getInstance();

        //Fins que el servidor no pugui autenticar usuaris, farem això:
        
        pantallaLogin.afegirAccioLogin(e -> {
               pantallaLogin.dispose();
                new ControladorPrincipal(false);
        });
        /*
        // Afegeix una acció que s'executa quan l'usuari intenta fer login
        pantallaLogin.afegirAccioLogin(e -> {
            String usuari = pantallaLogin.getUsuari();
            String contrasenya = pantallaLogin.getContrasenya();

            // Validar credencials amb el servidor
            Boolean[] resposta = conexioServidor.validarCredencials(usuari, contrasenya);
            boolean validat = resposta[0];
            boolean esAdministrador = resposta[1];
            
            if (validat) {
                // Si l'usuari i contrasenya són correctes, es tanca la pantalla de login
                pantallaLogin.dispose();
                // Es crea un nou controlador per a la pantalla principal
                new ControladorPrincipal(esAdministrador);
            } else {
                JOptionPane.showMessageDialog(pantallaLogin, "Usuari o contrasenya incorrectes");
            }
            
        });*/

        pantallaLogin.setVisible(true);
    }
}