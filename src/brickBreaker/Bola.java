package brickBreaker;

import javax.swing.*;
import java.awt.*;

public class Bola extends Juego implements Constantes {

    private int dirX = 1, dirY = -1;
    private final ImageIcon img;

    public Bola(int x, int y, int width, int height) {
        super(x, y, width, height);
        img = new ImageIcon(getClass().getResource("/Imagenes/bola.png"));
    }

    public void paint(Graphics g) {
        g.drawImage(img.getImage(), x, y, width, height, null);
    }

    public void movimiento() {
        x += dirX;
        y += dirY;
    }

    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }
}
