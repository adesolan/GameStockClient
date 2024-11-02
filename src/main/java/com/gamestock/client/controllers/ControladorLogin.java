package com.gamestock.client.controllers;

import com.gamestock.client.views.PantallaLogin;

import javax.swing.*;

/**
 * Controlador per a la pantalla de login de l'aplicació.
 * S'encarrega de gestionar la interacció de l'usuari amb la pantalla de login
 * i validar les credencials d'accés.
 */
public class ControladorLogin {
    private final PantallaLogin pantallaLogin;

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

        // Afegeix una acció que s'executa quan l'usuari intenta fer login
        pantallaLogin.afegirAccioLogin(_ -> {
            String usuari = pantallaLogin.getUsuari();
            String contrasenya = pantallaLogin.getContrasenya();

            // Comprova si les credencials són correctes, son de prova fins que no pogui accedir al servidor
            if (usuari.equals("admin") && contrasenya.equals("1234")) {
                // Si l'usuari i contrasenya són correctes, es tanca la pantalla de login
                pantallaLogin.dispose();
                // Es crea un nou controlador per a la pantalla principal
                new ControladorPrincipal();
            } else {
                JOptionPane.showMessageDialog(pantallaLogin, "Usuari o contrasenya incorrectes");
            }
        });

        pantallaLogin.setVisible(true);
    }
}