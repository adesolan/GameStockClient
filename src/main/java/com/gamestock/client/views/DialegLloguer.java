package com.gamestock.client.views;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.gamestock.client.models.Lloguer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The type Dialeg lloguer.
 */
public class DialegLloguer extends JDialog {
    private JTextField textId;
    private JTextField textIdClient;
    private JTextField textIdJoc;
    private JTextField textDataLloguer;
    private JTextField textDataRetorn;
    private JTextField textPreu;
    private JCheckBox checkboxActiu;
    private JButton botoConfirmar;
    private JButton botoCancelar;
    private Lloguer lloguer;

    /**
     * Instantiates a new Dialeg lloguer.
     *
     * @param propietari the propietari
     * @param titol      the titol
     */
    public DialegLloguer(Frame propietari, String titol) {
        super(propietari, titol, true);
        inicialitzarComponents();
    }

    /**
     * Instantiates a new Dialeg lloguer.
     *
     * @param propietari the propietari
     * @param titol      the titol
     * @param lloguer    the lloguer
     */
    public DialegLloguer(Frame propietari, String titol, Lloguer lloguer) {
        this(propietari, titol);
        textId.setText(String.valueOf(lloguer.getId()));
        textId.setEditable(false);
        textIdClient.setText(String.valueOf(lloguer.getIdClient()));
        textIdJoc.setText(String.valueOf(lloguer.getIdJoc()));
        textDataLloguer.setText(lloguer.getDataLloguer());
        textDataRetorn.setText(lloguer.getDataRetorn());
        textPreu.setText(String.valueOf(lloguer.getPreu()));
        checkboxActiu.setSelected(lloguer.isActiu());
    }

    private void inicialitzarComponents() {
        setSize(400, 250);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        addLabelAndField(panel, "ID Lloguer:", textId = new JTextField(), 0, gbc);
        addLabelAndField(panel, "ID Client:", textIdClient = new JTextField(), 1, gbc);
        addLabelAndField(panel, "ID Joc:", textIdJoc = new JTextField(), 2, gbc);
        addLabelAndField(panel, "Data Lloguer:", textDataLloguer = new JTextField(), 3, gbc);
        addLabelAndField(panel, "Data Retorn:", textDataRetorn = new JTextField(), 4, gbc);
        addLabelAndField(panel, "Preu:", textPreu = new JTextField(), 5, gbc);
        addLabelAndCheckbox(panel, "Actiu:", checkboxActiu = new JCheckBox(), 6, gbc);

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
    
    private void addLabelAndCheckbox(JPanel panel, String text, JCheckBox checkbox, int row, GridBagConstraints gbc) {
        JLabel label = new JLabel(text);
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(checkbox, gbc);
    }

    /**
     * Confirmar.
     */
    public void confirmar() {
        try {
            long id = Long.parseLong(textId.getText());
            long idClient = Long.parseLong(textIdClient.getText());
            long idJoc = Long.parseLong(textIdJoc.getText());
            String dataLloguer = textDataLloguer.getText();
            String dataRetorn = textDataRetorn.getText();
            double preu = Double.parseDouble(textPreu.getText());
            boolean actiu = checkboxActiu.isSelected();

            if (dataLloguer.isEmpty() || dataRetorn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tots els camps són obligatoris.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            lloguer = new Lloguer(id, idClient, idJoc, dataLloguer, dataRetorn, preu, actiu);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID Client, ID Joc i Preu han de ser números vàlids.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Cancelar.
     */
    public void cancelar() {
        lloguer = null;
        dispose();
    }

    /**
     * Gets lloguer.
     *
     * @return the lloguer
     */
    public Lloguer getLloguer() {
        return lloguer;
    }

    public JTextField getTextId() {
        return textId;
    }

    public void setTextId(JTextField textId) {
        this.textId = textId;
    }

    public JTextField getTextIdClient() {
        return textIdClient;
    }

    public void setTextIdClient(JTextField textIdClient) {
        this.textIdClient = textIdClient;
    }

    public JTextField getTextIdJoc() {
        return textIdJoc;
    }

    public void setTextIdJoc(JTextField textIdJoc) {
        this.textIdJoc = textIdJoc;
    }

    public JTextField getTextDataLloguer() {
        return textDataLloguer;
    }

    public void setTextDataLloguer(JTextField textDataLloguer) {
        this.textDataLloguer = textDataLloguer;
    }

    public JTextField getTextDataRetorn() {
        return textDataRetorn;
    }

    public void setTextDataRetorn(JTextField textDataRetorn) {
        this.textDataRetorn = textDataRetorn;
    }

    public JTextField getTextPreu() {
        return textPreu;
    }

    public void setTextPreu(JTextField textPreu) {
        this.textPreu = textPreu;
    }

    public JCheckBox getCheckboxActiu() {
        return checkboxActiu;
    }

    public void setCheckboxActiu(JCheckBox checkboxActiu) {
        this.checkboxActiu = checkboxActiu;
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