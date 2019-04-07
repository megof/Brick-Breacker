package brickBreaker;

import Final.menu;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public final class Tablero extends JPanel implements Runnable, Constantes {

    private boolean inmunidad = false;
    private final Barra barra;
    private final Bola bola;
    private final Escucha escucha;
    private final Grilla[][] grilla = new Grilla[7][5];
    private final Thread juego;
    private final ImageIcon img;
    private Font font;
    static int vidas = 3, puntaje = 0, ladriTotal = 35, nivel = 3;
    static AtomicBoolean pausa;

    public Tablero(int width, int height) {
        //se define el tamaño del panel.
        super.setSize(width, height);

        //permite los escuchas en el panel
        setFocusable(true);

        //se crean los objetos.
        barra = new Barra(BARRA_POS_INICIALX, BARRA_POS_INICIALY, BARRA_WIDTH, BARRA_HEIGHT);
        bola = new Bola(BOLA_POS_INICIALX, BOLA_POS_INICIALY, BOLA_RADIO, BOLA_RADIO);
        pausa = new AtomicBoolean();
        escucha = new Escucha();
        juego = new Thread(this);

        //se añaden los escuchas.
        addMouseMotionListener(escucha);
        addKeyListener(escucha);

        //se crea la grilla(ladrillos) inicial.
        grilla();

        ReproducirSonido(0);
        //se empieza el hilo y se pausa.
        juego.start();
        stop();
        pausa.set(true);

        //imagenfondo del panel.
        img = new ImageIcon(getClass().getResource("/Imagenes/background.jpg"));
    }

    //metodo para reproducir los sonidos
    public void ReproducirSonido(int audio) {
        try {
            AudioInputStream audio1 = AudioSystem.getAudioInputStream(new File("src/Sound/golpeBarra.wav").getAbsoluteFile());
            AudioInputStream audio2 = AudioSystem.getAudioInputStream(new File("src/Sound/golpeGrilla.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            switch (audio) {
                case 1:
                    clip.open(audio1);
                    clip.start();
                    break;
                case 2:
                    clip.open(audio2);
                    clip.start();
                    break;
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    //metodo para crear la grilla(ladrillos).
    public final void grilla() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                Random random = new Random();
                int color = random.nextInt(3) + 1;
                grilla[i][j] = new Grilla((i * LADRILLO_WIDTH + 5),
                        ((j * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                        LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, color);
            }
        }
    }

    //metodo para iniciar el juego.
    public void start() {
        juego.resume();
        pausa.set(false);
    }

    //metodo para pausar el juego.
    public final void stop() {
        juego.suspend();
    }

    //metodo para que el hilo deje de correr.
    public void destroy() {
        juego.resume();
        pausa.set(false);
        juego.stop();
        pausa.set(true);
    }

    //metodo para inciar el hilo de la interfaz runnable.
    @Override
    public void run() {
        while (true) {
            bola.movimiento();
            rebotePared();
            reboteBarra();
            reboteGrilla();
            ganar();
            repaint();
            try {
                juego.sleep(nivel);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    //metodo para comprobar cuando se han roto todos los ladrillos y si es asi, cambia de nivel.
    public void ganar() {
        if (ladriTotal == 0) {
            if (nivel == 3) {
                pausa.set(true);
                ladriTotal = 32;
                grilla();
                grilla[1][1] = new Grilla((1 * LADRILLO_WIDTH + 5),
                        ((1 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                        LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4);
                grilla[3][1] = new Grilla((3 * LADRILLO_WIDTH + 5),
                        ((1 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                        LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4);
                grilla[5][1] = new Grilla((5 * LADRILLO_WIDTH + 5),
                        ((1 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                        LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4);
                nivel--;
                vidas++;
                reStart();
            }
        }
    }

    //metodo que pinta todos los componentes del juego.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img.getImage(), 0, 0, VENTANA_WIDTH, VENTANA_HEIGHT, null);
        barra.paint(g);
        bola.paint(g);
        if (pausa.get() == true) {
            g.setColor(Color.WHITE);
            g.setFont(fuente());
            g.drawString("Presione espacio para Empezar", 65, 240);
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                grilla[i][j].paint(g);
            }
        }
    }

    //este metodo dice si el jugador ya perdio, si no es asi devuelve la bola y la barra a su estado inicial.
    private void reStart() {
        if (vidas != 0) {
            bola.setX(BOLA_POS_INICIALX);
            bola.setY(BOLA_POS_INICIALY);
            barra.setX(BARRA_POS_INICIALX);
            bola.setDirY(-1);
            repaint();
            stop();
        } else {
            JOptionPane.showMessageDialog(null, "su Puntaje fue de: " + puntaje);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
            menu.main(null);
            destroy();
        }
    }

    //metodo para comparar cuando rebota en una pared o cuando pierde.
    public void rebotePared() {
        if (bola.getX() < 0 || bola.getX() > 670) {
            bola.setDirX(bola.getDirX() * -1);
            inmunidad = false;
        }
        if (bola.getY() == 0) {
            bola.setDirY(bola.getDirY() * -1);
            inmunidad = false;
        } else if (bola.getY() > 510) {
            pausa.set(true);
            if (puntaje > 0) {
                if (puntaje == 50) {
                    puntaje -= 50;
                } else {
                    puntaje -= 100;
                }
            }
            vidas--;
            reStart();
        }
    }

    //metodo para comparar cuando rebota contra la barra.
    public void reboteBarra() {
        if (inmunidad == false) {
            if (barra.golpeArriba(bola.getX(), bola.getY())) {
                ReproducirSonido(1);
                bola.setDirY(bola.getDirY() * -1);
                inmunidad = true;
            } else if (barra.golpeIzquierda(bola.getX(), bola.getY()) || barra.golpeDerecha(bola.getX(), bola.getY())) {
                ReproducirSonido(1);
                bola.setDirY(bola.getDirY() * -1);
                bola.setDirX(bola.getDirX() * -1);
                inmunidad = true;
            }
        }
    }

    //metodo para comprobar cuando rebota contra la grilla(ladrillos).
    public void reboteGrilla() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                if (grilla[i][j].golpeArriba(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                    break;
                }
                if (grilla[i][j].golpeAbajo(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                    break;
                }
                if (grilla[i][j].golpeDerecha(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirX(bola.getDirX() * -1);
                    inmunidad = false;
                    break;
                }
                if (grilla[i][j].golpeIzquierda(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirX(bola.getDirX() * -1);
                    inmunidad = false;
                    break;
                }
                if (grilla[i][j].golpeEsquinaAD(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirX(bola.getDirX() * -1);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                    break;
                }
                if (grilla[i][j].golpeEsquinaAI(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirX(bola.getDirX() * -1);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                    break;
                }
                if (grilla[i][j].golpeEsquinaArD(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirX(bola.getDirX() * -1);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                    break;
                }
                if (grilla[i][j].golpeEsquinaArI(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirX(bola.getDirX() * -1);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                    break;
                }
            }
        }
    }

    //metodo para cambiar la fuente a una personalizada y si no la encuentra la fuente arial.
    public Font fuente() {
        try {
            InputStream is = getClass().getResourceAsStream("/imagenes/batmfa__.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException ex) {
            System.err.println("batmfa__.ttf" + " No se cargo la fuente");
            font = new Font("Arial", Font.PLAIN, 14);
        }
        Font tfont = font.deriveFont(Font.BOLD, 25);
        return tfont;
    }

    //clase Escucha que hereda de KeyApapter el metodo keyPressed y implementa la interfaz MouseMotionListener
    // y sus metodos.
    private class Escucha extends KeyAdapter implements MouseMotionListener {

        //metodo que comprar cuando se presiona espacio para inciar el juego
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

        //metodo para mover la barra y cuando esta pausado el juego, mueve la barra y la bola.
        @Override
        public void mouseMoved(MouseEvent e) {
            if (pausa.get() == false) {
                barra.setX(e.getX() - (barra.width / 2));
            } else if (pausa.get() == true) {
                barra.setX(e.getX() - (barra.width / 2));
                bola.setX(e.getX() - (bola.width / 2));
                repaint();
            }
        }
    }
}
