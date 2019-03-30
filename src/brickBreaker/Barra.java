package brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

public class Barra extends Juego implements Constantes {

    public Barra(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void paint(Graphics g) {
        g.setColor(new Color(255, 92, 41 ));
        g.fillRect(x, y, width, height);
    }

    public boolean golpeArriba(int bolaX, int bolaY) {
        return (bolaX + BOLA_RADIO >= x) && (bolaX <= x + width) && (bolaY + BOLA_RADIO == y);
    }

    public boolean golpeDerecha(int bolaX, int bolaY) {
        return (bolaX == x + width) && ((bolaY + BOLA_RADIO >= y) && (bolaY <= y + height));
    }

    public boolean golpeIzquierda(int bolaX, int bolaY) {
        return (bolaX + BOLA_RADIO == x) && ((bolaY + BOLA_RADIO >= y) && (bolaY <= y + height));
    }
}
