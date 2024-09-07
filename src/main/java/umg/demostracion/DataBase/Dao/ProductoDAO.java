package umg.demostracion.DataBase.Dao;

import umg.demostracion.DataBase.Model.Producto;

import java.sql.SQLException;
import java.util.List;

public interface ProductoDAO {
    void agregar(Producto producto) throws SQLException;
    void modificar(Producto producto) throws SQLException;
    void eliminar(int idProducto) throws SQLException;
    Producto obtener(int idProducto) throws SQLException;
    List<Producto> obtenerTodos() throws SQLException;
}
