package gui;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class PanelArchivo extends JPanel {
    
    private JLabel etiquetaCabecera;
    public JTextField nombreArchivo = new JTextField();
    public JButton botonExplorar = new JButton("Explorar");
    private SpringLayout layout = new SpringLayout();

    public PanelArchivo (String encabezado) {
        setupComponents(encabezado);
        addComponents();
        setupLayout();
    }

    private void setupComponents(String encabezado) {
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(500,110));
        this.setBorder(BorderFactory.createEtchedBorder());
        this.etiquetaCabecera = new JLabel(encabezado);
    }

    private void addComponents() {
        this.add(etiquetaCabecera);
        this.add(nombreArchivo);
        this.add(botonExplorar);
    }

    private void setupLayout() {
        layout.putConstraint(SpringLayout.NORTH, etiquetaCabecera, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiquetaCabecera, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nombreArchivo, 10, SpringLayout.SOUTH, etiquetaCabecera);
        layout.putConstraint(SpringLayout.WEST, nombreArchivo, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, nombreArchivo, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, botonExplorar, 10, SpringLayout.SOUTH, nombreArchivo);
        layout.putConstraint(SpringLayout.WEST, botonExplorar, 10, SpringLayout.WEST, this);
    }
}
