/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ProductoDAO {

    ConexionBD conexion = new ConexionBD(); // instancia de conexionBD
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() {
        String sql = "select * from productos";
        List<Producto> lista = new ArrayList<>();
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setCodigo(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setInventario(rs.getInt(4));
                lista.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("Error listar:" + e);
        }
        return lista;
    } // fin del metodo listar

    // Metodo agregar
    public void agregar(Producto producto) {
        String sql = "insert into productos(nombre, precio, inventario) values(?, ?, ?)";
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getInventario());
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El producto ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en agregar: " + e);
        }
    } // fin del metodo agregar

    // Metodo actualizar
    public void actualizar(Producto producto) {
        String sql = "update productos set nombre=?, precio=?, inventario=? where codigo=?";
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getInventario());
            ps.setInt(4, producto.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error ActualizarDAO: " + e);
        }
    } // fin del metodo actualizar

    // Metodo borar
    public void borar(int id) {
        String sql = "delete from productos where codigo=" + id;
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error borrarDao: " + e);
        }
    } // fin del metodo borrar

} // fin de la clase ProductoDAO
