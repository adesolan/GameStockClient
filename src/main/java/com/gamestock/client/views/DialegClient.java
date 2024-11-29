package com.gamestock.client.views;

/**
 *
 * @author Avery Lopez Jordan
 */
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
    private JTextField textCognom1;
    private JTextField textCognom2;
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
        textCodi.setText(String.valueOf(client.getId()));
        textCodi.setEditable(false); // El codi no es pot editar
        textNom.setText(client.getNom());
        textCognom1.setText(client.getCognom1());
        textCognom2.setText(client.getCognom2());
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
        
        final JLabel label2 = new JLabel();
        label2.setText("Nom:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelDialeg.add(label2, gbc);
        
        final JLabel label3 = new JLabel();
        label3.setText("Cognom1:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelDialeg.add(label3, gbc);
        
        final JLabel label4 = new JLabel();
        label4.setText("Cognom2:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panelDialeg.add(label4, gbc);
        
        final JLabel label5 = new JLabel();
        label5.setText("Email:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panelDialeg.add(label5, gbc);
        
        final JLabel label6 = new JLabel();
        label6.setText("Telefon:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panelDialeg.add(label6, gbc);
        
        textCodi = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDialeg.add(textCodi, gbc);
        
        textNom = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDialeg.add(textNom, gbc);
        
        textCognom1 = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDialeg.add(textCognom1, gbc);
        
        textCognom2 = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDialeg.add(textCognom2, gbc);
        
        textEmail = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDialeg.add(textEmail, gbc);
        
        textTelefon = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDialeg.add(textTelefon, gbc);
        
        botoConfirmar = new JButton();
        botoConfirmar.setText("Confirmar");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        panelDialeg.add(botoConfirmar, gbc);
        botoCancelar = new JButton();
        botoCancelar.setText("Cancelar");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        panelDialeg.add(botoCancelar, gbc);
        botoConfirmar.addActionListener(e -> confirmar());
        botoCancelar.addActionListener(e -> cancelar());

        setContentPane(panelDialeg);
        setLocationRelativeTo(getOwner());
    }

    /**
     * Confirma l'entrada de dades i crea o actualitza un client.
     */
    public void confirmar() {
        try {
            long id = Integer.parseInt(textCodi.getText());
            String nom = textNom.getText();
            String cognom1 = textCognom1.getText();
            String cognom2 = textCognom2.getText();
            String email = textEmail.getText();
            String telefon = textTelefon.getText();
            if (nom.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Els camps Nom i Email són obligatoris.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            client = new Client(id, nom, cognom1, cognom2, email, telefon);
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

    /**
     * Gets text cognom 1.
     *
     * @return the text cognom 1
     */
    public JTextField getTextCognom1() {
        return textCognom1;
    }

    /**
     * Sets text cognom 1.
     *
     * @param textCognom1 the text cognom 1
     */
    public void setTextCognom1(JTextField textCognom1) {
        this.textCognom1 = textCognom1;
    }

    /**
     * Gets text cognom 2.
     *
     * @return the text cognom 2
     */
    public JTextField getTextCognom2() {
        return textCognom2;
    }

    /**
     * Sets text cognom 2.
     *
     * @param textCognom2 the text cognom 2
     */
    public void setTextCognom2(JTextField textCognom2) {
        this.textCognom2 = textCognom2;
    }

    /**
     * Gets panel dialeg.
     *
     * @return the panel dialeg
     */
    public JPanel getPanelDialeg() {
        return panelDialeg;
    }

    /**
     * Sets panel dialeg.
     *
     * @param panelDialeg the panel dialeg
     */
    public void setPanelDialeg(JPanel panelDialeg) {
        this.panelDialeg = panelDialeg;
    }
    
    
}