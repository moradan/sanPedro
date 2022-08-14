package gui;

import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.swing.JFileChooser;
import java.io.File;

import crypto.Cryptographer;

public class EventHandler implements ActionListener {

    private Ventana ventana;
    private Cryptographer cryptographer;
    private File archivoOrigen;
    private File archivoDestino;

    public EventHandler (Ventana ventana, Cryptographer cryptographer) {
        this.ventana = ventana;
        this.cryptographer = cryptographer;
        this.ventana.botonSalir.addActionListener(this);
        this.ventana.botonArchivoOrigen.addActionListener(this);
        this.ventana.botonArchivoDestino.addActionListener(this);
        this.ventana.botonCifrar.addActionListener(this);
        this.ventana.botonDecifrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventana.botonSalir) {
            System.exit(0);
        }

        if (e.getSource() == ventana.botonArchivoOrigen) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            
            this.archivoOrigen = fileChooser.getSelectedFile();
            try {
                cryptographer.text = Files.readAllBytes(this.archivoOrigen.toPath());
            } catch (Exception exception) {}
        }

        if (e.getSource() == ventana.botonArchivoDestino) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);

            this.archivoDestino = fileChooser.getSelectedFile();
        }

        if (e.getSource() == ventana.botonCifrar) {
            cryptographer.encrypt = true;
            executeCipher();
        }

        if (e.getSource() == ventana.botonDecifrar) {
            cryptographer.encrypt = false;
            executeCipher();
        }
    }

    private void executeCipher() {
        char[] password = ventana.passwordField.getPassword();
        cryptographer.password = password.toString();

        if (cryptographer.password.length() == 0) {
            System.out.println("Password issue");
        } else if (cryptographer.text.length == 0) {
            System.out.println("Plain text not loaded");
        } else {
            System.out.println("Inicializando cifrado");
            cryptographer.initialize();
            System.out.println("Procesando cifrado");
            cryptographer.processCipher();
            try {
                Files.write(archivoDestino.toPath(), cryptographer.getOutput(), StandardOpenOption.CREATE);
                System.out.println("Archivo generado");
            } catch (Exception exception) {}
        }
    }    
}
