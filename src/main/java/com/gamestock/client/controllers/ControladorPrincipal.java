package com.gamestock.client.controllers;

import com.gamestock.client.views.DialegClient;
import com.gamestock.client.views.PantallaPrincipal;
import com.gamestock.client.models.Client;
import com.gamestock.client.models.ServeiSimulat;


import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.util.List;

/**
 * Controlador principal de l'aplicació.
 * Gestiona les accions sobre la pantalla principal
 */
public class ControladorPrincipal {
    private final PantallaPrincipal pantallaPrincipal;
    private final ServeiSimulat serveiSimulat;

    /**
     * Constructor del controlador principal.
     * Inicialitza la pantalla principal, carrega els clients i afegeix els listeners per a les accions.
     */
    public ControladorPrincipal() {
        pantallaPrincipal = new PantallaPrincipal();
        pantallaPrincipal.setContentPane(pantallaPrincipal.panelPrincipal);
        pantallaPrincipal.setTitle("GameStockClient");
        pantallaPrincipal.setSize(1024, 768);
        pantallaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantallaPrincipal.setLocationRelativeTo(null);


        serveiSimulat = new ServeiSimulat();
        carregarClients(); // Carrega la llista de clients a la taula
        afegirListeners(); // Afegeix els listeners als botons


        pantallaPrincipal.setVisible(true);
    }

    /**
     * Carrega la llista de clients des del servei simulat i la mostra en la taula de la pantalla principal.
     */
    public void carregarClients() {
        String[] nomColumnes = {"ID", "Nom", "Email", "Teléfon"};
        Object[][] dades = new Object[][]{};
        DefaultTableModel model = new DefaultTableModel(dades, nomColumnes);
        List<Client> clients = serveiSimulat.obtenirTotsClients();

        // Afegeix cada client a la taula
        for (Client client : clients) {
            model.addRow(new Object[]{client.getCodi(), client.getNom(), client.getEmail(), client.getTelefon()});

        }
        pantallaPrincipal.canviaModelTaula(model);
    }

    /**
     * Afegeix els listeners als botons de la pantalla principal per gestionar les accions d'afegir, eliminar i editar clients.
     */
    private void afegirListeners() {
        // Listener per afegir un nou client
        pantallaPrincipal.getBotoAfegir().addActionListener(_ -> {
            DialegClient dialeg = new DialegClient(pantallaPrincipal, "Afegir Client");
            dialeg.setVisible(true);
            Client nouClient = dialeg.getClient();
            if (nouClient != null) {
                boolean exit = serveiSimulat.afegirClient(nouClient);
                if (exit) {
                    carregarClients();
                    JOptionPane.showMessageDialog(pantallaPrincipal, "Client afegit correctament.");
                } else {
                    JOptionPane.showMessageDialog(pantallaPrincipal, "El codi del client ja existeix o no s'ha pogut afegir.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Listener per eliminar un client seleccionat de la taula
        pantallaPrincipal.getBotoEliminar().addActionListener(_ -> {
            int filaSeleccionada = pantallaPrincipal.getTaulaClients().getSelectedRow();
            if (filaSeleccionada != -1) {
                int codi = (int) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
                int confirm = JOptionPane.showConfirmDialog(pantallaPrincipal, "Esteu segur que voleu eliminar el client amb codi " + codi + "?", "Confirmar Eliminació", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean exit = serveiSimulat.eliminarClient(codi);
                    if (exit) {
                        carregarClients();
                        JOptionPane.showMessageDialog(pantallaPrincipal, "Client eliminat correctament.");
                    } else {
                        JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut eliminar el client.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "Selecciona un client per eliminar.", "Advertència", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Listener per editar les dades d'un client seleccionat de la taula
        pantallaPrincipal.getBotoEditar().addActionListener(_ -> {
            int filaSeleccionada = pantallaPrincipal.getTaulaClients().getSelectedRow();
            if (filaSeleccionada != -1) {
                int codi = (int) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 0);
                String nom = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 1);
                String email = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 2);
                String telefon = (String) pantallaPrincipal.getModelTaula().getValueAt(filaSeleccionada, 3);
                Client client = new Client(codi, nom, email, telefon);

                DialegClient dialeg = new DialegClient(pantallaPrincipal, "Editar Client", client);
                dialeg.setVisible(true);
                Client clientEditat = dialeg.getClient();
                if (clientEditat != null) {
                    boolean exit = serveiSimulat.editarClient(clientEditat);
                    if (exit) {
                        carregarClients();
                        JOptionPane.showMessageDialog(pantallaPrincipal, "Client editat correctament.");
                    } else {
                        JOptionPane.showMessageDialog(pantallaPrincipal, "No s'ha pogut editar el client.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(pantallaPrincipal, "Selecciona un client per editar.", "Advertència", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}