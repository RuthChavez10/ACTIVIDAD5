/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.Producto;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Modelo.ProductoDAO;
import Vista.Interfaz;

/**
 *
 * @author yeiim
 */
public class ControladorProducto implements ActionListener {

    // Instancias
    Producto producto = new Producto();
    ProductoDAO productodao = new ProductoDAO();
    Interfaz vista = new Interfaz();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    //variables globales
    private int codigo = 0;
    private String nombre;
    private double precio;
    private int inventario;

    public ControladorProducto(Interfaz vista) {
        this.vista = vista;
        vista.setVisible(true);
        agregarEventos();
        listarTabla();
    }

    private void agregarEventos() {
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnActualizar().addActionListener(this);
        vista.getBtnBorrar().addActionListener(this);
        vista.getBtnLimpiar().addActionListener(this);
        vista.getTblTabla().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                llenarCampos(e);
            }
        });
    }

    private void listarTabla() {
        String[] titulos = new String[]{"Codigo", "Nombre", "Precio", "Inventario"};
        modeloTabla = new DefaultTableModel(titulos, 0);
        List<Producto> listaProductos = productodao.listar();
        for (Producto producto : listaProductos) {
            modeloTabla.addRow(new Object[]{producto.getCodigo(), producto.getNombre(), producto.getPrecio(), producto.getInventario()});
        }
        vista.getTblTabla().setModel(modeloTabla);
        vista.getTblTabla().setPreferredSize(new Dimension(350, modeloTabla.getRowCount() * 16));
    }

    private void llenarCampos(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        codigo = (int) vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 0);
        vista.getTxtNombre().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 1).toString());
        vista.getTxtPrecio().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 2).toString());
        vista.getTxtInventario().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 3).toString());
    }

    //---------------------------------------- validar formulario
    private boolean validarDatos() {
        if ("".equals(vista.getTxtNombre().getText()) || "".equals(vista.getTxtPrecio().getText()) || "".equals(vista.getTxtInventario().getText())) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden ser vacios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Metodo 3 en 1
    // 1. cargando las variables globales
    // 2. parseando valores
    // 3. estamos validando que precio y inventario sean numericos
    private boolean cargarDatos() {
        try {
            nombre = vista.getTxtNombre().getText();
            precio = Double.parseDouble(vista.getTxtPrecio().getText());
            inventario = Integer.parseInt(vista.getTxtInventario().getText());
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Los campos precio e inventario deben ser numericos", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error ala cargar Datos: " + e);
            return false;
        }
    }

    private void limpiarCampos() {
        vista.getTxtNombre().setText("");
        vista.getTxtPrecio().setText("");
        vista.getTxtInventario().setText("");
        codigo = 0;
        nombre = "";
        precio = 0;
        inventario = 0;
    }
    //-----------------------------------------------------------

    private void agregarProducto() {
        try {
            if (validarDatos()) { // validarDatos() == true
                if (cargarDatos()) { // cargarDatos() == true
                    Producto producto = new Producto(nombre, precio, inventario);
                    productodao.agregar(producto);
                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                    limpiarCampos();
                }
            }
        } catch (HeadlessException e) {
            System.out.println("Error AgregarC: " + e);
        } finally {
            listarTabla();
        }
    }

    private void actualizaProducto() {
        try {
            if (validarDatos()) {
                if (cargarDatos()) {
                    Producto producto = new Producto(codigo, nombre, precio, inventario);
                    productodao.actualizar(producto);
                    JOptionPane.showMessageDialog(null, "Registro actualizado");
                    limpiarCampos();
                }
            }
        } catch (HeadlessException e) {
            System.out.println("Error actualizarC : " + e);
        } finally {
            listarTabla();
        }
    }

    private void borrarProducto() {
        try {
            if (codigo != 0) {
                productodao.borar(codigo);
                JOptionPane.showMessageDialog(null, "Registro borrado");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un producto de la tabal", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            System.out.println("Error borrarC:" + e);
        } finally {
            listarTabla();
        }
    }

    // Dar acciones a los botones
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getBtnAgregar()) {
            agregarProducto();
        }
        if (ae.getSource() == vista.getBtnActualizar()) {
            actualizaProducto();
        }
        if (ae.getSource() == vista.getBtnBorrar()) {
            borrarProducto();
        }
        if (ae.getSource() == vista.getBtnLimpiar()) {
            limpiarCampos();
        }
    }

}
