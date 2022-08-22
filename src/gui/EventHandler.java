package gui;

import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import java.io.File;

import crypto.Cryptographer;

public class EventHandler implements ActionListener {
    
    private Ventana ventana;
    private Cryptographer cryptographer;
    private File archivoOrigen;
    private File archivoDestino;
    private PasswordDialog dialogoPassword;

    public EventHandler (Ventana ventana) {
        this.ventana = ventana;
        this.dialogoPassword = new PasswordDialog(ventana, "Password", true);
        this.dialogoPassword.botonAceptar.addActionListener(this);
        this.dialogoPassword.botonCancelar.addActionListener(this);
        this.ventana.panelComandos.botonSalir.addActionListener(this);
        this.ventana.panelArchivoOrigen.botonExplorar.addActionListener(this);
        this.ventana.panelArchivoDestino.botonExplorar.addActionListener(this);
        this.ventana.panelComandos.botonCifrar.addActionListener(this);
        this.ventana.panelComandos.botonDescifrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == ventana.panelComandos.botonSalir) {
            salir();
        } else if (e.getSource() == ventana.panelArchivoOrigen.botonExplorar) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            

            this.archivoOrigen = fileChooser.getSelectedFile();
            ventana.panelArchivoOrigen.nombreArchivo.setText(this.archivoOrigen.toPath().toString());
            try {
                cryptographer.text = Files.readAllBytes(this.archivoOrigen.toPath());
            } catch (Exception exception) {}
        } else if (e.getSource() == ventana.panelArchivoDestino.botonExplorar) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);

            this.archivoDestino = fileChooser.getSelectedFile();
            ventana.panelArchivoDestino.nombreArchivo.setText(this.archivoDestino.toPath().toString());
        } else {
            if (e.getSource() == ventana.panelComandos.botonCifrar) {
                cryptographer.encrypt = true;
            } else if (e.getSource() == ventana.panelComandos.botonDescifrar) {
                cryptographer.encrypt = false;
            }
            openPasswordDialog();
        }

        if (e.getSource() == dialogoPassword.botonAceptar) {
            dialogoPassword.setVisible(false);
            String password = new String(dialogoPassword.passwordField.getPassword());
            if (password.equals("")) {
                System.out.println("No password error");
            } else {
                dialogoPassword.passwordField.setText("");
                executeCipher(password);
            }
        } else if (e.getSource() == dialogoPassword.botonCancelar) {
            dialogoPassword.setVisible(false);
            System.out.println("Cipher cancelled");
        }
    }

    private void openPasswordDialog() {
        this.dialogoPassword.setVisible(true);
    }

    private void executeCipher(String password) {
        this.cryptographer.password = password;

         if (cryptographer.text == null) {
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
        } else System.out.println("No se eligio un archivo de destino");
        this.initialize();
    } 

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
