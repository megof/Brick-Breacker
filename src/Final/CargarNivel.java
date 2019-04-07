package Final;

import Basededatos.Crear;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CargarNivel extends JFrame implements ActionListener {

    private final Container contenedor;
    private final JButton Bcrear, Bcancelar, Bcargar;
    private final JPanel Pcampos, Pgrilla, Pimg;
    private final JButton grilla[];
    private final int imgInt[];
    private final String img[];

    public CargarNivel() {
        super("Crear/Cargar niveles");
        
        grilla = new JButton[35];
        imgInt = new int[35];
        img = new String[4];
        img[0]="";
        img[1]="Green";
        img[2]="Yellow";
        img[3]="Red";
        for (int i = 0; i < 35; i++) {
            grilla[i]= new JButton();    
            grilla[i].addActionListener(this);
            imgInt[i]=0;
        }
        grilla[0].setPreferredSize(new Dimension(60,50));
        
        Icon save = new ImageIcon("src/Imagenes/save.PNG");
        Bcrear = new JButton("Crear", save);
        Bcrear.addActionListener(this);

        Icon delete = new ImageIcon("src/Imagenes/delete.PNG");
        Bcancelar = new JButton("Cancelar", delete);
        Bcancelar.addActionListener(this);

        Icon load = new ImageIcon("src/Imagenes/load.PNG");
        Bcargar = new JButton("Cargar", load);
        Bcargar.addActionListener(this);
        
        Pimg = new JPanel(new GridLayout(1, 6));
        Pimg.add(new JLabel(new ImageIcon("src/Imagenes/Green.PNG")));
        Pimg.add(new JLabel("= 1 Golpe"));
        Pimg.add(new JLabel(new ImageIcon("src/Imagenes/Yellow.PNG")));
        Pimg.add(new JLabel("= 2 Golpes"));
        Pimg.add(new JLabel(new ImageIcon("src/Imagenes/Red.PNG")));
        Pimg.add(new JLabel("= 3 Golpes"));
        
        Pcampos = new JPanel(new GridLayout(1, 3));
        Pcampos.add(Bcrear);
        Pcampos.add(Bcargar);
        Pcampos.add(Bcancelar);
        
        Pgrilla = new JPanel(new GridLayout(5, 7));
        for (int i = 0; i < 35; i++) {
            Pgrilla.add(grilla[i]);          
        }
        
        contenedor = getContentPane();
        contenedor.setLayout(new FlowLayout());
        contenedor.add(Pimg);
        contenedor.add(Pgrilla);
        contenedor.add(Pcampos);

        setSize(700, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent evento){
        for (int i = 0; i < 35; i++) {
            if(grilla[i]==evento.getSource()){                
                if(imgInt[i]+1>3){
                    imgInt[i]=0;
                    grilla[i].setIcon(null);
                }else{
                    imgInt[i]++;
                    grilla[i].setIcon(new ImageIcon("src/Imagenes/"+img[imgInt[i]]+".PNG"));
                }
            }          
        }
        if (evento.getSource()== Bcrear){
            for (int i = 0; i < grilla.length; i++) {
                System.out.print(imgInt[i]+" ");
                if((i+1) %7 ==0){
                    System.out.println("");
                }
            }
        }else if(evento.getSource()== Bcancelar){
            //devolver a la otra ventana
            this.dispose();
            menu m = new menu();
            m.main(null);
        }
    }
}
