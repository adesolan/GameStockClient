package com.gamestock.client.views;

import com.gamestock.client.models.Usuari;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DialegUsuari extends JDialog {
    private JTextField textUsername;
    private JPasswordField textPassword;
    private JTextField textEmail;
    private JComboBox<String> comboRole;
    private JButton botoConfirmar;
    private JButton botoCancelar;
    private Usuari usuari;

    public DialegUsuari(Frame propietari, String titol) {
        super(propietari, titol, true);
        inicialitzarComponents();
    }

    public DialegUsuari(Frame propietari, String titol, Usuari usuari) {
        this(propietari, titol);
        textUsername.setText(usuari.getUsername());
        textUsername.setEditable(false); // No permetre canviar el nom d'usuari
        textPassword.setText(usuari.getPassword());
        textEmail.setText(usuari.getEmail());
        comboRole.setSelectedItem(usuari.getRole());
    }

    private void inicialitzarComponents() {
        setSize(400, 250);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        // Camps del formulari
        addLabelAndField(panel, "Nom d'usuari:", textUsername = new JTextField(), 0, gbc);
        addLabelAndField(panel, "Contrasenya:", textPassword = new JPasswordField(), 1, gbc);
        addLabelAndField(panel, "Correu electrònic:", textEmail = new JTextField(), 2, gbc);

        JLabel labelRole = new JLabel("Rol:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(labelRole, gbc);

        comboRole = new JComboBox<>(new String[]{"admin", "usuari"});
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboRole, gbc);

        // Botons
        botoConfirmar = new JButton("Confirmar");
        botoCancelar = new JButton("Cancelar");
        JPanel botoPanel = new JPanel();
        botoPanel.add(botoConfirmar);
        botoPanel.add(botoCancelar);

        botoConfirmar.addActionListener(e -> confirmar());
        botoCancelar.addActionListener(e -> cancelar());

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(botoPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(getOwner());
    }

    private void addLabelAndField(JPanel panel, String text, JTextField field, int row, GridBagConstraints gbc) {
        JLabel label = new JLabel(text);
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, gbc);
    }

    public void confirmar() {
        String username = textUsername.getText();
        String password = new String(textPassword.getPassword());
        String email = textEmail.getText();
        String role = (String) comboRole.getSelectedItem();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tots els camps són obligatoris.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuari = new Usuari(username, password, email, role);
        dispose();
    }

    public void cancelar() {
        usuari = null;
        dispose();
    }

    public Usuari getUsuari() {
        return usuari;
    }
}