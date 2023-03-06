package gui;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SpringLayout;

import java.awt.*;

public class Ventana extends JFrame {
    //Constants
    final String APP_TITLE = "San Pedro";
    final Point WINDOW_LOCATION = new Point(400, 200);
    final Dimension WINDOW_SIZE = new Dimension(400, 450);
    final Dimension TEXT_FIELD_DIMENSION = new Dimension(350, 25);
    final Border borde = BorderFactory.createLineBorder(new Color(50,20,100));
    
    //Window
    private SpringLayout layout = new SpringLayout();
    private Container area = this.getContentPane();
        
    //Components
    private ImageIcon app_icon = new ImageIcon("./images/keyicon.png");
    private JLabel eti_cabecera = new JLabel("...te entregare las llaves del reino de los cielos...");
    public PanelArchivo panelArchivoOrigen = new PanelArchivo("Archivo Origen");
    public PanelArchivo panelArchivoDestino = new PanelArchivo("Archivo Destino");
    public PanelComandos panelComandos = new PanelComandos();

    public Ventana () {
        setupWindow();
        setupComponents();
        addComponents();
        setupLayout();
    }

    private void setupWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(WINDOW_LOCATION);
        this.setSize(WINDOW_SIZE);
        this.setTitle(APP_TITLE);
        this.setIconImage(app_icon.getImage());

        this.setLayout(this.layout);
    }

    private void setupComponents() {
    }

    private void addComponents() {
        //Add components
        this.add(eti_cabecera);
        this.add(panelArchivoOrigen);
        this.add(panelArchivoDestino);
        this.add(panelComandos);
    }

    private void setupLayout() {
        layout.putConstraint(SpringLayout.NORTH, eti_cabecera, 10, SpringLayout.NORTH, area);
        layout.putConstraint(SpringLayout.WEST, eti_cabecera, 10, SpringLayout.WEST, area);

        layout.putConstraint(SpringLayout.NORTH, panelArchivoOrigen, 20, SpringLayout.SOUTH, eti_cabecera);
        layout.putConstraint(SpringLayout.WEST, panelArchivoOrigen, 10, SpringLayout.WEST, area);
        layout.putConstraint(SpringLayout.EAST, panelArchivoOrigen, -10, SpringLayout.EAST, area);

        layout.putConstraint(SpringLayout.NORTH, panelArchivoDestino, 20, SpringLayout.SOUTH, panelArchivoOrigen);
        layout.putConstraint(SpringLayout.WEST, panelArchivoDestino, 10, SpringLayout.WEST, area);
        layout.putConstraint(SpringLayout.EAST, panelArchivoDestino, -10, SpringLayout.EAST, area);

        layout.putConstraint(SpringLayout.NORTH, panelComandos, 20, SpringLayout.SOUTH, panelArchivoDestino);
        layout.putConstraint(SpringLayout.WEST, panelComandos, 10, SpringLayout.WEST, area);
        layout.putConstraint(SpringLayout.EAST, panelComandos, -10, SpringLayout.EAST, area);
    }
}
