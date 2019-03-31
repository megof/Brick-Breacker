package Final;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class VentanaLogo extends javax.swing.JFrame implements ActionListener{
    private Container contenedor;
    private JLabel Imagen;
    public VentanaLogo(){
        Icon imagen = new ImageIcon("src/Imagenes/Intro.GIF" );
        Imagen = new JLabel(imagen);
        contenedor = getContentPane();
        contenedor.setLayout(new FlowLayout());
        contenedor.add(Imagen);                                  
        AudioClip sonido;
        sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Sound/Intro.wav"));
        sonido.play(); 
        delay(300);
        setSize(700,500);
        setVisible(true);
        setResizable(false);  
        delay(5500);
            String[] args=null;
            menu m = new menu();
            m.main(args);
            this.dispose();
    }
    public void delay(int temp){    
        try{
            Thread.sleep(temp);            
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static void main(String[] args) {
        VentanaLogo aplicacion = new VentanaLogo();
        aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    }
} 

