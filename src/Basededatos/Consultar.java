package Basededatos;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Consultar {
    private String Jugador, consulta, tabla, dato;
    PreparedStatement Ps;
    ResultSet Rs;
    Conectar conectar = new Conectar();
    Connection Conexion =conectar.GetConnection(); 
    public Consultar(String Jugador, String tabla, String dato){
        this.Jugador = Jugador;
        this.tabla = tabla;
        this.dato = dato;
    }
    public ResultSet GetConsult(){
        if(tabla=="proyecto_jugadores"){
            if(Jugador==""){
                consulta="Select * From "+tabla;
            }else{
                consulta="Select * From "+tabla+" where "+dato+" ='"+Jugador+"'";
            }
        }else{
            consulta="Select * from "+tabla+" ORDER BY Partidas_Puntaje DESC LIMIT 5";
        }
        if(Conexion!=null){
            try {
                Ps = Conexion.prepareStatement(consulta);
                Rs = Ps.executeQuery();
            } catch (Exception e) {}
            return Rs;
        }else{
            return null;//no conecta
        }
    }
    public Connection GetConexion(){
        return Conexion;
    }
}
