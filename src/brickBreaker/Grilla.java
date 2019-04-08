package brickBreaker;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Grilla extends Juego implements Constantes {

    private int color;
    private boolean destruido = false;
    private final ImageIcon img1, img2, img3, img4;

    public Grilla(int x, int y, int width, int height, int color) {
        super(x, y, width, height);
        this.color = color;
        img1 = new ImageIcon(getClass().getResource("/Imagenes/Ladrillo1.png"));
        img2 = new ImageIcon(getClass().getResource("/Imagenes/Ladrillo2.png"));
        img3 = new ImageIcon(getClass().getResource("/Imagenes/Ladrillo3.png"));
        img4 = new ImageIcon(getClass().getResource("/Imagenes/Ladrillo4.png"));
    }

    public void paint(Graphics g) {
        golpe();
        if (color >= 1 && color <= 4) {
            switch (color) {
                case 1:
                    g.drawImage(img1.getImage(), x, y, width, height, null);
                    break;
                case 2:
                    g.drawImage(img2.getImage(), x, y, width, height, null);
                    break;
                case 3:
                    g.drawImage(img3.getImage(), x, y, width, height, null);
                    break;
                case 4:
                    g.drawImage(img4.getImage(), x, y, width, height, null);
                    break;
            }
        }
    }

    public void golpe() {
        if (color == 0) {
            destruido = true;
            Tablero.puntaje += 50;
            color = -1;
        }
        if(color == -1){
            destruido = true;
            color = -2;
        }
    }

    public int getColor() {
        return color;
    }

    public boolean golpeArriba(int bolaX, int bolaY) {
        if ((bolaX >= x) && (bolaX <= x + width + 4) && (bolaY + 9 == y) && (destruido == false)) {
            if (color < 4) {
                color--;
            }
            return true;
        }
        return false;
    }

    public boolean golpeAbajo(int bolaX, int bolaY) {
        if ((bolaX >= x) && (bolaX <= x + width + 4) && (bolaY - 9 == y + height) && (destruido == false)) {
            if (color < 4) {
                color--;
            }
            return true;
        }
        return false;
    }

    public boolean golpeDerecha(int bolaX, int bolaY) {
        if ((bolaY >= y) && (bolaY <= y + height + 4) && (bolaX - 9 == x + width) && (destruido == false)) {
            if (color < 4) {
                color--;
            }
            return true;
        }
        return false;
    }

    public boolean golpeIzquierda(int bolaX, int bolaY) {
        if ((bolaY >= y) && (bolaY <= y + height + 4) && (bolaX + 9 == x) && (destruido == false)) {
            if (color < 4) {
                color--;
            }
            return true;
        }
        return false;
    }

    public boolean golpeEsquinaAD(int bolaX, int bolaY) {
        if ((bolaY - 5 >= y + height - 9) && (bolaY - 5 <= y + height)
                && (bolaX - 5 >= x + width - 9) && (bolaX - 5 <= x + width) && (destruido == false)) {
            if (color < 4) {
                color--;
            }
            return true;
        }
        return false;
    }

    public boolean golpeEsquinaAI(int bolaX, int bolaY) {
        if ((bolaY - 5 >= y + height - 9) && (bolaY - 5 <= y + height)
                && (bolaX + 5 >= x) && (bolaX + 5 <= x + 9) && (destruido == false)) {
            if (color < 4) {
                color--;
            }
            return true;
        }
        return false;
    }

    public boolean golpeEsquinaArD(int bolaX, int bolaY) {
        if ((bolaY + 5 >= y) && (bolaY + 5 <= y + 9) && (bolaX - 5 >= x + width - 9)
                && (bolaX - 5 <= x + width) && (destruido == false)) {
            if (color < 4) {
                color--;
            }
            return true;
        }
        return false;
    }

    public boolean golpeEsquinaArI(int bolaX, int bolaY) {
        if ((bolaY + 5 >= y - 9) && (bolaY + 5 <= y)
                && (bolaX + 5 >= x) && (bolaX + 5 <= x + 9) && (destruido == false)) {
            if (color < 4) {
                color--;
            }
            return true;
        }
        return false;
    }
}
