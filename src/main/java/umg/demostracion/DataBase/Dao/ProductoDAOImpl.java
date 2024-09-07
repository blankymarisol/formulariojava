package umg.demostracion.DataBase.Dao;

import umg.demostracion.DataBase.Conexion.ConexionBD;
import umg.demostracion.DataBase.Model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {
    @Override
    public void agregar(Producto producto) throws SQLException {
        String sql = "INSERT INTO tb_producto (descripcion, origen) VALUES (?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, producto.getDescripcion());
            pstmt.setString(2, producto.getOrigen());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    producto.setIdProducto(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void modificar(Producto producto) throws SQLException {
        String sql = "UPDATE tb_producto SET descripcion = ?, origen = ? WHERE id_producto = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getDescripcion());
            pstmt.setString(2, producto.getOrigen());
            pstmt.setInt(3, producto.getIdProducto());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int idProducto) throws SQLException {
        String sql = "DELETE FROM tb_producto WHERE id_producto = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProducto);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Producto obtener(int idProducto) throws SQLException {
        String sql = "SELECT * FROM tb_producto WHERE id_producto = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProducto);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                            rs.getInt("id_producto"),
                            rs.getString("descripcion"),
                            rs.getString("origen")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Producto> obtenerTodos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM tb_producto";
        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("descripcion"),
                        rs.getString("origen")
                ));
            }
        }
        return productos;
    }
}