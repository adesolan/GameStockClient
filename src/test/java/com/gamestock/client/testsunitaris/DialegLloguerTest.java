package com.gamestock.client.testsunitaris;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.gamestock.client.models.Lloguer;
import com.gamestock.client.views.DialegLloguer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Proves unitàries per a la classe DialegLloguer.
 * Aquesta classe comprova el comportament de les operacions que es poden realitzar
 * a la finestra de diàleg de lloguer.
 */
class DialegLloguerTest {
    private DialegLloguer dialog;

    /**
     * Configura el diàleg abans de cada prova.
     * S'inicialitza un objecte **DialegLloguer** amb un **Frame** buit i el títol "Test Dialog".
     */
    @BeforeEach
    void setUp() {
        dialog = new DialegLloguer(new Frame(), "Test Dialog");
    }

    /**
     * Prova la creació d'un lloguer amb dades vàlides.
     */
    @Test
    void testConfirmarAmbDadesValides() {
        dialog.getTextId().setText("1");
        dialog.getTextIdClient().setText("10");
        dialog.getTextIdJoc().setText("20");
        dialog.getTextDataLloguer().setText("2024-01-01");
        dialog.getTextDataRetorn().setText("2024-01-10");
        dialog.getTextPreu().setText("15.5");
        dialog.getCheckboxActiu().setSelected(true);

        dialog.confirmar();

        Lloguer lloguer = dialog.getLloguer();
        assertNotNull(lloguer);
        assertEquals(1, lloguer.getId());
        assertEquals(10, lloguer.getIdClient());
        assertEquals(20, lloguer.getIdJoc());
        assertEquals("2024-01-01", lloguer.getDataLloguer());
        assertEquals("2024-01-10", lloguer.getDataRetorn());
        assertEquals(15.5, lloguer.getPreu());
        assertTrue(lloguer.isActiu());
    }

    /**
     * Prova la creació d'un lloguer amb dades invàlides.
     */
    @Test
    void testConfirmarAmbDadesInvalides() {
        dialog.getTextId().setText("abc");
        dialog.getTextPreu().setText("xyz");
        dialog.confirmar();

        assertNull(dialog.getLloguer());
    }

    /**
     * Prova la cancel·lació del diàleg.
     */
    @Test
    void testCancelar() {
        dialog.cancelar();
        assertNull(dialog.getLloguer());
    }
}