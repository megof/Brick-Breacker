package brickBreaker;

import Final.Jugadores;
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
        super.paint(g);
        g.drawString(Jugadores.Jugador, 10, 20);
        g.drawString(""+ Tablero.puntaje, 10, 40);
        g.drawImage(img.getImage(), 250, 10, 30, 30, null);
        g.drawString("=  "+Tablero.vidas, 280, 30);
        repaint();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

}
