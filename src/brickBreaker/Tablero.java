package brickBreaker;

import Basededatos.Consultar;
import Basededatos.Crear;
import Final.CargarNivel;
import Final.Jugadores;
import Final.Menu;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
>>>>>>> 64e84a50d6a4e43a6f1aa1279b9f215acfef22ca
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public final class Tablero extends JPanel implements Runnable, Constantes {

    private boolean inmunidad = false, gano, disparo;
    private Barra barra;
    private Bola bola;
    private Escucha escucha;
    static Grilla[][] grilla = new Grilla[5][7];
    static int[][] colores = new int[5][7];
    private Thread juego;
    private ImageIcon img;
    private Font font;
    static int vidas, puntaje, nivel, municion;
    static AtomicBoolean pausa;
<<<<<<< HEAD
    private ArrayList<Poderes> items;
=======
    ResultSet Rs;
>>>>>>> 64e84a50d6a4e43a6f1aa1279b9f215acfef22ca

    public Tablero(int width, int height) {
        //se define el tamaño del panel.
        super.setSize(width, height);

        //permite los escuchas en el panel
        setFocusable(true);

        comenzar();
    }

    public void comenzar() {
        //se crean los objetos.
        barra = new Barra(BARRA_POS_INICIALX, BARRA_POS_INICIALY, BARRA_WIDTH, BARRA_HEIGHT);
        bola = new Bola(BOLA_POS_INICIALX, BOLA_POS_INICIALY, BOLA_RADIO, BOLA_RADIO);
        pausa = new AtomicBoolean();
        escucha = new Escucha();
        juego = new Thread(this);
        items = new ArrayList<>();
        
        disparo = false;
        gano = false;
        vidas = 3;
        puntaje = 0;
        nivel = 3;
        municion = 0;

        //se añaden los escuchas.
        addMouseMotionListener(escucha);
        addKeyListener(escucha);

        //se crea la grilla(ladrillos) inicial
        grilla();

        ReproducirSonido(0);
        //se empieza el hilo y se pausa.
        juego.start();
        stop();
        pausa.set(true);

        //imagenfondo del panel.
        img = new ImageIcon(getClass().getResource("/Imagenes/background.jpg"));
    }

    //metodo para reproducir los sonidos cuando la pelota toca la grilla, barra o pared
    public void ReproducirSonido(int audio) {
        try {
            AudioInputStream audio1 = AudioSystem.getAudioInputStream(new File("src/Sound/golpeBarra.wav").getAbsoluteFile());
            AudioInputStream audio2 = AudioSystem.getAudioInputStream(new File("src/Sound/golpeGrilla.wav").getAbsoluteFile());
            AudioInputStream audio3 = AudioSystem.getAudioInputStream(new File("src/Sound/golpePared.wav").getAbsoluteFile());
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
                case 3:
                    clip.open(audio3);
                    clip.start();
                    break;
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    //metodo para crear la grilla(ladrillos).
    public final void grilla() {
        if (CargarNivel.archivo == null && CargarNivel.imgInt == null) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 7; j++) {
                    Random random = new Random();
                    int color = random.nextInt(3) + 1;
                    int poder = random.nextInt(5) + 1;
                    grilla[i][j] = new Grilla((j * LADRILLO_WIDTH + 5), ((i * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, color, poder);
                }
            }
        } else {
            if (CargarNivel.archivo == null) {
                CargarGrilla(CargarNivel.imgInt);
            } else {
                CargarGrilla(null);
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
            siguienteLvl();
            bola.movimiento();
            rebotePared();
            reboteBarra();
            reboteGrilla();
            dropItems();
            checkItemList();
            repaint();
            try {
                juego.sleep(nivel);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void CargarGrilla(int[] Agrilla) {
        if (Agrilla == null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(CargarNivel.archivo));
                String linea = reader.readLine();
                while (linea != null) {
                    for (int i = 0; i < 5; i++) {
                        String[] values = linea.split(",");
                        for (int j = 0; j < values.length; j++) {
                            if (Integer.parseInt(values[j]) == 0) {
                                colores[i][j] = -1;
                            } else {
                                colores[i][j] = Integer.parseInt(values[j]);
                            }
                        }
                        linea = reader.readLine();
                    }
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 7; j++) {
                    if (Agrilla[j + 7 * i] == 0) {
                        colores[i][j] = -1;
                    } else {
                        colores[i][j] = Agrilla[j + 7 * i];
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                Random random = new Random();
                int poder = random.nextInt(5) + 1;
                grilla[i][j] = new Grilla((j * LADRILLO_WIDTH + 5), ((i * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                        LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, colores[i][j], poder);
            }
        }
    }

    public void addItem(Poderes i) {
        items.add(i);
    }

    public void dropItems() {
        for (int i = 0; i < items.size(); i++) {
            Poderes tempItem = items.get(i);
            tempItem.drop();
            items.set(i, tempItem);
        }
    }

    public void checkItemList() {
        for (int i = 0; i < items.size(); i++) {
            Poderes tempItem = items.get(i);
            if (barra.caughtItem(tempItem)) {
                items.remove(i);
            } else if (tempItem.getY() > VENTANA_HEIGHT) {
                items.remove(i);
            }
        }
    }

    //metodo para comprobar cuando se han roto todos los ladrillos y si es asi, cambia de nivel.
    public boolean ganar() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                if (grilla[i][j].getColor() > 0 && grilla[i][j].getColor() < 4) {
                    return false;
                }
            }
        }
        return true;
    }

    public void siguienteLvl() {
        if (ganar()) {
            if (CargarNivel.archivo == null && CargarNivel.imgInt == null) {
                if (nivel == 3) {
                    pausa.set(true);
                    grilla();
                    grilla[1][1] = new Grilla((1 * LADRILLO_WIDTH + 5),
                            ((1 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    grilla[1][3] = new Grilla((3 * LADRILLO_WIDTH + 5),
                            ((1 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    grilla[1][5] = new Grilla((5 * LADRILLO_WIDTH + 5),
                            ((1 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    nivel--;
                    vidas++;
                    reStart();
                } else if (nivel == 2) {
                    pausa.set(true);
                    grilla();
                    grilla[1][1] = new Grilla((1 * LADRILLO_WIDTH + 5),
                            ((1 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    grilla[1][2] = new Grilla((2 * LADRILLO_WIDTH + 5),
                            ((1 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    grilla[2][1] = new Grilla((1 * LADRILLO_WIDTH + 5),
                            ((2 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    grilla[2][2] = new Grilla((2 * LADRILLO_WIDTH + 5),
                            ((2 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    grilla[3][1] = new Grilla((1 * LADRILLO_WIDTH + 5),
                            ((3 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    grilla[3][2] = new Grilla((2 * LADRILLO_WIDTH + 5),
                            ((3 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);

                    grilla[1][4] = new Grilla((4 * LADRILLO_WIDTH + 5),
                            ((1 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    grilla[1][5] = new Grilla((5 * LADRILLO_WIDTH + 5),
                            ((1 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    grilla[2][4] = new Grilla((4 * LADRILLO_WIDTH + 5),
                            ((2 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    grilla[2][5] = new Grilla((5 * LADRILLO_WIDTH + 5),
                            ((2 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    grilla[3][4] = new Grilla((4 * LADRILLO_WIDTH + 5),
                            ((3 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    grilla[3][5] = new Grilla((5 * LADRILLO_WIDTH + 5),
                            ((3 * LADRILLO_HEIGHT) + (LADRILLO_HEIGHT / 5)),
                            LADRILLO_WIDTH - 5, LADRILLO_HEIGHT - 5, 4, 3);
                    nivel--;
                    vidas++;
                    reStart();
                } else if (nivel == 1) {
                    gano = true;
                    int opcion = JOptionPane.showConfirmDialog(null, "Quiere volver a jugar?", "BrickBreacker", 0);
                    if (opcion == 0) {
                        ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
                        Ventana.main(null);
                        destroy();
                    } else {
                        ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
                        Menu.main(null);
                        destroy();
                    }
                    destroy();
                }
            } else {
                gano = true;
                int opcion = JOptionPane.showConfirmDialog(null, "Quiere volver a jugar tu nivel personalizado?", "BrickBreacker", 0);
                if (opcion == 0) {
                    ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
                    Ventana.main(null);
                    destroy();
                } else {
                    ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
                    CargarNivel.archivo = null;
                    CargarNivel.imgInt = null;
                    Menu.main(null);
                    destroy();
                }
                destroy();
            }
        }
    }

   
    //metodo que pinta todos los componentes del juego.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img.getImage(), 0, 0, VENTANA_WIDTH, VENTANA_HEIGHT, null);
        g.setColor(Color.WHITE);
        g.setFont(fuente(25));
        barra.paint(g);
        bola.paint(g);
        for (Poderes i : items) {
            i.draw(g);
        }
        if (pausa.get() == true && gano == false) {
            g.drawString("Presione espacio para Empezar", 65, 240);
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                grilla[i][j].paint(g);
            }
        }
        if (gano == true) {
            g.drawString("Tu puntaje fue de : " + puntaje, 130, 240);
            g.setFont(fuente(35));
            g.drawString("FELICITACIONES GANASTE!!", 40, 200);
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
<<<<<<< HEAD
            int opcion = JOptionPane.showConfirmDialog(null, "Quiere volver a jugar?", "BrickBreacker", 0);
            if (opcion == 0) {
                ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
                Ventana.main(null);
                destroy();
            } else {
                ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
                Menu.main(null);
                destroy();
            }

=======
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
            registrarPuntajes();
            Menu.main(null);
            destroy();
>>>>>>> 64e84a50d6a4e43a6f1aa1279b9f215acfef22ca
        }
    }

    public void registrarPuntajes() {
        Consultar consulta = new Consultar(Jugadores.Jugador, "proyecto_jugadores", "Jugadores_Nick");
        Rs = consulta.GetConsult();
        String codjugador="";
        try {
            while (Rs.next()) {
                codjugador = Rs.getString("Jugadores_Id");
            }
        } catch (SQLException ex) {
        }
        LocalDateTime fecha = LocalDateTime.now();
        String fechastring = fecha.getYear() + "-" + agregarcero(fecha.getMonthValue()) + "-" + agregarcero(fecha.getDayOfMonth()) + " " + agregarcero(fecha.getHour()) + ":" + agregarcero(fecha.getMinute()) + ":" + agregarcero(fecha.getSecond());
        Crear crear = new Crear(codjugador);
        crear.Insertar(consulta, "Puntaje", puntaje, fechastring);
    }

    public String agregarcero(int dato) {
        if (dato < 10) {
            return "0" + dato;
        } else {
            return "" + dato;
        }
    }

    //metodo para comparar cuando rebota en una pared o cuando pierde.
    public void rebotePared() {
        if (bola.getX() < 0 || bola.getX() > 670) {
            ReproducirSonido(3);
            bola.setDirX(bola.getDirX() * -1);
            inmunidad = false;
        }
        if (bola.getY() == 0) {
            ReproducirSonido(3);
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
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                if (grilla[i][j].golpeArriba(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                    if (grilla[i][j].isDestruido()) {
                        addItem(grilla[i][j].poder);
                        puntaje += 100;
                    } else {
                        puntaje += 50;
                    }
                    break;
                }
                if (grilla[i][j].golpeAbajo(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                    if (grilla[i][j].isDestruido()) {
                        addItem(grilla[i][j].poder);
                        puntaje += 100;
                    } else {
                        puntaje += 50;
                    }
                    break;
                }
                if (grilla[i][j].golpeDerecha(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirX(bola.getDirX() * -1);
                    inmunidad = false;
                    if (grilla[i][j].isDestruido()) {
                        addItem(grilla[i][j].poder);
                        puntaje += 100;
                    } else {
                        puntaje += 50;
                    }
                    break;
                }
                if (grilla[i][j].golpeIzquierda(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirX(bola.getDirX() * -1);
                    inmunidad = false;
                    if (grilla[i][j].isDestruido()) {
                        addItem(grilla[i][j].poder);
                        puntaje += 100;
                    } else {
                        puntaje += 50;
                    }
                    break;
                }
                if (grilla[i][j].golpeEsquinaAD(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirX(bola.getDirX() * -1);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                    if (grilla[i][j].isDestruido()) {
                        addItem(grilla[i][j].poder);
                        puntaje += 100;
                    } else {
                        puntaje += 50;
                    }
                    break;
                }
                if (grilla[i][j].golpeEsquinaAI(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirX(bola.getDirX() * -1);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                    if (grilla[i][j].isDestruido()) {
                        addItem(grilla[i][j].poder);
                        puntaje += 100;
                    } else {
                        puntaje += 50;
                    }
                    break;
                }
                if (grilla[i][j].golpeEsquinaArD(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirX(bola.getDirX() * -1);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                    if (grilla[i][j].isDestruido()) {
                        addItem(grilla[i][j].poder);
                        puntaje += 100;
                    } else {
                        puntaje += 50;
                    }
                    break;
                }
                if (grilla[i][j].golpeEsquinaArI(bola.getX() + 10, bola.getY() + 10)) {
                    ReproducirSonido(2);
                    bola.setDirX(bola.getDirX() * -1);
                    bola.setDirY(bola.getDirY() * -1);
                    inmunidad = false;
                    if (grilla[i][j].isDestruido()) {
                        addItem(grilla[i][j].poder);
                        puntaje += 100;
                    } else {
                        puntaje += 50;
                    }
                    break;
                }
            }
        }
    }

    //metodo para cambiar la fuente a una personalizada y si no la encuentra la fuente arial.
    public Font fuente(int tam) {
        try {
            InputStream is = getClass().getResourceAsStream("/imagenes/batmfa__.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException ex) {
            System.err.println("batmfa__.ttf" + " No se cargo la fuente");
            font = new Font("Arial", Font.PLAIN, 14);
        }
        Font tfont = font.deriveFont(Font.BOLD, tam);
        return tfont;
    }

    //clase Escucha que hereda de KeyApapter el metodo keyPressed y implementa la interfaz MouseMotionListener
    // y sus metodos.
    private class Escucha extends KeyAdapter implements MouseMotionListener {

        //metodo que comprar cuando se presiona espacio para inciar el juego
        @Override
        public void keyPressed(KeyEvent ke) {
            int key = ke.getKeyCode();
            if (key == KeyEvent.VK_SPACE && pausa.equals(true)) {
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
                if (e.getX() + (barra.width / 2) < VENTANA_WIDTH && e.getX() - (barra.width / 2) > 0) {
                    barra.setX(e.getX() - (barra.width / 2));
                }
            } else if (pausa.get() == true) {
                if (e.getX() + (barra.width / 2) < VENTANA_WIDTH && e.getX() - (barra.width / 2) > 0) {
                    barra.setX(e.getX() - (barra.width / 2));
                    bola.setX(e.getX() - (bola.width / 2));
                    repaint();
                }
            }
        }
    }
}
