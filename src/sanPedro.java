import gui.EventHandler;
import gui.Ventana;

public class sanPedro {
    
    static Ventana ventanaDeAplicacion;

    public static void main(String[] args) {
        ventanaDeAplicacion = new Ventana();
        ventanaDeAplicacion.setVisible(true);

        EventHandler logicHandler = new EventHandler(ventanaDeAplicacion);
        logicHandler.initialize();
    }
}