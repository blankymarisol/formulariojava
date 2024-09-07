package umg.demostracion.DataBase.Service;

import umg.demostracion.DataBase.Dao.ProductoDAO;
import umg.demostracion.DataBase.Dao.ProductoDAOImpl;
import umg.demostracion.DataBase.Model.Producto;

import java.sql.SQLException;
import java.util.List;

public class ProductoServicio {
    private ProductoDAO productoDAO;

    public ProductoServicio() {
        this.productoDAO = new ProductoDAOImpl();
    }

    public void agregarProducto(Producto producto) throws SQLException {
        productoDAO.agregar(producto);
    }

    public void modificarProducto(Producto producto) throws SQLException {
        productoDAO.modificar(producto);
    }

    public void eliminarProducto(int idProducto) throws SQLException {
        productoDAO.eliminar(idProducto);
    }

    public Producto obtenerProducto(int idProducto) throws SQLException {
        return productoDAO.obtener(idProducto);
    }

    public List<Producto> obtenerTodosProductos() throws SQLException {
        return productoDAO.obtenerTodos();
    }

    public void crearProducto(Producto producto) {
    }
}