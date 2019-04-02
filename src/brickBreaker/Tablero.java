package brickBreaker;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.*;

public class Tablero extends JPanel implements Runnable, Constantes {

    private boolean inmunidad = false;
    private final Barra barra;
    private final Bola bola;
    private final Escucha escucha;
    private final Grilla[][] grilla = new Grilla[7][5];
    private final Thread juego;
    private final ImageIcon img;
    static int vidas = 3, puntaje = 0, ladriTotal = 35;
    static AtomicBoolean pausa;

    public Tablero(int width, int height) {
        super.setSize(width, height);
        this.setFocusable(true);
        barra = new Barra(BARRA_POS_INICIALX, BARRA_POS_INICIALY, BARRA_WIDTH, BARRA_HEIGHT);
        bola = new Bola(BOLA_POS_INICIALX, BOLA_POS_INICIALY, BOLA_RADIO, BOLA_RADIO);
        pausa = new AtomicBoolean();
        escucha = new Escucha();
        juego = new Thread(this);
        this.grilla();
        this.addMouseMotionListener(escucha);
        this.addKeyListener(escucha);
        juego.start();
        stop();
        pausa.set(true);
        img = new ImageIcon(getClass().getResource("/Imagenes/background.jpg"));

    }

    public void start() {
        juego.resume();
        pausa.set(false);
    }

    public void stop() {
        juego.suspend();
    }

    public void destroy() {
        juego.resume();
        pausa.set(false);
        juego.stop();
        pausa.set(true);
    }

    @Override
    public void run() {
        while (true) {
            bola.movimiento();
            rebotePared();
            reboteBarra();
            reboteGrilla();
            repaint();
            try {
                juego.sleep(3);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img.getImage(), 0, 0, VENTANA_WIDTH, VENTANA_HEIGHT, null);
        barra.paint(g);
        bola.paint(g);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                grilla[i][j].paint(g);
            }
        }
    }

    public void rebotePared() {
        if (bola.getX() < 0 || bola.getX() > 670) {
            bola.setDirX(bola.getDirX() * -1);
            inmunidad = false;
        }
        if (bola.getY() == 0) {
            bola.setDirY(bola.getDirY() * -1);
            inmunidad = false;
        } else if (bola.getY() > 510) {
            if (puntaje > 0) {
                if (puntaje == 50) {
                    puntaje -= 50;
                } else {
                    puntaje -= 100;
                }
            }
            reStart();
        }
    }

    public void reboteBarra() {
        if (inmunidad == false) {
            if (barra.golpeArriba(bola.getX(), bola.getY())) {
                bola.setDirY(bola.getDirY() * -1);
                inmunidad = true;
            } else if (barra.golpeIzquierda(bola.getX(), bola.getY()) || barra.golpeDerecha(bola.getX(), bola.getY())) {
                bola.setDirY(bola.getDirY() * -1);
                bola.setDirX(bola.getDirX() * -1);
                inmunidad = true;
            }
        }
    }

    public void grilla() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                Random random = new Random();
                int color = random.nextInt(3) + 1;
                grilla[i][j] = new Grilla((i * LADRILLO_WIDTH + 5), ((j * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)), LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, color);
            }
        }
    }

    public void reboteGrilla() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                if (grilla[i][j].golpeArriba(bola.getX() + 10, bola.getY() + 10)) {
                    grilla[i][j].color--;
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                }
                if (grilla[i][j].golpeAbajo(bola.getX() + 10, bola.getY() + 10)) {
                    grilla[i][j].color--;
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                    break;
                }
                if (grilla[i][j].golpeDerecha(bola.getX() + 10, bola.getY() + 10)) {
                    grilla[i][j].color--;
                    bola.setDirX(bola.getDirX() * -1);
                    inmunidad = false;
                }
                if (grilla[i][j].golpeIzquierda(bola.getX() + 10, bola.getY() + 10)) {
                    grilla[i][j].color--;
                    bola.setDirX(bola.getDirX() * -1);
                    inmunidad = false;
                    break;
                }
                if (grilla[i][j].golpeEsquinaAD(bola.getX() + 10, bola.getY() + 10)) {
                    grilla[i][j].color--;
                    bola.setDirX(bola.getDirX() * -1);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                }
                if (grilla[i][j].golpeEsquinaAI(bola.getX() + 10, bola.getY() + 10)) {
                    grilla[i][j].color--;
                    bola.setDirX(bola.getDirX() * -1);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                }
                if (grilla[i][j].golpeEsquinaArD(bola.getX() + 10, bola.getY() + 10)) {
                    grilla[i][j].color--;
                    bola.setDirX(bola.getDirX() * -1);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                }
                if (grilla[i][j].golpeEsquinaArI(bola.getX() + 10, bola.getY() + 10)) {
                    grilla[i][j].color--;
                    bola.setDirX(bola.getDirX() * -1);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                }
            }
        }
    }

    private void reStart() {
        if (vidas != 0) {
            vidas--;
            bola.setX(BOLA_POS_INICIALX);
            bola.setY(BOLA_POS_INICIALY);
            barra.setX(BARRA_POS_INICIALX);
            repaint();
            stop();
        } else {
            JOptionPane.showMessageDialog(null, "su Puntaje fue de: " + puntaje);
            destroy();
        }

    }

    private class Escucha extends KeyAdapter implements MouseMotionListener {

        @Override
        public void keyPressed(KeyEvent ke) {
            int key = ke.getKeyCode();
            if (key == KeyEvent.VK_SPACE) {
                start();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (pausa.get() == false) {
                barra.setX(e.getX() - 50);
            }
        }
    }
}
