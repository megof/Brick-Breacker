package brickBreaker;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Informacion extends JPanel {

    final ImageIcon img;

    public Informacion(int width, int height) {
        super.setSize(500, 50);
        repaint();
        img = new ImageIcon(getClass().getResource("/Imagenes/Vidas.png"));
    }

    @Override
    public void paint(Graphics g) {
       if (Tablero.pausa.get() == false) {
            super.paint(g);
            g.drawString("00" + Tablero.puntaje, 10, 30);
            g.drawImage(img.getImage(), 250, 10, 30, 30, null);
            repaint();
        }
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
}
