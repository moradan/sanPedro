package gui;

import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import java.io.File;

import crypto.Cryptographer;

public class EventHandler {
    
    private Ventana ventana;
    private Cryptographer cryptographer;
    private File archivoOrigen;
    private File archivoDestino;
    private PasswordDialog dialogoPassword;

    public EventHandler (Ventana ventana) {
        this.ventana = ventana;
        this.dialogoPassword = new PasswordDialog(ventana, "Password", true);
        this.dialogoPassword.botonAceptar.addActionListener(new BotonAceptarListener());
        this.dialogoPassword.botonCancelar.addActionListener(new BotonCancelarListener());
        //TODO add key listener for dialogoPassword so ENTER and ESCAPE confirm and cancel the dialog
        this.ventana.panelComandos.botonSalir.addActionListener(new BotonSalirListener());
        this.ventana.panelArchivoOrigen.botonExplorar.addActionListener(new BotonExplorarOrigenListener());
        this.ventana.panelArchivoOrigen.nombreArchivo.addFocusListener(new OrigenFocusListener());
        this.ventana.panelArchivoDestino.botonExplorar.addActionListener(new BotonExplorarDestinoListener());
        this.ventana.panelArchivoDestino.nombreArchivo.addFocusListener(new DestinoFocusListener());
        this.ventana.panelComandos.botonCifrar.addActionListener(new BotonCifrarListener());
        this.ventana.panelComandos.botonDescifrar.addActionListener(new BotonDescifrarListener());
    }

    public void initialize() {
        this.cryptographer = new Cryptographer();
        this.archivoOrigen = null;
        this.archivoDestino = null;

        ventana.panelArchivoOrigen.nombreArchivo.setText("");
        ventana.panelArchivoDestino.nombreArchivo.setText("");
    }

    private class BotonSalirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            salir();          
        }
    }

    private class BotonCifrarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cryptographer.encrypt = true;
            openPasswordDialog();
        }
    }


    private class BotonDescifrarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cryptographer.encrypt = false;
            openPasswordDialog();
        }
    }

    private class BotonExplorarOrigenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            
            archivoOrigen = fileChooser.getSelectedFile();
            if (archivoOrigen != null) {
                ventana.panelArchivoOrigen.nombreArchivo.setText(archivoOrigen.toPath().toString());
            }
            try {
                cryptographer.text = Files.readAllBytes(archivoOrigen.toPath());
            } catch (Exception exception) {}   
        }
    }

    private class BotonExplorarDestinoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);

            archivoDestino = fileChooser.getSelectedFile();
            if (archivoDestino != null) {
                ventana.panelArchivoDestino.nombreArchivo.setText(archivoDestino.toPath().toString());
            }
        }
    }

    private class OrigenFocusListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {    
        }

        @Override
        public void focusLost(FocusEvent e) { 
            //TODO cargar el contenido del arghivo origen a cryptographer cuando el campo origen pierde foco
        }
    }

    private class DestinoFocusListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {            
        }

        @Override
        public void focusLost(FocusEvent e) {            
            //TODO asignar el archivo de destino al cryptographer cuando el campo archivoDestino pierde foco
        }

    }

    private class BotonAceptarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            processPassword();
        }
    }

    private class BotonCancelarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cancelPassword();
        }
    }
    
    //TODO Key events are generated from the password field and we need a way to cascade it up the hierarchy
    /* 
    private class PasswordTeclasListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                processPassword();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                cancelPassword();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {            
        }
    } */

    private void openPasswordDialog() {
        this.dialogoPassword.setupWindow();
        this.dialogoPassword.setVisible(true);
    }

    private void processPassword() {
        dialogoPassword.passwordField.requestFocusInWindow();
        dialogoPassword.setVisible(false);
        String password = new String(dialogoPassword.passwordField.getPassword());
        if (password.equals("")) {
            System.out.println("No password error");
        } else {
            dialogoPassword.passwordField.setText("");
            executeCipher(password);
        }
    }

    private void cancelPassword() {
        dialogoPassword.passwordField.setText("");
        dialogoPassword.passwordField.requestFocusInWindow();
        dialogoPassword.setVisible(false);
        System.out.println("Cipher cancelled");
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


    private void salir() {
        System.exit(0);
    }
}
