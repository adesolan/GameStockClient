package com.gamestock.client.controllers;

import com.gamestock.client.views.DialegClient;
import com.gamestock.client.views.PantallaPrincipal;
import com.gamestock.client.models.Client;
import com.gamestock.client.models.Joc;
import com.gamestock.client.models.Lloguer;
import com.gamestock.client.models.Usuari;
import com.gamestock.client.serveis.ServeiClient;
import com.gamestock.client.views.DialegJoc;
import com.gamestock.client.views.DialegLloguer;
import com.gamestock.client.views.DialegUsuari;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador principal de l'aplicació. Gestiona les accions sobre la pantalla
 * principal
 */
public class ControladorPrincipal {

    private final PantallaPrincipal pantallaPrincipal;
    private final ServeiClient conexioServidor;

    private enum Contexte {
        CLIENTS,
        JOCS,
        LLOGUERS,
        USUARIS
    }
    private Contexte contexteActual = Contexte.CLIENTS; //Inicialment carreguem clients

    /**
     * Constructor del controlador principal. Inicialitza la pantalla principal,
     * carrega els clients i afegeix els listeners per a les accions.
     *
     * @param esAdministrador Indica si l'usuari té permisos d'administrador.
     */
    public ControladorPrincipal(boolean esAdministrador) {
        pantallaPrincipal = new PantallaPrincipal(esAdministrador);
        pantallaPrincipal.setContentPane(pantallaPrincipal.panelPrincipal);
        pantallaPrincipal.setTitle("GameStockClient");
        pantallaPrincipal.setSize(1024, 768);
        pantallaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantallaPrincipal.setLocationRelativeTo(null);

        this.conexioServidor = ServeiClient.getInstance();
        carregarClients(); // Carrega la llista de clients a la taula
        afegirListeners(); // Afegeix els listeners als botons

        pantallaPrincipal.setVisible(true);
    }

    private void setContexte(Contexte contexte) {
        this.contexteActual = contexte;
    }

    /**
     * Carrega la llista de clients des del servei simulat i la mostra en la
     * taula de la pantalla principal.
     */
    public void carregarClients() {
        try {
            String[] nomColumnes = {"ID", "Nom", "Cognom 1", "Cognom 2", "Email", "Teléfon"};
            Object[][] dades = new Object[][]{};
            DefaultTableModel model = new DefaultTableModel(dades, nomColumnes);
            List<Client> clients = conexioServidor.obtenirClients();

            // Afegeix cada client a la taula
            for (Client client : clients) {
                model.addRow(new Object[]{client.getId(), client.getNom(), client.getCognom1(), client.getCognom2(), client.getEmail(), client.getTelefon()});

            }
            pantallaPrincipal.canviaModelTaula(model);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carrega la llista de jocs des del servidor i la mostra en la taula de la
     * pantalla principal.
     */
    public void carregarJocs() {
        try {
            String[] nomColumnes = {"ID", "Títol", "Gènere", "Estudi", "Preu Lloguer", "Stock"};
            Object[][] dades = new Object[][]{};
            DefaultTableModel model = new DefaultTableModel(dades, nomColumnes);

            // Obtenim la llista de jocs del servidor
            List<Joc> jocs = conexioServidor.obtenirJocs();

            // Afegim cada joc a la taula
            for (Joc joc : jocs) {
                model.addRow(new Object[]{
                    joc.getId(),
                    joc.getTitol(),
                    joc.getGenere(),
                    joc.getEstudi(),
                    joc.getPreuLloguer(),
                    joc.getStock()
                });
            }

            // Actualitza el model de la taula a la vista
            pantallaPrincipal.canviaModelTaula(model);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carrega la llista de lloguers des del servidor i la mostra en la taula de
     * la pantalla principal.
     */
    public void carregarLloguers() {
        try {
            String[] nomColumnes = {"ID Lloguer", "ID Client", "ID Joc", "Data Lloguer", "Data Retorn", "Preu", "Actiu"};
            Object[][] dades = new Object[][]{};
            DefaultTableModel model = new DefaultTableModel(dades, nomColumnes);
            // Obtenim la llista de lloguers del servidor
            List<Lloguer> lloguers = conexioServidor.obtenirLloguers();
            // Afegim cada lloguer a la taula
            for (Lloguer lloguer : lloguers) {
                model.addRow(new Object[]{
                    lloguer.getId(),
                    lloguer.getIdClient(),
                    lloguer.getIdJoc(),
                    lloguer.getDataLloguer(),
                    lloguer.getDataRetorn(),
                    lloguer.getPreu(),
                    lloguer.isActiu()
                });
            }
            // Actualitza el model de la taula a la vista
            pantallaPrincipal.canviaModelTaula(model);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carrega la llista d'usuaris des del servidor i la mostra en la taula de
     * la pantalla principal.
     */
    public void carregarUsuaris() {
        try {
            String[] nomColumnes = {"ID", "Nom d'usuari", "Contrasenya", "Email", "Rol"};
            Object[][] dades = new Object[][]{};
            DefaultTableModel model = new DefaultTableModel(dades, nomColumnes);
            List<Usuari> usuaris = conexioServidor.obtenirUsuaris();

            for (Usuari usuari : usuaris) {
                model.addRow(new Object[]{usuari.getId(), usuari.getUsername(), usuari.getPassword(), usuari.getEmail(), usuari.getRole()});
            }
            pantallaPrincipal.canviaModelTaula(model);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Mètodes per gestionar l'afegit, eliminació i edició per context
    private void afegirClient() {
        DialegClient dialeg = new DialegClient(pantallaPrincipal, "Afegir Client");
        dialeg.setVisible(true);
        Client nouClient = dialeg.getClient();
        if (nouClient != null) {
            boolean exit = conexioServidor.afegirClient(nouClient);
            if (exit) {
                carregarClients();
                JOptionPane.showMessageDialog(pantallaPrincipal, "Client afegit correctament.");
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "El codi del client ja existeix o no s'ha pogut afegir.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void afegirJoc() {
        DialegJoc dialeg = new DialegJoc(pantallaPrincipal, "Afegir Joc");
        dialeg.setVisible(true);
        Joc nouJoc = dialeg.getJoc();
        if (nouJoc != null) {
            boolean exit = conexioServidor.afegirJoc(nouJoc);
            if (exit) {
                carregarJocs();
                JOptionPane.showMessageDialog(pantallaPrincipal, "Joc afegit correctament.");
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut afegir el joc.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void afegirLloguer() {
        DialegLloguer dialeg = new DialegLloguer(pantallaPrincipal, "Afegir Lloguer");
        dialeg.setVisible(true);
        Lloguer nouLloguer = dialeg.getLloguer();
        if (nouLloguer != null) {
            boolean exit = conexioServidor.afegirLloguer(nouLloguer);
            if (exit) {
                carregarLloguers();
                JOptionPane.showMessageDialog(pantallaPrincipal, "Lloguer afegit correctament.");
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut afegir el lloguer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void afegirUsuari() {
        DialegUsuari dialeg = new DialegUsuari(pantallaPrincipal, "Afegir Usuari");
        dialeg.setVisible(true);
        Usuari nouUsuari = dialeg.getUsuari();
        if (nouUsuari != null) {
            boolean exit = conexioServidor.afegirUsuari(nouUsuari);
            if (exit) {
                carregarUsuaris();
                JOptionPane.showMessageDialog(pantallaPrincipal, "Usuari afegit correctament.");
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut afegir l'usuari.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarClient(int filaSeleccionada) {
        long id = (long) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
        boolean exit = conexioServidor.eliminarClient(id);
        if (exit) {
            carregarClients();
            JOptionPane.showMessageDialog(pantallaPrincipal, "Client eliminat correctament.");
        } else {
            JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut eliminar el client.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarJoc(int filaSeleccionada) {
        long id = (long) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
        boolean exit = conexioServidor.eliminarJoc(id);
        if (exit) {
            carregarJocs();
            JOptionPane.showMessageDialog(pantallaPrincipal, "Joc eliminat correctament.");
        } else {
            JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut eliminar el joc.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarLloguer(int filaSeleccionada) {
        long id = (long) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
        boolean exit = conexioServidor.eliminarLloguer(id);
        if (exit) {
            carregarLloguers();
            JOptionPane.showMessageDialog(pantallaPrincipal, "Lloguer eliminat correctament.");
        } else {
            JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut eliminar el lloguer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarUsuari(int filaSeleccionada) {
        long id = (long) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
        boolean exit = conexioServidor.eliminarUsuari(id);
        if (exit) {
            carregarUsuaris();
            JOptionPane.showMessageDialog(pantallaPrincipal, "Usuari eliminat correctament.");
        } else {
            JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut eliminar l'usuari.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarClient(int filaSeleccionada) {
        long id = (long) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
        String nom = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 1);
        String cognom1 = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 2);
        String cognom2 = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 3);
        String email = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 4);
        String telefon = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 5);
        Client client = new Client(id, nom, cognom1, cognom2, email, telefon);

        DialegClient dialeg = new DialegClient(pantallaPrincipal, "Editar Client", client);
        dialeg.setVisible(true);
        Client clientEditat = dialeg.getClient();
        if (clientEditat != null) {
            boolean exit = conexioServidor.editarClient(clientEditat);
            if (exit) {
                carregarClients();
                JOptionPane.showMessageDialog(pantallaPrincipal, "Client editat correctament.");
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut editar el client.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarJoc(int filaSeleccionada) {
        long id = (long) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
        String titol = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 1);
        String genere = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 2);
        String estudi = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 3);
        double preuLloguer = (double) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 4);
        int stock = (int) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 5);
        Joc joc = new Joc(id, titol, genere, estudi, preuLloguer, stock);

        DialegJoc dialeg = new DialegJoc(pantallaPrincipal, "Editar Joc", joc);
        dialeg.setVisible(true);
        Joc jocEditat = dialeg.getJoc();
        if (jocEditat != null) {
            boolean exit = conexioServidor.editarJoc(jocEditat);
            if (exit) {
                carregarJocs();
                JOptionPane.showMessageDialog(pantallaPrincipal, "Joc editat correctament.");
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut editar el joc.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarLloguer(int filaSeleccionada) {
        long id = (long) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
        long idClient = (long) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 1);
        long idJoc = (long) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 2);
        String dataLloguer = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 3);
        String dataRetorn = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 4);
        double preu = (double) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 5);
        boolean actiu = (boolean) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 6);
        Lloguer lloguer = new Lloguer(id, idClient, idJoc, dataLloguer, dataRetorn, preu, actiu);

        DialegLloguer dialeg = new DialegLloguer(pantallaPrincipal, "Editar Lloguer", lloguer);
        dialeg.setVisible(true);
        Lloguer lloguerEditat = dialeg.getLloguer();
        if (lloguerEditat != null) {
            boolean exit = conexioServidor.editarLloguer(lloguerEditat);
            if (exit) {
                carregarLloguers();
                JOptionPane.showMessageDialog(pantallaPrincipal, "Lloguer editat correctament.");
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut editar el lloguer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarUsuari(int filaSeleccionada) {
        long id = (long) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
        String username = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 1);
        String password = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 2);
        String email = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 3);
        String role = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 4);
        Usuari usuari = new Usuari(id, username, password, email, role);

        DialegUsuari dialeg = new DialegUsuari(pantallaPrincipal, "Editar Usuari", usuari);
        dialeg.setVisible(true);
        Usuari usuariEditat = dialeg.getUsuari();
        if (usuariEditat != null) {
            boolean exit = conexioServidor.editarUsuari(usuariEditat);
            if (exit) {
                carregarUsuaris();
                JOptionPane.showMessageDialog(pantallaPrincipal, "Usuari editat correctament.");
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut editar l'usuari.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void afegirListeners() {
        // Listener per canviar el context
        pantallaPrincipal.getBotoClients().addActionListener(e -> {
            setContexte(Contexte.CLIENTS);
            carregarClients();
        });
        pantallaPrincipal.getBotoJocs().addActionListener(e -> {
            setContexte(Contexte.JOCS);
            carregarJocs();
        });
        pantallaPrincipal.getBotoLloguers().addActionListener(e -> {
            setContexte(Contexte.LLOGUERS);
            carregarLloguers();
        });
        if (pantallaPrincipal.isEsAdministrador()) {
            pantallaPrincipal.getBotoUsuaris().addActionListener(e -> {
                setContexte(Contexte.USUARIS);
                carregarUsuaris();
            });
        }

        // Listener per afegir un nou element
        pantallaPrincipal.getBotoAfegir().addActionListener(e -> {
            switch (contexteActual) {
                case CLIENTS ->
                    afegirClient();
                case JOCS ->
                    afegirJoc();
                case LLOGUERS ->
                    afegirLloguer();
                case USUARIS ->
                    afegirUsuari();
            }
        });

        // Listener per eliminar un element seleccionat
        pantallaPrincipal.getBotoEliminar().addActionListener(e -> {
            int filaSeleccionada = pantallaPrincipal.getTaulaPrincipal().getSelectedRow();
            if (filaSeleccionada != -1) {
                switch (contexteActual) {
                    case CLIENTS ->
                        eliminarClient(filaSeleccionada);
                    case JOCS ->
                        eliminarJoc(filaSeleccionada);
                    case LLOGUERS ->
                        eliminarLloguer(filaSeleccionada);
                    case USUARIS ->
                        eliminarUsuari(filaSeleccionada);
                }
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha seleccionat cap element.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Listener per editar un element seleccionat
        pantallaPrincipal.getBotoEditar().addActionListener(e -> {
            int filaSeleccionada = pantallaPrincipal.getTaulaPrincipal().getSelectedRow();
            if (filaSeleccionada != -1) {
                switch (contexteActual) {
                    case CLIENTS ->
                        editarClient(filaSeleccionada);
                    case JOCS ->
                        editarJoc(filaSeleccionada);
                    case LLOGUERS ->
                        editarLloguer(filaSeleccionada);
                    case USUARIS ->
                        editarUsuari(filaSeleccionada);
                }
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha seleccionat cap element.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Listener per logout
        pantallaPrincipal.getBotoLogout().addActionListener(e -> {
            boolean logoutExitos = conexioServidor.tancarSessio();
            if (logoutExitos) {
                JOptionPane.showMessageDialog(
                        pantallaPrincipal,
                        "Sessió tancada correctament.",
                        "Logout",
                        JOptionPane.INFORMATION_MESSAGE
                );
                pantallaPrincipal.dispose(); // Tanca la pantalla principal
                new ControladorLogin(); // Torna a la pantalla de login
            } else {
                JOptionPane.showMessageDialog(
                        pantallaPrincipal,
                        "Error tancant la sessió. Torna-ho a intentar.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

}
