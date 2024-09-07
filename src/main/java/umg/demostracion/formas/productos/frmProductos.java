package umg.demostracion.formas.productos;

import umg.demostracion.DataBase.Model.Producto;
import umg.demostracion.DataBase.Service.ProductoServicio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmProductos {

    private JPanel jPanelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblid;
    private JTextField textFieldidProducto;
    private JLabel lblNombreProducto;
    private JTextField textFieldNombreProducto;
    private JLabel lblOrigen;

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmProductos");
        frame.setContentPane(new frmProductos().jPanelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JComboBox comboBoxOrigen;
    private JButton buttonGrabar;
    private JButton buttonBuscar;

    public frmProductos() {
        comboBoxOrigen.addItem("China");
        comboBoxOrigen.addItem("Japon");
        comboBoxOrigen.addItem("Corea");
        buttonGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Producto producto = new Producto();
                producto.setDescripcion(textFieldNombreProducto.getText());
                producto.setOrigen(comboBoxOrigen.getSelectedItem().toString());

                try {
                    new ProductoServicio().crearProducto(producto);
                    JOptionPane.showMessageDialog(null,"Producto creado correctamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear el producto");
                }
            }
        });
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProducto = textFieldidProducto.getText().isEmpty() ? 0 : Integer.parseInt(textFieldidProducto.getText());
                try{
                    Producto productoEncontrado = new ProductoServicio().obtenerProducto(idProducto);
                    if (productoEncontrado == null) {
                        textFieldidProducto.setText(productoEncontrado.getDescripcion());
                        comboBoxOrigen.setSelectedItem(productoEncontrado.getOrigen());
                    } else{
                        JOptionPane.showMessageDialog(null,"El producto NO existe");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error de base de datos:"+ex.getMessage());
                }
            }
        });
    }
}
