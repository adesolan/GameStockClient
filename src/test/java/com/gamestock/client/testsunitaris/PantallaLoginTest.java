package com.gamestock.client.testsunitaris;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.gamestock.client.controllers.ControladorLogin;
import com.gamestock.client.serveis.ServeiClient;
import com.gamestock.client.views.PantallaLogin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Proves unitàries per a la classe PantallaLogin.
 * Aquesta classe comprova el comportament dels mètodes de la pantalla de login.
 */
class PantallaLoginTest {
    private ControladorLogin controladorLogin;
    private PantallaLogin pantallaLogin;
    private ServeiClient conexioServidor;
    
    /**
     * Configura els components necessaris per les proves abans de cada mètode de prova.
     * S'inicialitzen les instàncies de ServeiClient, ControladorLogin i PantallaLogin.
     */
    @BeforeEach
    void setUp() {
        conexioServidor = ServeiClient.getInstance("http://127.0.0.1:9090");
        controladorLogin = new ControladorLogin();
        pantallaLogin = controladorLogin.getPantallaLogin();
    }

    /**
     * Prova el mètode getUsuari per assegurar-se que retorna el nom d'usuari correcte.
     */
    @Test
    void testGetUsuari() {
        pantallaLogin.getTextUsuari().setText("user123");
        assertEquals("user123", pantallaLogin.getUsuari());
    }

    /**
     * Prova el mètode getContrasenya per assegurar-se que retorna la contrasenya correcta.
     */
    @Test
    void testGetContrasenya() {
        pantallaLogin.getTextContrasenya().setText("password");
        assertEquals("password", pantallaLogin.getContrasenya());
    }

    /**
     * Prova que el botó de login existeix i que el seu text és "Login".
     */
    @Test
    void testButtonLoginExists() {
        JButton botoLogin = pantallaLogin.getBotoLogin();
        assertNotNull(botoLogin);
        assertEquals("Login", botoLogin.getText());
    }
    
    
        
}