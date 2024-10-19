/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author chave
 */
public class ConexionBD {
    
    private static Connection conn;
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Asegúrate de usar el driver correcto
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/tienda";
    
   
    
    public Connection conectarBaseDatos(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexion: "+ e);
        }
        return conn;
    }
    
    public Connection getConnection() {
        return conn;
    }
        
    public void desconectar() {
        if (conn != null) { 
            try {
                conn.close(); 
                System.out.println("Conexión terminada...");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        } else {
            System.out.println("No hay conexión activa...");
        }
    }
}