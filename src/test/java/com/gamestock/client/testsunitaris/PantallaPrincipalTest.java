package com.gamestock.client.testsunitaris;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.gamestock.client.views.PantallaPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de proves per a la classe PantallaPrincipal.
 * Comprova la funcionalitat i els components de la interfície gràfica.
 */
class PantallaPrincipalTest {
    private PantallaPrincipal pantallaPrincipal;

    /**
     * Configura l'objecte PantallaPrincipal abans de cada prova.
     */
    @BeforeEach
    void setUp() {
        pantallaPrincipal = new PantallaPrincipal(true); // Inicialitza com a administrador
    }

    /**
     * Comprova que es pot canviar correctament el model de la taula principal.
     */
    @Test
    void testCanviaModelTaula() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Column1", "Column2"}, 0);
        pantallaPrincipal.canviaModelTaula(model);
        assertEquals(model, pantallaPrincipal.getModelTaula());
    }

    /**
     * Comprova que el botó "Afegir" no és null.
     */
    @Test
    void testGetBotoAfegir() {
        assertNotNull(pantallaPrincipal.getBotoAfegir());
    }

    /**
     * Comprova que el botó "Eliminar" no és null.
     */
    @Test
    void testGetBotoEliminar() {
        assertNotNull(pantallaPrincipal.getBotoEliminar());
    }

    /**
     * Comprova que el botó "Editar" no és null.
     */
    @Test
    void testGetBotoEditar() {
        assertNotNull(pantallaPrincipal.getBotoEditar());
    }

    /**
     * Comprova que el botó "Clients" no és null.
     */
    @Test
    void testGetBotoClients() {
        assertNotNull(pantallaPrincipal.getBotoClients());
    }

    /**
     * Comprova que el botó "Jocs" no és null.
     */
    @Test
    void testGetBotoJocs() {
        assertNotNull(pantallaPrincipal.getBotoJocs());
    }

    /**
     * Comprova que el botó "Lloguers" no és null.
     */
    @Test
    void testGetBotoLloguers() {
        assertNotNull(pantallaPrincipal.getBotoLloguers());
    }

    /**
     * Comprova que el botó "Usuaris" és accessible només si l'usuari és administrador.
     */
    @Test
    void testGetBotoUsuaris() {
        if (pantallaPrincipal.isEsAdministrador()) {
            assertNotNull(pantallaPrincipal.getBotoUsuaris());
        } else {
            assertNull(pantallaPrincipal.getBotoUsuaris());
        }
    }

    /**
     * Comprova que el botó "Logout" no és null.
     */
    @Test
    void testGetBotoLogout() {
        assertNotNull(pantallaPrincipal.getBotoLogout());
    }

    /**
     * Comprova que la pantalla identifica correctament l'usuari com a administrador.
     */
    @Test
    void testEsAdministrador() {
        assertTrue(pantallaPrincipal.isEsAdministrador());
    }

    /**
     * Comprova que es pot canviar el valor d'administrador de la pantalla.
     */
    @Test
    void testCanviaEsAdministrador() {
        pantallaPrincipal.setEsAdministrador(false);
        assertFalse(pantallaPrincipal.isEsAdministrador());
    }

    /**
     * Comprova que la taula principal no és null.
     */
    @Test
    void testGetTaulaPrincipal() {
        assertNotNull(pantallaPrincipal.getTaulaPrincipal());
    }

    /**
     * Comprova que es pot canviar la taula principal.
     */
    @Test
    void testSetTaulaPrincipal() {
        JTable novaTaula = new JTable();
        pantallaPrincipal.setTaulaPrincipal(novaTaula);
        assertEquals(novaTaula, pantallaPrincipal.getTaulaPrincipal());
    }

    /**
     * Comprova que el panell principal no és null.
     */
    @Test
    void testPanelPrincipalNoNull() {
        assertNotNull(pantallaPrincipal.getPanelPrincipal());
    }
}