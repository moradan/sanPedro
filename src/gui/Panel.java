package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class Panel extends JPanel {
    
    public Panel (Color c) {
        this.setBackground(c);
    }

    public Panel () {
        final Color APP_BACKGROUND_COLOR = Color.DARK_GRAY;

        Border border = BorderFactory.createEtchedBorder();
        this.setBorder(border);
        this.setBackground(APP_BACKGROUND_COLOR);
    }

}
