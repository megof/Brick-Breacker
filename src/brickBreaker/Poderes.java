package brickBreaker;

import java.awt.*;
import javax.swing.ImageIcon;

public class Poderes extends Juego implements Constantes {

    private int type;
    private final ImageIcon poderes[];

    public Poderes(int x, int y, int width, int height, int type) {
        super(x, y, width, height);
        setType(type);
        poderes = new ImageIcon[3];
        cargarImagenes();
    }

    public void draw(Graphics g) {
        switch (getType()) {
            case 1:
                g.drawImage(poderes[2].getImage(), x, y, width+25, height, null);
                break;
            case 2:
                g.drawImage(poderes[1].getImage(), x, y, width+25, height, null);
                break;
            case 3:
                g.drawImage(poderes[0].getImage(), x, y, width, height, null);
                break;
            case 4:
                return;
        }
    }

    public void drop() {
        y += 1;
    }

    public void cargarImagenes() {
        poderes[0] = new ImageIcon(getClass().getResource("/Imagenes/Vidas.png"));
        for (int i = 1; i < poderes.length; i++) {
            poderes[i] = new ImageIcon(getClass().getResource("/Imagenes/poder" + i + ".png"));
        }
    }

    public void poder(Barra b) {
        if (getType() == 1 && b.getWidth() < 200) {
            b.setWidth(b.getWidth() + 15);
        } else if (getType() == 2 && b.getWidth() > 50) {
            b.setWidth(b.getWidth() - 15);
        } else if (getType() == 3) {
            Tablero.vidas++;
        } 
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
