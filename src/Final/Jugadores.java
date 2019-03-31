package Final;

import Basededatos.Consultar;
import java.awt.BorderLayout;
import java.awt.Container;
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
        Icon save = new ImageIcon("img/load.PNG");
        Bcargar = new JButton("Cargar", save);
        Bcargar.addActionListener(this);

        Icon delete = new ImageIcon("img/delete.PNG");
        Bcancelar = new JButton("Cancelar", delete);
        Bcancelar.addActionListener(this);

        Lmensaje = new JLabel("Seleccionar jugadores");
        modelo = new DefaultListModel();
        Llista = new JList();
        cargarlista();
        Llista.setModel(modelo);
        Sbarra = new JScrollPane(Llista);
        
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
        setVisible(true);
        setResizable(false);
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
            System.out.println(Llista.getSelectedValue());
        }else{
            //devolver a la otra ventana
            String[] args=null;
            menu m = new menu();
            m.main(args);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        Jugadores aplicacion = new Jugadores();
        aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}