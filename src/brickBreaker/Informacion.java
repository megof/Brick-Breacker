package brickBreaker;

import Final.Jugadores;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Informacion extends JPanel implements Constantes {

    private final ImageIcon img, fondo, poder;
    private final Font fuente;
    private int width, height;

    public Informacion(int width, int height) {
        super.setSize(width, height);
        this.width = width;
        this.height = height;
        img = new ImageIcon(getClass().getResource("/Imagenes/Vidas.png"));
        fondo = new ImageIcon(getClass().getResource("/Imagenes/FondoInfo.jpg"));
        poder = new ImageIcon(getClass().getResource("/Imagenes/1.png"));
        fuente = new Font("MS Gothic", Font.BOLD, 20);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(fondo.getImage(), 0, 0, width, height, null);
        g.setFont(fuente);
        g.setColor(Color.WHITE);
        g.drawString(Jugadores.Jugador, 10, 20);
        g.drawString("Score: " + Tablero.puntaje, 10, 40);
        g.drawImage(img.getImage(), 200, 10, 30, 30, null);
        g.drawString(" X " + Tablero.vidas, 230, 35);
        g.drawImage(poder.getImage(), 350, 10, 30, 30, null);
        if (Tablero.cant == 0) {
            g.drawString(Tablero.poder, 400, 35);
        } else {
            g.drawString(Tablero.poder + " por " + Tablero.cant, 400, 35);
        }
        g.drawString("Tiempo: "+Tablero.tiempo,550,35);
        repaint();
    }
}
