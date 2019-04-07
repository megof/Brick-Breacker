package Final;

import Basededatos.Consultar;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Jugadores extends JFrame implements ActionListener {
    public static String Jugador="";
    private final Container contenedor;
    private final JButton Bcargar;
    private final JButton Bcancelar;
    private final JLabel Lmensaje;
    private final JPanel Pcampos, Pbotones;
    private final JScrollPane Sbarra;
    private final JList Llista;
    DefaultListModel modelo;
    ResultSet Rs;

    public Jugadores() {
        super("Selección de usuarios");
        Icon save = new ImageIcon("src/Imagenes/load.PNG");
        Bcargar = new JButton("Cargar", save);
        Bcargar.addActionListener(this);

        Icon delete = new ImageIcon("src/Imagenes/delete.PNG");
        Bcancelar = new JButton("Cancelar", delete);
        Bcancelar.addActionListener(this);

        Lmensaje = new JLabel("Seleccionar jugadores");
        modelo = new DefaultListModel();
        Llista = new JList();
        cargarlista();
        Llista.setModel(modelo);
        Sbarra = new JScrollPane(Llista);
        Sbarra.setPreferredSize(new Dimension(200,120));
        
        Pbotones = new JPanel(new GridLayout(1, 2));
        Pbotones.add(Bcargar);
        Pbotones.add(Bcancelar);
        Pcampos = new JPanel(new GridLayout(1,1));
        Pcampos.add(Lmensaje);
        contenedor = getContentPane();
        contenedor.setLayout(new FlowLayout());
        contenedor.add(Lmensaje);
        contenedor.add(Sbarra);
        contenedor.add(Pbotones);

        setSize(300, 225);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void cargarlista(){
        Consultar consultar = new Consultar("","proyecto_jugadores","Jugadores_Nick"); 
        Rs=consultar.GetConsult();
        if(Rs==null){
            JOptionPane.showMessageDialog(null, "error inesperado, por favor intenta de nuevo", "Error al cargar", 0);
        }else{
            try {
                while(Rs.next()){                        
                    modelo.addElement(Rs.getString("Jugadores_Nick"));
                }
            } catch (SQLException ex) {}
        }
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource()== Bcargar){
            if(Llista.getSelectedValue()!=null){
                Jugador=""+Llista.getSelectedValue();
                dispose();
                menu m = new menu();
                m.main(null);
            }else{
                JOptionPane.showMessageDialog(null, "Por favor selecciona un jugador");
            }
            //System.out.println(Llista.getSelectedValue());
        }else{
            //devolver a la otra ventana
            String[] args=null;
            menu m = new menu();
            m.main(args);
            this.dispose();
        }
    }
}