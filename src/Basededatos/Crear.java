package Basededatos;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Crear {

    private String Jugador;
    PreparedStatement Ps;
    ResultSet Rs;

    public Crear(String Jugador) {
        this.Jugador = Jugador;
    }

    public ResultSet GetConsult() {
        Consultar consulta = new Consultar(Jugador, "proyecto_jugadores", "Jugadores_Nick");
        Rs = consulta.GetConsult();
        try {
            if (Rs.next()) {
                System.out.println(1);
                return null;//encuentra
            } else {
                Insertar(consulta, "Jugador", 0, "");//no encuentra entonces crea
            }
        } catch (Exception e) {
        }
        return Rs;
    }

    public PreparedStatement Insertar(Consultar consulta, String tipo, int puntaje, String fecha) {
        Connection Conexion = consulta.GetConexion();
        if (tipo == "Jugador") {
            try {
                Ps = Conexion.prepareStatement("Insert into proyecto_jugadores(Jugadores_Nick) Values('" + Jugador + "')");
                if (Ps.executeUpdate() > 0) {
                    return Ps;//guardado
                }
            } catch (Exception e) {
            }
        } else {
            try {
                Ps = Conexion.prepareStatement("Insert into proyecto_partidas(Partidas_Jugador, Partidas_Puntaje, Partidas_Fecha) Values('" + Jugador + "','"+ puntaje + "','"+ fecha +"')");
                System.out.println(Ps);
                if (Ps.executeUpdate() > 0) {
                    return Ps;//guardado
                }
            } catch (Exception e) {
            }
        }
        return null;//error
    }
}
