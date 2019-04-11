package Final;

import Basededatos.Crear;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Crud extends JFrame implements ActionListener {

    private final Container contenedor;
    private final JButton Bcrear, Bcancelar;
    private final JLabel Lnombre, Lmensaje;
    private final JTextField TFnombre;
    private final JPanel Pcampos;
    private final Icon save, delete;

    public Crud() {
        super("Registro de usuarios");

        save = new ImageIcon("src/Imagenes/save.PNG");
        Bcrear = new JButton("Crear", save);
        Bcrear.addActionListener(this);

        delete = new ImageIcon(getClass().getResource("/Imagenes/delete.PNG"));
        Bcancelar = new JButton("Cancelar", delete);
        Bcancelar.addActionListener(this);

        Lnombre = new JLabel("Nombre");
        TFnombre = new JTextField("", 10);
        Lmensaje = new JLabel("Crear jugadores");
        Pcampos = new JPanel(new GridLayout(2, 2));
        Pcampos.add(Lnombre);
        Pcampos.add(TFnombre);
        Pcampos.add(Bcrear);
        Pcampos.add(Bcancelar);
        contenedor = getContentPane();
        contenedor.setLayout(new FlowLayout());
        contenedor.add(Lmensaje);
        contenedor.add(Pcampos);

        setSize(300, 130);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == Bcrear) {
            if ((TFnombre.getText()).length() == 0) {
                JOptionPane.showMessageDialog(null, "Por favor ingresa un Nombre en el campo de texto", "Error al crear", 0);
            } else {
                Crear crear = new Crear(TFnombre.getText());
                if (crear.GetConsult() == null) {
                    JOptionPane.showMessageDialog(null, "error inesperado, por favor intenta de nuevo", "Error al crear", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "Exito al crear jugador", "Exito", 1);
                    this.dispose();
                    Menu m = new Menu();
                    m.main(null);
                }
            }
        } else {
            //devolver a la otra ventana
            this.dispose();
            Menu m = new Menu();
            m.main(null);
        }
    }
}
