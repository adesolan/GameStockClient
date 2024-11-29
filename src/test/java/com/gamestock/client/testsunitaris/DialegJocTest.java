package com.gamestock.client.testsunitaris;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.gamestock.client.models.Joc;
import com.gamestock.client.views.DialegJoc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Proves unitàries per a la classe DialegJoc.
 * Aquesta classe comprova el comportament de les operacions que es poden realitzar
 * a la finestra de diàleg de joc.
 */
class DialegJocTest {
    private DialegJoc dialog;

    /**
     * Configura el diàleg abans de cada prova.
     * S'inicialitza un objecte **DialegJoc** amb un **Frame** buit i el títol "Test Dialog".
     */
    @BeforeEach
    void setUp() {
        dialog = new DialegJoc(new Frame(), "Test Dialog");
    }

    /**
     * Prova la creació d'un joc amb dades vàlides.
     * Aquesta prova omple els camps de text del diàleg amb dades vàlides i comprova que el joc
     * creat és correcte amb les dades introduïdes.
     */
    @Test
    void testConfirmarAmbDadesValides() {
        dialog.getTextId().setText("1");
        dialog.getTextTitol().setText("Títol del Joc");
        dialog.getTextGenere().setText("Acció");
        dialog.getTextEstudi().setText("Estudi X");
        dialog.getTextPreuLloguer().setText("5.5");
        dialog.getTextStock().setText("10");

        dialog.confirmar();

        Joc joc = dialog.getJoc();
        assertNotNull(joc);
        assertEquals(1, joc.getId());
        assertEquals("Títol del Joc", joc.getTitol());
        assertEquals("Acció", joc.getGenere());
        assertEquals("Estudi X", joc.getEstudi());
        assertEquals(5.5, joc.getPreuLloguer());
        assertEquals(10, joc.getStock());
    }

    /**
     * Prova la creació d'un joc amb dades invàlides.
     * Aquesta prova omple els camps amb valors no vàlids i comprova que no es crea cap joc.
     */
    @Test
    void testConfirmarAmbDadesInvalides() {
        dialog.getTextId().setText("abc");
        dialog.getTextPreuLloguer().setText("xyz");
        dialog.confirmar();

        assertNull(dialog.getJoc());
    }

    /**
     * Prova la creació d'un joc amb camps buits.
     * Aquesta prova comprova que si algun camp està buit, el joc no es crea.
     */
    @Test
    void testConfirmarAmbCampsBuits() {
        dialog.getTextId().setText("1");
        dialog.getTextTitol().setText("");
        dialog.getTextGenere().setText("");

        dialog.confirmar();

        assertNull(dialog.getJoc());
    }

    /**
     * Prova la cancel·lació del diàleg.
     * Aquesta prova comprova que en cancel·lar el diàleg, el joc no es crea i retorna null.
     */
    @Test
    void testCancelar() {
        dialog.cancelar();
        assertNull(dialog.getJoc());
    }
}