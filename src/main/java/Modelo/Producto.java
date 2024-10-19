/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author yeiim
 */
public class Producto {
    private int codigo;
    private String nombre;
    private double precio;
    private int inventario;

//    constructor vacio
    public Producto() {
        
    }

//    constructor agregar
    public Producto(String nombre, double precio, int inventario) {
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
    }
    
//    contructor actualizar
    public Producto(int codigo, String nombre, double precio, int inventario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
    }
    
//    getter y setter
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }
      
} // fIN CLASE PRODUCTO
