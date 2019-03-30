package Final;
//SELECT * FROM proyecto_jugadores ORDER BY Jugadores_Id DESC LIMIT 5
import Basededatos.Consultar;
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

public class Puntuaciones extends JFrame implements ActionListener {

    private final Container contenedor;
    private final JButton Bvolver;
    private final JLabel Lmensaje;
    private final JScrollPane Sbarra;
    private final JTable tabla;
    ResultSet Rs, auxiliar;
    private final String[] encabezados;
    private final String[][] contenido;

    public Puntuaciones() {
        super("Selección de usuarios");

        Icon back = new ImageIcon("img/back.PNG");
        Bvolver = new JButton("Volver", back);
        Bvolver.addActionListener(this);

        Lmensaje = new JLabel("Mejores puntuaciones");
        
        contenido = new String[11][4];
        encabezados = new String[3];
        cargarlista();
        tabla = new JTable(contenido, encabezados);
        tabla.enable(false); 
        Sbarra = new JScrollPane(tabla);
        Sbarra.setPreferredSize(new Dimension(298,199)); 
        JLabel l = new JLabel("prueba");
        JPanel panel = new JPanel(new GridLayout(1,1));
        panel.add(Sbarra);
        
        contenedor = getContentPane();
        contenedor.setLayout(new FlowLayout());
        contenedor.add(Lmensaje);
        contenedor.add(Sbarra);
        contenedor.add(Bvolver);

        setSize(400, 300);
        setVisible(true);
        setResizable(false);
    }
    public void cargarlista(){
        encabezados[0]="Nombre";
        encabezados[1]="Puntuación";
        encabezados[2]="Fecha";
        int i=0;
        Consultar consultar = new Consultar("", "proyecto_partidas",""); 
        Rs=consultar.GetConsult();
        if(Rs==null){
            JOptionPane.showMessageDialog(null, "error inesperado, por favor intenta de nuevo", "Error al cargar", 0);
        }else{
            try {
                while(Rs.next()){
                        contenido[i][0]=obtenernombres(Integer.parseInt(Rs.getString("Partidas_Jugador1")));
                        contenido[i][1]=Rs.getString("Partidas_Puntaje1");
                        contenido[i][2]=Rs.getString("Partidas_Fecha");
                        i++;
                }
            } catch (SQLException ex) {}
        }
    }
    public String obtenernombres(int id){
        Consultar consultar = new Consultar(""+id, "proyecto_jugadores","Jugadores_Id"); 
        auxiliar=consultar.GetConsult();
        try {
            while(auxiliar.next()){
                return auxiliar.getString("Jugadores_Nick");
            }
        } catch (SQLException ex) {}
        return "";
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
            String[] args=null;
            menu m = new menu();
            m.main(args);
            this.dispose();
    }

    public static void main(String[] args) {
        Puntuaciones aplicacion = new Puntuaciones();
        aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}