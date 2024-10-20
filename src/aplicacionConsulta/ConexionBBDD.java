package aplicacionConsulta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {

    private Connection miConexion;

    public void conectar() {
        try {
            miConexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/InventarioArticulos", 
                "root", 
                ""
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConexion() {
        return miConexion;
    }

    public void cerrar() {
        try {
            if (miConexion != null && !miConexion.isClosed()) {
                miConexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
