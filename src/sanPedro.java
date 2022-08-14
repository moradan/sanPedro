import gui.EventHandler;
import gui.Ventana;

import crypto.Cryptographer;

public class sanPedro {
    
    static Ventana ventanaDeAplicacion;

    public static void main(String[] args) {
        Cryptographer cryptographer = new Cryptographer();
        ventanaDeAplicacion = new Ventana();
        ventanaDeAplicacion.setVisible(true);

        EventHandler eventHandler = new EventHandler(ventanaDeAplicacion, cryptographer);
    }
}