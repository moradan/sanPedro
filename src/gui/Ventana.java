package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Class to create the application window. Extends JFrame.
 */
public class Ventana extends JFrame {
    
    /**
     * Constructor for class Ventana.<p>
     * For now it does not take parameters, all characteristics are hardcoded.
     */
    public Ventana () {
        final String titulo = "San Pedro - Quien tiene las llaves";
        final String APP_ICON_FILE = "keyicon.png";
        final String TEXTO_CABECERA = "Yo te daré las llaves del reino de los cielos.";
        final String TEXTO_MODO_OPERACION = "Cifrar o Descifrar?";
        final String TEXTO_ARCHIVO_ORIGEN = "Elija el archivo de origen";
        final String TEXTO_ARCHIVO_DESTINO = "Elija el archivo de destino";
        final String TEXTO_BARRA_ESTADO = "Barra de progreso";
        final Color APP_BACKGROUND_COLOR = Color.MAGENTA;

        this.setTitle(titulo);
        this.setLayout(new BorderLayout());
        this.setBounds(500, 200, 600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(APP_BACKGROUND_COLOR);
        this.setIconImage(new ImageIcon(APP_ICON_FILE).getImage());

        Panel cabecera = new Panel();
        Panel seleccionDeModo = new Panel();
        Panel seleccionArchivoOrigen = new Panel();
        Panel seleccionArchivoDestino = new Panel();
        Panel barraEstado = new Panel();

        Etiqueta etiquetaCabecera = new Etiqueta(TEXTO_CABECERA);
        Etiqueta etiquetaDeModo = new Etiqueta(TEXTO_MODO_OPERACION);
        Etiqueta etiquetaArchivoOrigen = new Etiqueta(TEXTO_ARCHIVO_ORIGEN);
        Etiqueta etiquetaArchivoDestino = new Etiqueta(TEXTO_ARCHIVO_DESTINO);
        Etiqueta etiquetaBarraEstado = new Etiqueta(TEXTO_BARRA_ESTADO);

        cabecera.add(etiquetaCabecera);
        seleccionDeModo.add(etiquetaDeModo);
        seleccionArchivoOrigen.add(etiquetaArchivoOrigen);
        seleccionArchivoDestino.add(etiquetaArchivoDestino);
        barraEstado.add(etiquetaBarraEstado);

        this.add(cabecera, BorderLayout.NORTH);
        this.add(seleccionDeModo, BorderLayout.CENTER);
        this.add(seleccionArchivoOrigen, BorderLayout.WEST);
        this.add(seleccionArchivoDestino, BorderLayout.EAST);
        this.add(barraEstado, BorderLayout.SOUTH);
    }
}