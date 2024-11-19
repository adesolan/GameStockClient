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

    /**
     * Constructor del controlador principal. Inicialitza la pantalla principal,
     * carrega els clients i afegeix els listeners per a les accions.
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
            String[] nomColumnes = {"ID Client", "ID Joc", "Data Lloguer", "Data Retorn", "Preu"};
            Object[][] dades = new Object[][]{};
            DefaultTableModel model = new DefaultTableModel(dades, nomColumnes);
            // Obtenim la llista de lloguers del servidor
            List<Lloguer> lloguers = conexioServidor.obtenirLloguers();
            // Afegim cada lloguer a la taula
            for (Lloguer lloguer : lloguers) {
                model.addRow(new Object[]{
                    lloguer.getIdClient(),
                    lloguer.getIdJoc(),
                    lloguer.getDataLloguer(),
                    lloguer.getDataRetorn(),
                    lloguer.getPreu()
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
            String[] nomColumnes = {"Nom d'usuari", "Contrasenya", "Email", "Rol"};
            Object[][] dades = new Object[][]{};
            DefaultTableModel model = new DefaultTableModel(dades, nomColumnes);
            List<Usuari> usuaris = conexioServidor.obtenirUsuaris();

            for (Usuari usuari : usuaris) {
                model.addRow(new Object[]{usuari.getUsername(), usuari.getPassword(), usuari.getEmail(), usuari.getRole()});
            }
            pantallaPrincipal.canviaModelTaula(model);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void afegirListeners() {
        // Listener per afegir un nou element (Client, Joc, Lloguer, Usuari)
        pantallaPrincipal.getBotoAfegir().addActionListener(e -> {
            if (pantallaPrincipal.getBotoClients().equals(e.getSource())) {
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
            } else if (pantallaPrincipal.getBotoJocs().equals(e.getSource())) {
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
            } else if (pantallaPrincipal.getBotoLloguers().equals(e.getSource())) {
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
            } else if (pantallaPrincipal.isEsAdministrador() && pantallaPrincipal.getBotoUsuaris().equals(e.getSource())) {
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
        });

        // Listener per eliminar un element seleccionat (Client, Joc, Lloguer, Usuari)
        pantallaPrincipal.getBotoEliminar().addActionListener(e -> {
            int filaSeleccionada = pantallaPrincipal.getTaulaPrincipal().getSelectedRow();
            if (filaSeleccionada != -1) {
                int confirm = JOptionPane.showConfirmDialog(pantallaPrincipal, "Esteu segur que voleu eliminar aquest element?", "Confirmar Eliminació", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (pantallaPrincipal.getBotoClients().equals(e.getSource())) {
                        int id = (int) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
                        boolean exit = conexioServidor.eliminarClient(id);
                        if (exit) {
                            carregarClients();
                            JOptionPane.showMessageDialog(pantallaPrincipal, "Client eliminat correctament.");
                        } else {
                            JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut eliminar el client.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (pantallaPrincipal.getBotoJocs().equals(e.getSource())) {
                        int id = (int) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
                        boolean exit = conexioServidor.eliminarJoc(id);
                        if (exit) {
                            carregarJocs();
                            JOptionPane.showMessageDialog(pantallaPrincipal, "Joc eliminat correctament.");
                        } else {
                            JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut eliminar el joc.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (pantallaPrincipal.getBotoLloguers().equals(e.getSource())) {
                        int id = (int) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
                        boolean exit = conexioServidor.eliminarLloguer(id);
                        if (exit) {
                            carregarLloguers();
                            JOptionPane.showMessageDialog(pantallaPrincipal, "Lloguer eliminat correctament.");
                        } else {
                            JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut eliminar el lloguer.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (pantallaPrincipal.isEsAdministrador() && pantallaPrincipal.getBotoUsuaris().equals(e.getSource())) {
                        int id = (int) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
                        boolean exit = conexioServidor.eliminarUsuari(id);
                        if (exit) {
                            carregarUsuaris();
                            JOptionPane.showMessageDialog(pantallaPrincipal, "Usuari eliminat correctament.");
                        } else {
                            JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut eliminar l'usuari.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "Selecciona un element per eliminar.", "Advertència", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Listener per editar un element seleccionat (Client, Joc, Lloguer, Usuari)
        pantallaPrincipal.getBotoEditar().addActionListener(e -> {
            int filaSeleccionada = pantallaPrincipal.getTaulaPrincipal().getSelectedRow();
            if (filaSeleccionada != -1) {
                if (pantallaPrincipal.getBotoClients().equals(e.getSource())) {
                    int id = (int) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
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
                } else if (pantallaPrincipal.getBotoJocs().equals(e.getSource())) {
                    int id = (int) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
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
                } else if (pantallaPrincipal.getBotoLloguers().equals(e.getSource())) {
                    int idClient = (int) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
                    int idJoc = (int) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 1);
                    String dataLloguer = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 2);
                    String dataRetorn = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 3);
                    double preu = (double) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 4);
                    Lloguer lloguer = new Lloguer(idClient, idJoc, dataLloguer, dataRetorn, preu);

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
                } else if (pantallaPrincipal.isEsAdministrador() && pantallaPrincipal.getBotoUsuaris().equals(e.getSource())) {
                    String username = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
                    String password = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 1);
                    String email = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 2);
                    String role = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 3);
                    Usuari usuari = new Usuari(username, password, email, role);

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
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "Selecciona un element per editar.", "Advertència", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
