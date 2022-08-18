package gui;

import javax.swing.*;

import java.awt.*;

/**
 * Class to create the application window. Extends JFrame.
 */
public class Ventana extends JFrame {
    
    public JButton botonArchivoOrigen;
    public JButton botonArchivoDestino;
    public JButton botonCifrar;
    public JButton botonDecifrar;
    public JButton botonSalir;

    public JTextField campoArchivoOrigen;
    public JTextField campoArchivoDestino;
    
    public JPasswordField passwordField = new JPasswordField();

    /**
     * Constructor for class Ventana.<p>
     * For now it does not take parameters, all characteristics are hardcoded.
     */
    public Ventana () {
        //Constants
        final String titulo = "San Pedro - Quien tiene las llaves";
        final String APP_ICON_FILE = "keyicon.png";
        final String TEXTO_CABECERA = "Yo te daré las llaves del reino de los cielos.";
        final String TEXTO_BARRA_ESTADO = "Barra de progreso";
        final String ETIQUETA_CIFRAR = "Cifrar";
        final String ETIQUETA_DECIFRAR = "Descifrar";
        final String ETIQUETA_BOTON_ARCHIVO_ORIGEN = "Archivo Origen";
        final String ETIQUETA_BOTON_ARCHIVO_DESTINO = "Archivo Destino";
        final String ETIQUETA_BOTON_SALIR = "Salir";
        final Dimension PASSWORD_FIELD_DIMENSION = new Dimension(300, 25);
        final int WINDOW_X = 500;
        final int WINDOW_Y = 300;
        final int WINDOW_WIDTH = 600;
        final int WINDOW_HEIGHT = 200;

        final Color APP_BACKGROUND_COLOR = Color.MAGENTA;

        //Window settings
        this.setTitle(titulo);
        this.setLayout(new BorderLayout());
        this.setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(APP_BACKGROUND_COLOR);
        this.setIconImage(new ImageIcon(APP_ICON_FILE).getImage());

        //Panel initializations
        Panel cabecera = new Panel();
        Panel seleccionDeModo = new Panel();
        Panel seleccionArchivoOrigen = new Panel();
        Panel seleccionArchivoDestino = new Panel();
        Panel barraEstado = new Panel();

        //Password field initialization
        passwordField.setPreferredSize(PASSWORD_FIELD_DIMENSION);

        //Label initializations
        Etiqueta etiquetaCabecera = new Etiqueta(TEXTO_CABECERA);
        Etiqueta etiquetaBarraEstado = new Etiqueta(TEXTO_BARRA_ESTADO);
        
        //Button initializations
        this.botonArchivoOrigen = new JButton(ETIQUETA_BOTON_ARCHIVO_ORIGEN);
        this.botonArchivoDestino = new JButton(ETIQUETA_BOTON_ARCHIVO_DESTINO);
        this.botonCifrar = new JButton(ETIQUETA_CIFRAR);
        this.botonDecifrar = new JButton(ETIQUETA_DECIFRAR);
        this.botonSalir = new JButton(ETIQUETA_BOTON_SALIR);

        //Adding components to each panel
        cabecera.add(etiquetaCabecera);
        seleccionDeModo.add(passwordField);
        seleccionDeModo.add(botonCifrar);
        seleccionDeModo.add(botonDecifrar);
        seleccionDeModo.add(botonSalir);
        seleccionArchivoOrigen.add(botonArchivoOrigen);
        seleccionArchivoDestino.add(botonArchivoDestino);
        barraEstado.add(etiquetaBarraEstado);

        //Adding panels to each border
        this.add(cabecera, BorderLayout.NORTH);
        this.add(seleccionDeModo, BorderLayout.CENTER);
        this.add(seleccionArchivoOrigen, BorderLayout.WEST);
        this.add(seleccionArchivoDestino, BorderLayout.EAST);
        this.add(barraEstado, BorderLayout.SOUTH);
    }
}