package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;

public class PasswordDialog extends JDialog {
    
    private JLabel etiquetaCabecera;
    public JPasswordField passwordField;
    public JButton botonAceptar;
    public JButton botonCancelar;
    private SpringLayout layout = new SpringLayout();
    private Container area = this.getContentPane();

    public PasswordDialog (Frame owner, String title, boolean modality) {
        super(owner, title, modality);
        setupComponents();
        addComponents();
        setupLayout();
    }

    public void setupWindow() {
        int x = this.getOwner().getLocation().x + 30;
        int y = this.getOwner().getLocation().y + 60;
        
        this.setSize(new Dimension(250,150));
        this.setLocation(new Point(x, y));
    }

    private void setupComponents() {
        this.etiquetaCabecera = new JLabel("Password");
        this.passwordField = new JPasswordField();
        this.botonAceptar = new JButton("Aceptar");
        this.botonCancelar = new JButton("Cancelar");        
    }

    private void addComponents() {
        this.add(etiquetaCabecera);
        this.add(passwordField);
        this.add(botonAceptar);
        this.add(botonCancelar);
    }

    private void setupLayout() {
        this.setLayout(layout);

        layout.putConstraint(SpringLayout.NORTH, etiquetaCabecera, 10, SpringLayout.NORTH, area);
        layout.putConstraint(SpringLayout.WEST, etiquetaCabecera, 10, SpringLayout.WEST, area);

        layout.putConstraint(SpringLayout.NORTH, passwordField, 10, SpringLayout.SOUTH, etiquetaCabecera);
        layout.putConstraint(SpringLayout.WEST, passwordField, 10, SpringLayout.WEST, area);
        layout.putConstraint(SpringLayout.EAST, passwordField, -10, SpringLayout.EAST, area);

        layout.putConstraint(SpringLayout.NORTH, botonAceptar, 20, SpringLayout.SOUTH, passwordField);
        layout.putConstraint(SpringLayout.WEST, botonAceptar, 10, SpringLayout.WEST, area);
        
        layout.putConstraint(SpringLayout.NORTH, botonCancelar, 0, SpringLayout.NORTH, botonAceptar);
        layout.putConstraint(SpringLayout.WEST, botonCancelar, 10, SpringLayout.EAST, botonAceptar);
    }
}
