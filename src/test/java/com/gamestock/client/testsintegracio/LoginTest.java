package com.gamestock.client.testsintegracio;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.gamestock.client.controllers.ControladorLogin;
import com.gamestock.client.serveis.ServeiClient;
import com.gamestock.client.views.PantallaLogin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Classe per realitzar proves d'integració del procés de login.
 * Aquesta classe necessita que el servidor estigui obert
 */
public class LoginTest {
    private ControladorLogin controladorLogin;
    private PantallaLogin pantallaLogin;
    private ServeiClient conexioServidor;
    
    /**
     * Configura el controlador, la pantalla de login i la connexió amb el servidor abans de cada prova.
     */
    @BeforeEach
    void setUp() {
        conexioServidor = ServeiClient.getInstance("http://127.0.0.1:9090");
        controladorLogin = new ControladorLogin();
        pantallaLogin = controladorLogin.getPantallaLogin();
    }
    
    /**
     * Prova que el mètode login funciona correctament quan es proporcionen les credencials correctes.
     * Aquesta prova fa un login vàlid amb l'usuari "avery" i la contrasenya "avery".
     */
    @Test
    void testLogin() {
        pantallaLogin.getTextUsuari().setText("avery");
        pantallaLogin.getTextContrasenya().setText("avery");
        pantallaLogin.getBotoLogin().doClick();
        assert(controladorLogin.isValidat());
    }
}
