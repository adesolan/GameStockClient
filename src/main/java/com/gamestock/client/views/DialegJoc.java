package com.gamestock.client.views;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.gamestock.client.models.Joc;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The type Dialeg joc.
 */
public class DialegJoc extends JDialog {
    private JTextField textId;
    private JTextField textTitol;
    private JTextField textGenere;
    private JTextField textEstudi;
    private JTextField textPreuLloguer;
    private JTextField textStock;
    private JButton botoConfirmar;
    private JButton botoCancelar;
    private Joc joc;

    /**
     * Instantiates a new Dialeg joc.
     *
     * @param propietari the propietari
     * @param titol      the titol
     */
    public DialegJoc(Frame propietari, String titol) {
        super(propietari, titol, true);
        inicialitzarComponents();
    }

    /**
     * Instantiates a new Dialeg joc.
     *
     * @param propietari the propietari
     * @param titol      the titol
     * @param joc        the joc
     */
    public DialegJoc(Frame propietari, String titol, Joc joc) {
        this(propietari, titol);
        textId.setText(String.valueOf(joc.getId()));
        textId.setEditable(false);
        textTitol.setText(joc.getTitol());
        textGenere.setText(joc.getGenere());
        textEstudi.setText(joc.getEstudi());
        textPreuLloguer.setText(String.valueOf(joc.getPreuLloguer()));
        textStock.setText(String.valueOf(joc.getStock()));
    }

    private void inicialitzarComponents() {
        setSize(400, 300);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        // Camps
        addLabelAndField(panel, "ID:", textId = new JTextField(), 0, gbc);
        addLabelAndField(panel, "Títol:", textTitol = new JTextField(), 1, gbc);
        addLabelAndField(panel, "Gènere:", textGenere = new JTextField(), 2, gbc);
        addLabelAndField(panel, "Estudi:", textEstudi = new JTextField(), 3, gbc);
        addLabelAndField(panel, "Preu Lloguer:", textPreuLloguer = new JTextField(), 4, gbc);
        addLabelAndField(panel, "Stock:", textStock = new JTextField(), 5, gbc);

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
        try {
            int id = Integer.parseInt(textId.getText());
            String titol = textTitol.getText();
            String genere = textGenere.getText();
            String estudi = textEstudi.getText();
            double preuLloguer = Double.parseDouble(textPreuLloguer.getText());
            int stock = Integer.parseInt(textStock.getText());

            if (titol.isEmpty() || genere.isEmpty() || estudi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tots els camps són obligatoris.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            joc = new Joc(id, titol, genere, estudi, preuLloguer, stock);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID, Preu i Stock han de ser números vàlids.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Cancelar.
     */
    public void cancelar() {
        joc = null;
        dispose();
    }

    /**
     * Gets joc.
     *
     * @return the joc
     */
    public Joc getJoc() {
        return joc;
    }

    public JTextField getTextId() {
        return textId;
    }

    public void setTextId(JTextField textId) {
        this.textId = textId;
    }

    public JTextField getTextTitol() {
        return textTitol;
    }

    public void setTextTitol(JTextField textTitol) {
        this.textTitol = textTitol;
    }

    public JTextField getTextGenere() {
        return textGenere;
    }

    public void setTextGenere(JTextField textGenere) {
        this.textGenere = textGenere;
    }

    public JTextField getTextEstudi() {
        return textEstudi;
    }

    public void setTextEstudi(JTextField textEstudi) {
        this.textEstudi = textEstudi;
    }

    public JTextField getTextPreuLloguer() {
        return textPreuLloguer;
    }

    public void setTextPreuLloguer(JTextField textPreuLloguer) {
        this.textPreuLloguer = textPreuLloguer;
    }

    public JTextField getTextStock() {
        return textStock;
    }

    public void setTextStock(JTextField textStock) {
        this.textStock = textStock;
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