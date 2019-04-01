package brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

public class Grilla extends Juego implements Constantes {

    private int color;
    private boolean destruido = false;
    
    private final Informacion info;

    public Grilla(int x, int y, int width, int height, int color) {
        super ( x, y, width, height );
        this.color = color;
    }

    public void paint(Graphics g) {
        golpe();
        if (color >= 1 && color <= 3) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 5; j++) {
                    switch (color) {
                        case 1:
                            g.setColor ( new Color ( 14, 255, 0 ) );
                            g.fillRect ( x, y, width, height );
                            break;
                        case 2:
                            g.setColor ( new Color ( 21, 0, 255 ) );
                            g.fillRect ( x, y, width, height );
                            break;
                        case 3:
                            g.setColor ( new Color ( 255, 26, 0 ) );
                            g.fillRect ( x, y, width, height );
                            break;
                    }
                }
            }
        }
    }

    public void golpe() {
        if (color==0){
            destruido=true;
            Tablero.puntaje += 50;
            color = -1;
        }
    }
    
    public boolean golpeArriba(int bolaX, int bolaY) {
        return (bolaX >= x) && (bolaX <= x + width + 5) && (bolaY + 10 == y) && (destruido == false);
    }

    public boolean golpeAbajo(int bolaX, int bolaY) {
        return (bolaX >= x) && (bolaX <= x + width + 5) && (bolaY - 10 == y + height) && (destruido == false);
    }

    public boolean golpeDerecha(int bolaX, int bolaY) {
        return (bolaY >= y) && (bolaY <= y + height) && (bolaX - 10 == x + width) && (destruido == false);
    }

    public boolean golpeIzquierda(int bolaX, int bolaY) {
        return (bolaY >= y) && (bolaY <= y + height) && (bolaX + 10 == x) && (destruido == false);
    }

    public boolean golpeEsquinaAD(int bolaX, int bolaY) {
        return (bolaY - 6 >= y + height - 9) && (bolaY - 6 <= y + height + 2)
                && (bolaX - 6 >= x + width - 9) && (bolaX - 6 <= x + width + 2) && (destruido == false);
    }

    public boolean golpeEsquinaAI(int bolaX, int bolaY) {
        return (bolaY - 6 >= y + height) && (bolaY - 6 <= y + height + 2)
                && (bolaX + 6 >= x - 9) && (bolaX + 6 <= x + 2 ) && (destruido == false);
    }

    public boolean golpeEsquinaArD(int bolaX, int bolaY) {
        return (bolaY + 6 >= y - 2) && (bolaY + 6 <= y + 9) && (bolaX - 6 >= x + width - 9)
                && (bolaX - 6 <= x + width + 2) && (destruido == false);
    }

    public boolean golpeEsquinaArI(int bolaX, int bolaY) {
        return (bolaY + 6 >= y - 2) && (bolaY + 6 <= y + 9) && (bolaX + 6 >= x - 9) 
                && (bolaX + 6 <= x + 2) && (destruido == false);
    }
}


