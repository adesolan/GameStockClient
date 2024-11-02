package com.gamestock.client.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe que representa la pantalla de login.
 */
public class PantallaLogin extends JFrame {
    private JTextField textUsuari;
    private JPasswordField textContrasenya;
    private JButton botoLogin;
    public JPanel panelLogin;

    /**
     * Constructor de la classe PantallaLogin.
     * Configura la interfície gràfica i inicialitza els components de la pantalla de login.
     */
    public PantallaLogin() {
        panelLogin = new JPanel();
        panelLogin.setLayout(new GridBagLayout());
        panelLogin.setBorder(new EmptyBorder(10, 20, 10, 20));
        textUsuari = new JTextField();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelLogin.add(textUsuari, gbc);
        textContrasenya = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelLogin.add(textContrasenya, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Usuari:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelLogin.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Contrasenya:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelLogin.add(label2, gbc);
        botoLogin = new JButton();
        botoLogin.setText("Login");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelLogin.add(botoLogin, gbc);


    }

    /**
     * Assigna un ActionListener al botó de login.
     *
     * @param listener L'ActionListener que s'executarà quan es premi el botó de login.
     */
    public void afegirAccioLogin(ActionListener listener) {
        botoLogin.addActionListener(listener);
    }

    /**
     * Recupera el botó de login.
     *
     * @return el botó de login.
     */
    public JButton getBotoLogin() {
        return botoLogin;
    }

    /**
     * Recupera el panell de login.
     *
     * @return el panell de login.
     */
    public JPanel getPanelLogin() {
        return panelLogin;
    }

    /**
     * Recupera el camp d'usuari.
     *
     * @return el camp d'usuari com a JTextField.
     */
    public JTextField getTextUsuari() {
        return textUsuari;
    }

    /**
     * Recupera el nom d'usuari introduït en el camp de text.
     *
     * @return el nom d'usuari com a cadena de text.
     */
    public String getUsuari() {
        return textUsuari.getText();
    }

    /**
     * Recupera la contrasenya introduïda en el camp de contrasenya.
     *
     * @return la contrasenya com a cadena de text.
     */
    public String getContrasenya() {
        return new String(textContrasenya.getPassword());
    }

    /**
     * Recupera el camp de contrasenya.
     *
     * @return el camp de contrasenya com a JPasswordField.
     */
    public JPasswordField getTextContrasenya() {
        return textContrasenya;
    }
}