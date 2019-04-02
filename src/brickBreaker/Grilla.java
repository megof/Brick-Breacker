package brickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Grilla extends Juego implements Constantes {

    int color;
    private boolean destruido = false;
    private final ImageIcon img1,img2,img3,img4;

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
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 5; j++) {
                    switch (color) {
                        case 1:
                            g.drawImage(img1.getImage(),x , y, width,height,null );
                            break;
                        case 2:
                            g.drawImage(img2.getImage(),x , y, width,height,null );
                            break;
                        case 3:
                            g.drawImage(img3.getImage(),x , y, width,height,null );
                            break;
                        case 4:
                            g.drawImage(img4.getImage(),x , y, width,height,null );
                            break;
                    }
                }
            }
        }
    }

    public void golpe() {
        if (color == 0) {
            destruido = true;
            Tablero.puntaje += 50;
            color = -1;
            Tablero.ladriTotal--;
        }
    }

    public boolean golpeArriba(int bolaX, int bolaY) {
        return (bolaX >= x) && (bolaX <= x + width + 5) && (bolaY + 9 == y) && (destruido == false);
    }

    public boolean golpeAbajo(int bolaX, int bolaY) {
        return (bolaX >= x) && (bolaX <= x + width + 5) && (bolaY - 9 == y + height) && (destruido == false);
    }

    public boolean golpeDerecha(int bolaX, int bolaY) {
        return (bolaY >= y) && (bolaY <= y + height + 5) && (bolaX - 9 == x + width) && (destruido == false);
    }

    public boolean golpeIzquierda(int bolaX, int bolaY) {
        return (bolaY >= y) && (bolaY <= y + height + 5) && (bolaX + 9 == x) && (destruido == false);
    }

    public boolean golpeEsquinaAD(int bolaX, int bolaY) {
        return (bolaY - 6 >= y + height - 9) && (bolaY - 6 <= y + height + 2)
                && (bolaX - 6 >= x + width - 9) && (bolaX - 6 <= x + width + 2) && (destruido == false);
    }

    public boolean golpeEsquinaAI(int bolaX, int bolaY) {
        return (bolaY - 6 >= y + height - 9) && (bolaY - 6 <= y + height + 2)
                && (bolaX + 6 >= x - 2) && (bolaX + 6 <= x + 9) && (destruido == false);
    }

    public boolean golpeEsquinaArD(int bolaX, int bolaY) {
        return (bolaY + 6 >= y - 2) && (bolaY + 6 <= y + 9) && (bolaX - 6 >= x + width - 9)
                && (bolaX - 6 <= x + width + 2) && (destruido == false);
    }

    public boolean golpeEsquinaArI(int bolaX, int bolaY) {
        return (bolaY + 6 <= y - 9) && (bolaY + 6 <= y + 2)
                && (bolaX + 6 >= x - 2) && (bolaX + 6 <= x + 9) && (destruido == false);
    }
}
