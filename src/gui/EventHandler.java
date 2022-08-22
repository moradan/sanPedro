package gui;

import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.swing.JFileChooser;
import java.io.File;

import crypto.Cryptographer;

public class EventHandler implements ActionListener {

    public enum Status {
        INICILIZADO,
        FINALIZADO
    }
    
    private Ventana ventana;
    private Cryptographer cryptographer;
    private File archivoOrigen;
    private File archivoDestino;
    private Status status = Status.FINALIZADO;

    public EventHandler (Ventana ventana) {
        this.ventana = ventana;
        this.ventana.panelComandos.botonSalir.addActionListener(this);
        this.ventana.panelArchivoOrigen.botonExplorar.addActionListener(this);
        this.ventana.panelArchivoDestino.botonExplorar.addActionListener(this);
        this.ventana.panelComandos.botonCifrar.addActionListener(this);
        this.ventana.panelComandos.botonDescifrar.addActionListener(this);
    }

    /* 
    public void reiniciar (Cryptographer cryptographer) {
        this.cryptographer = cryptographer;

        this.archivoOrigen = null;
        this.archivoDestino = null;

        this.status = Status.INICILIZADO;
    } */

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == ventana.panelComandos.botonSalir) {
            salir();
        }

        
        if (e.getSource() == ventana.panelArchivoOrigen.botonExplorar) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            

            this.archivoOrigen = fileChooser.getSelectedFile();
            ventana.panelArchivoOrigen.nombreArchivo.setText(this.archivoOrigen.toPath().toString());
            try {
                cryptographer.text = Files.readAllBytes(this.archivoOrigen.toPath());
            } catch (Exception exception) {}
        } 

        
        if (e.getSource() == ventana.panelArchivoDestino.botonExplorar) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);

            this.archivoDestino = fileChooser.getSelectedFile();
            ventana.panelArchivoDestino.nombreArchivo.setText(this.archivoDestino.toPath().toString());
        } 

        if (e.getSource() == ventana.panelComandos.botonCifrar) {
            cryptographer.encrypt = true;
            executeCipher();
        } 

        if (e.getSource() == ventana.panelComandos.botonDescifrar) {
            cryptographer.encrypt = false;
            executeCipher();
        }
    }

    private void executeCipher() {
        System.out.println(cryptographer.encrypt);
        //Call password modal pop-up
        
        /*
        String password = new String(ventana.passwordField.getPassword());
        this.cryptographer.password = password;

        if (password.length() == 0) {
            System.out.println("Password issue");
        } else if (cryptographer.text == null) {
            System.out.println("Plain text not loaded");
        } else if (archivoDestino != null) {
            System.out.println("Inicializando cifrado");
            cryptographer.initialize();
            System.out.println("Procesando cifrado");
            cryptographer.processCipher();

            try {
                Files.write(archivoDestino.toPath(), cryptographer.getOutput(), StandardOpenOption.CREATE);
                System.out.println("Archivo generado");
            } catch (Exception exception) {}
            
            this.initialize();
        } else System.out.println("No se eligio un archivo de destino"); */
        this.initialize();
    } 

    /*
    public Status getStatus() {
        return this.status;
    } */

    public void initialize() {
        this.cryptographer = new Cryptographer();
        this.archivoOrigen = null;
        this.archivoDestino = null;

        ventana.panelArchivoOrigen.nombreArchivo.setText("");
        ventana.panelArchivoDestino.nombreArchivo.setText("");
    }

    private void salir() {
        System.exit(0);
    }
}
