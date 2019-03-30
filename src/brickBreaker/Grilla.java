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
        
        info = new Informacion(VENTANA_INFO_WIDTH, VENTANA_INFO_HEIGHT);
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
            info.repaint();
        }
    }
    
    public boolean golpeArriba(int bolaX, int bolaY) {
        if (((bolaX + 9 >= x) && (bolaX - 9 <= x + width)) && (bolaY + 9 == y) && (destruido == false)) {
            color--;
            return true;
        }
        return false;
    }

    public boolean golpeAbajo(int bolaX, int bolaY) {
        if (((bolaX >= x) && (bolaX <= x + width)) && (bolaY - 9 == y + height) && (destruido == false)) {
            color--;
            return true;
        }
        return false;
    }

    public boolean golpeDerecha(int bolaX, int bolaY) {
        if ((bolaX - 9 == x + width-2) && ((bolaY + 9 >= y-1) && (bolaY - 9 <= y + height+1)) && (destruido == false)) {
            color--;
            return true;
        }
        return false;
    }

    public boolean golpeIzquierda(int bolaX, int bolaY) {
        if ((bolaX + 9 == x) && ((bolaY + 9 >= y-1) && (bolaY - 9 <= y + height+1)) && (destruido == false)) {
            color--;
            return true;
        }
        return false;
    }
}


