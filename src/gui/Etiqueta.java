package gui;

import java.awt.Color;

import javax.swing.JLabel;

public class Etiqueta extends JLabel {
    public Etiqueta (String text) {
        this.setText(text);
        this.setForeground(Color.white);
    }

    public Etiqueta() {
        this.setForeground(Color.white);
    }
}
