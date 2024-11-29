package com.gamestock.client.views;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.gamestock.client.models.Usuari;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The type Dialeg usuari.
 */
public class DialegUsuari extends JDialog {
    private JTextField textId;
    private JTextField textUsername;
    private JPasswordField textPassword;
    private JTextField textEmail;
    private JComboBox<String> comboRole;
    private JButton botoConfirmar;
    private JButton botoCancelar;
    private Usuari usuari;

    /**
     * Instantiates a new Dialeg usuari.
     *
     * @param propietari the propietari
     * @param titol      the titol
     */
    public DialegUsuari(Frame propietari, String titol) {
        super(propietari, titol, true);
        inicialitzarComponents();
    }

    /**
     * Instantiates a new Dialeg usuari.
     *
     * @param propietari the propietari
     * @param titol      the titol
     * @param usuari     the usuari
     */
    public DialegUsuari(Frame propietari, String titol, Usuari usuari) {
        this(propietari, titol);
        textId.setText(String.valueOf(usuari.getId()));
        textId.setEnabled(false);
        textUsername.setText(usuari.getUsername());
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
        addLabelAndField(panel, "ID:", textId = new JTextField(), 0, gbc);
        addLabelAndField(panel, "Nom d'usuari:", textUsername = new JTextField(), 1, gbc);
        addLabelAndField(panel, "Contrasenya:", textPassword = new JPasswordField(), 2, gbc);
        addLabelAndField(panel, "Correu electrònic:", textEmail = new JTextField(), 3, gbc);

        JLabel labelRole = new JLabel("Rol:");
        gbc.gridx = 0;
        gbc.gridy = 4;
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

    /**
     * Confirmar.
     */
    public void confirmar() {
        long id = Long.parseLong(textId.getText());
        String username = textUsername.getText();
        String password = new String(textPassword.getPassword());
        String email = textEmail.getText();
        String role = (String) comboRole.getSelectedItem();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tots els camps són obligatoris.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuari = new Usuari(id, username, password, email, role);
        dispose();
    }

    /**
     * Cancelar.
     */
    public void cancelar() {
        usuari = null;
        dispose();
    }

    /**
     * Gets usuari.
     *
     * @return the usuari
     */
    public Usuari getUsuari() {
        return usuari;
    }

    public JTextField getTextId() {
        return textId;
    }

    public void setTextId(JTextField textId) {
        this.textId = textId;
    }

    public JTextField getTextUsername() {
        return textUsername;
    }

    public void setTextUsername(JTextField textUsername) {
        this.textUsername = textUsername;
    }

    public JPasswordField getTextPassword() {
        return textPassword;
    }

    public void setTextPassword(JPasswordField textPassword) {
        this.textPassword = textPassword;
    }

    public JTextField getTextEmail() {
        return textEmail;
    }

    public void setTextEmail(JTextField textEmail) {
        this.textEmail = textEmail;
    }

    public JComboBox<String> getComboRole() {
        return comboRole;
    }

    public void setComboRole(JComboBox<String> comboRole) {
        this.comboRole = comboRole;
    }

    public JButton getBotoConfirmar() {
        return botoConfirmar;
    }

    public void setBotoConfirmar(JButton botoConfirmar) {
        this.botoConfirmar = botoConfirmar;
    }

    public JButton getBotoCancelar() {
        return botoCancelar;
    }

    public void setBotoCancelar(JButton botoCancelar) {
        this.botoCancelar = botoCancelar;
    }
    
    
}