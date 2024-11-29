package com.gamestock.client.testsunitaris;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.gamestock.client.models.Usuari;
import com.gamestock.client.views.DialegUsuari;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Proves unitàries per a la classe DialegUsuari.
 */
class DialegUsuariTest {
    private DialegUsuari dialog;

    /**
     * Configura el diàleg abans de cada prova.
     */
    @BeforeEach
    void setUp() {
        dialog = new DialegUsuari(null, "Test Dialog");
    }

    /**
     * Prova la creació d'un usuari amb dades vàlides.
     */
    @Test
    void testConfirmarAmbDadesValides() {
        dialog.getTextId().setText("1");
        dialog.getTextUsername().setText("admin");
        dialog.getTextPassword().setText("1234");
        dialog.getTextEmail().setText("admin@example.com");
        dialog.getComboRole().setSelectedItem("admin");

        dialog.confirmar();

        Usuari usuari = dialog.getUsuari();
        assertNotNull(usuari);
        assertEquals(1, usuari.getId());
        assertEquals("admin", usuari.getUsername());
        assertEquals("1234", usuari.getPassword());
        assertEquals("admin@example.com", usuari.getEmail());
        assertEquals("admin", usuari.getRole());
    }

    /**
     * Prova la creació d'un usuari amb dades invàlides (camp ID no numèric).
     */
    @Test
    void testConfirmarAmbDadesInvalides() {
        dialog.getTextId().setText("xyz");
        dialog.getTextUsername().setText("admin");
        dialog.getTextPassword().setText("1234");
        dialog.getTextEmail().setText("admin@example.com");
        dialog.getComboRole().setSelectedItem("admin");

        assertThrows(NumberFormatException.class, dialog::confirmar);
    }

    /**
     * Prova la creació d'un usuari amb camps buits.
     */
    @Test
    void testConfirmarAmbCampsBuits() {
        dialog.getTextId().setText("1");
        dialog.getTextUsername().setText("");
        dialog.getTextPassword().setText("1234");
        dialog.getTextEmail().setText("admin@example.com");
        dialog.getComboRole().setSelectedItem("admin");

        dialog.confirmar();

        assertNull(dialog.getUsuari());
    }

    /**
     * Prova la cancel·lació del diàleg.
     */
    @Test
    void testCancelar() {
        dialog.cancelar();
        assertNull(dialog.getUsuari());
    }

    /**
     * Prova que els valors d'entrada es carreguen correctament des d'un objecte Usuari existent.
     */
    @Test
    void testInicialitzarAmbUsuari() {
        Usuari usuari = new Usuari(1, "admin", "1234", "admin@example.com", "admin");
        dialog = new DialegUsuari(null, "Test Dialog", usuari);

        assertEquals("1", dialog.getTextId().getText());
        assertEquals("admin", dialog.getTextUsername().getText());
        assertEquals("1234", new String(dialog.getTextPassword().getPassword()));
        assertEquals("admin@example.com", dialog.getTextEmail().getText());
        assertEquals("admin", dialog.getComboRole().getSelectedItem());
    }
}