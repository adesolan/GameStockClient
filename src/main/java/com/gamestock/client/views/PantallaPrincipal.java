package com.gamestock.client.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Classe que representa la pantalla principal de l'aplicació.
 */
public class PantallaPrincipal extends JFrame {
    private JTable taulaPrincipal;
    private JButton botoAfegir;
    private JButton botoEliminar;
    private JButton botoEditar;
    private JButton botoClients;
    private JButton botoJocs;
    private JButton botoLloguers;
    public JPanel panelPrincipal;

    /**
     * Constructor de la classe PantallaPrincipal.
     * Configura la interfície gràfica i inicialitza els components de la pantalla principal.
     */
    public PantallaPrincipal() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        panelPrincipal.setBorder(new EmptyBorder(10, 20, 10, 20));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBorder(new EmptyBorder(0, 20, 0, 20));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(panel1, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Accions:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        botoAfegir = new JButton();
        botoAfegir.setText("Afegir");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(botoAfegir, gbc);
        botoEditar = new JButton();
        botoEditar.setText("Editar");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(botoEditar, gbc);
        botoEliminar = new JButton();
        botoEliminar.setText("Eliminar");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(botoEliminar, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Dades:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label2, gbc);
        botoClients = new JButton();
        botoClients.setText("Clients");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(botoClients, gbc);
        botoJocs = new JButton();
        botoJocs.setText("Jocs");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(botoJocs, gbc);
        botoLloguers = new JButton();
        botoLloguers.setText("Lloguers");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(botoLloguers, gbc);
        final JScrollPane scrollPane1 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelPrincipal.add(scrollPane1, gbc);
        taulaPrincipal = new JTable();
        taulaPrincipal.setFillsViewportHeight(false);
        scrollPane1.setViewportView(taulaPrincipal);

        setContentPane(panelPrincipal);
        setLocationRelativeTo(getOwner());
    }

    /**
     * Canvia el model de la taula principal.
     *
     * @param modelTaula El nou model de taula.
     */
    public void canviaModelTaula(TableModel modelTaula) {
        taulaPrincipal.setModel(modelTaula);
    }

    /**
     * Obté el model actual de la taula.
     *
     * @return El model de la taula.
     */
    public DefaultTableModel getModelTaula() {
        return (DefaultTableModel) taulaPrincipal.getModel();
    }

    /**
     * Obté el botó d'afegir.
     *
     * @return El botó d'afegir.
     */
    public AbstractButton getBotoAfegir() {
        return botoAfegir;
    }

    /**
     * Obté el botó d'eliminar.
     *
     * @return El botó d'eliminar.
     */
    public AbstractButton getBotoEliminar() {
        return botoEliminar;
    }

    /**
     * Obté la taula de clients.
     *
     * @return La taula de clients.
     */
    public JTable getTaulaClients() {
        return taulaPrincipal;
    }

    /**
     * Obté el botó d'editar.
     *
     * @return El botó d'editar.
     */
    public AbstractButton getBotoEditar() {
        return botoEditar;
    }

}
