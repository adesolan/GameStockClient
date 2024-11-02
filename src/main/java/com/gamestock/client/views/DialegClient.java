package com.gamestock.client.views;

import com.gamestock.client.models.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Dialog per gestionar la creació i edició de clients.
 */
public class DialegClient extends JDialog {
    private JTextField textCodi;
    private JTextField textNom;
    private JTextField textEmail;
    private JTextField textTelefon;
    private JButton botoConfirmar;
    private JButton botoCancelar;
    private JPanel panelDialeg;
    private Client client;

    /**
     * Constructor per crear un diàleg per afegir un nou client.
     *
     * @param propietari El frame propietari del diàleg.
     * @param titol      El títol del diàleg.
     */
    public DialegClient(Frame propietari, String titol) {
        super(propietari, titol, true);
        inicialitzarComponents();
    }

    /**
     * Constructor per crear un diàleg per editar un client existent.
     *
     * @param propietari El frame propietari del diàleg.
     * @param titol      El títol del diàleg.
     * @param client     El client a editar.
     */
    public DialegClient(Frame propietari, String titol, Client client) {
        super(propietari, titol, true);
        inicialitzarComponents();
        textCodi.setText(String.valueOf(client.getCodi()));
        textCodi.setEditable(false); // El codi no es pot editar
        textNom.setText(client.getNom());
        textEmail.setText(client.getEmail());
        textTelefon.setText(client.getTelefon());
    }

    /**
     * Inicialitza els components gràfics del diàleg.
     */
    private void inicialitzarComponents() {
        setSize(300, 200);
        panelDialeg = new JPanel();
        panelDialeg.setLayout(new GridBagLayout());
        panelDialeg.setBorder(new EmptyBorder(10, 20, 10, 20));
        final JLabel label1 = new JLabel();
        label1.setText("Codi:");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelDialeg.add(label1, gbc);
        textCodi = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDialeg.add(textCodi, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Nom:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelDialeg.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Email:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelDialeg.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Telefon:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panelDialeg.add(label4, gbc);
        textNom = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDialeg.add(textNom, gbc);
        textEmail = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDialeg.add(textEmail, gbc);
        textTelefon = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDialeg.add(textTelefon, gbc);
        botoConfirmar = new JButton();
        botoConfirmar.setText("Confirmar");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDialeg.add(botoConfirmar, gbc);
        botoCancelar = new JButton();
        botoCancelar.setText("Cancelar");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDialeg.add(botoCancelar, gbc);
        botoConfirmar.addActionListener(_ -> confirmar());
        botoCancelar.addActionListener(_ -> cancelar());

        setContentPane(panelDialeg);
        setLocationRelativeTo(getOwner());
    }

    /**
     * Confirma l'entrada de dades i crea o actualitza un client.
     */
    public void confirmar() {
        try {
            int codi = Integer.parseInt(textCodi.getText());
            String nom = textNom.getText();
            String email = textEmail.getText();
            String telefon = textTelefon.getText();
            if (nom.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Els camps Nom i Email són obligatoris.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            client = new Client(codi, nom, email, telefon);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El codi ha de ser un número enter.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Cancel·la l'operació i tanca el diàleg.
     */
    public void cancelar() {
        client = null;
        dispose();
    }

    /**
     * Retorna el client creat o editat en el diàleg.
     *
     * @return El client.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Retorna el camp de text que conté el codi del client.
     *
     * @return El camp de text del codi.
     */
    public JTextField getTextCodi() {
        return textCodi;
    }

    /**
     * Retorna el camp de text que conté el nom del client.
     *
     * @return El camp de text del nom.
     */
    public JTextField getTextNom() {
        return textNom;
    }

    /**
     * Retorna el camp de text que conté el correu electrònic del client.
     *
     * @return El camp de text del correu electrònic.
     */
    public JTextField getTextEmail() {
        return textEmail;
    }

    /**
     * Retorna el camp de text que conté el telèfon del client.
     *
     * @return El camp de text del telèfon.
     */
    public JTextField getTextTelefon() {
        return textTelefon;
    }

    /**
     * Retorna el botó de confirmació.
     *
     * @return El botó de confirmació.
     */
    public JButton getBotoConfirmar() {
        return botoConfirmar;
    }

    /**
     * Retorna el botó de cancel·lar.
     *
     * @return El botó de cancel·lar.
     */
    public JButton getBotoCancelar() {
        return botoCancelar;
    }
}