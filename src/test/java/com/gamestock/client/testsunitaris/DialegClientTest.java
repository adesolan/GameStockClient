package com.gamestock.client.testsunitaris;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.gamestock.client.models.Client;
import com.gamestock.client.views.DialegClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Proves unitàries per a la classe DialegClient.
 * Aquesta classe comprova el comportament de les operacions que es poden realitzar
 * a la finestra de diàleg de client
 */
class DialegClientTest {
    private DialegClient dialog;

    /**
     * Configura el diàleg abans de cada prova.
     * S'inicialitza un objecte **DialegClient** amb un **Frame** buit i el títol "Test Dialog".
     */
    @BeforeEach
    void setUp() {
        dialog = new DialegClient(new Frame(), "Test Dialog");
    }

    /**
     * Prova la creació d'un client amb dades vàlides.
     * Aquesta prova omple els camps de text del diàleg amb dades vàlides i comprova que el client
     * creat és correcte amb les dades introduïdes.
     */
    @Test
    void testConfirmarAmbDadesValides() {
        dialog.getTextCodi().setText("1");
        dialog.getTextNom().setText("Nom");
        dialog.getTextCognom1().setText("Cognom1");
        dialog.getTextCognom2().setText("Cognom2");
        dialog.getTextEmail().setText("test@example.com");
        dialog.getTextTelefon().setText("123456789");

        dialog.confirmar();

        Client client = dialog.getClient();
        assertNotNull(client);
        assertEquals(1, client.getId());
        assertEquals("Nom", client.getNom());
        assertEquals("Cognom1", client.getCognom1());
        assertEquals("Cognom2", client.getCognom2());
        assertEquals("test@example.com", client.getEmail());
        assertEquals("123456789", client.getTelefon());
    }

    /**
     * Prova la creació d'un client amb dades invàlides.
     * Aquesta prova omple el camp de codi amb un valor no numèric i comprova que no es crea un client
     * en aquest cas.
     */
    @Test
    void testConfirmarAmbDadesInvalides() {
        dialog.getTextCodi().setText("abc");
        dialog.confirmar();

        assertNull(dialog.getClient());
    }

    /**
     * Prova la creació d'un client amb un nom i un correu electrònic buits.
     * Aquesta prova comprova que si el nom o el correu electrònic estan buits, el client no es crea.
     */
    @Test
    void testConfirmarAmbNomIEmailBuits() {
        dialog.getTextCodi().setText("1");
        dialog.getTextNom().setText("");
        dialog.getTextEmail().setText("");

        dialog.confirmar();

        assertNull(dialog.getClient());
    }

    /**
     * Prova la cancel·lació del diàleg.
     * Aquesta prova comprova que en cancel·lar el diàleg, el client no es crea i s'ha de retornar null.
     */
    @Test
    void testCancelar() {
        dialog.cancelar();
        assertNull(dialog.getClient());
    }
}