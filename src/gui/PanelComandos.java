package gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class PanelComandos extends JPanel {
    public JButton botonCifrar = new JButton("Cifrar");
    public JButton botonDescifrar = new JButton("Descifrar");
    public JButton botonSalir = new JButton("Salir");
    private SpringLayout layout = new SpringLayout();

    public PanelComandos () {
        setupPanel();
        addComponents();
        setupLayout();       
    }    

    public void setupPanel() {
        this.setPreferredSize(new Dimension(500,110));
    }

    private void addComponents() {
        this.add(botonCifrar);
        this.add(botonDescifrar);
        this.add(botonSalir);
    }

    private void setupLayout() {
        this.setLayout(layout);

        layout.putConstraint(SpringLayout.NORTH, botonCifrar, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, botonCifrar, 10, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, botonDescifrar, 0, SpringLayout.NORTH, botonCifrar);
        layout.putConstraint(SpringLayout.WEST, botonDescifrar, 10, SpringLayout.EAST, botonCifrar);

        layout.putConstraint(SpringLayout.NORTH, botonSalir, 0, SpringLayout.NORTH, botonCifrar);
        layout.putConstraint(SpringLayout.WEST, botonSalir, 10, SpringLayout.EAST, botonDescifrar);
    }
}
