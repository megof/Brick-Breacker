package brickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Barra extends Juego implements Constantes {
    
    private final ImageIcon img;
    
    public Barra(int x, int y, int width, int height) {
        super(x, y, width, height);
        img = new ImageIcon(getClass().getResource("/Imagenes/barra.png")); 
    }
    
    public boolean caughtItem(Poderes i) {
		if ((i.getX() < x + width) && (i.getX() + i.getWidth() > x) && (y == i.getY() || y == i.getY() - 1)) {
                    i.poder(this);
			return true;
		}
		return false;
	}

    public void paint(Graphics g) {
        g.drawImage(img.getImage(),x , y, width,height,null );
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
