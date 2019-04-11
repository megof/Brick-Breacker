package Basededatos;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class Conectar {

    private static final String Servidor = "localhost";
    private static final int Puerto = 3306;
    private static final String Basededatos = "proyecto";
    private static final String Url = "jdbc:mysql://";
    private static final String User = "root";
    private static final String Pass = "";
    Connection Conexion = null;

    public Connection GetConnection() {
        Connection Conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = (Connection) DriverManager.getConnection((Url + Servidor + ":" + Puerto + "/" + Basededatos), User, Pass);
        } catch (Exception e) {
        }
        if (Conexion != null) {
            return Conexion;//conecta
        } else {
            return null;//no conecta
        }
    }
}
