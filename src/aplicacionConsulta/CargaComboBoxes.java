package aplicacionConsulta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;

public class CargaComboBoxes {

    private ConexionBBDD conexion;

    public CargaComboBoxes(ConexionBBDD conexion) {
        this.conexion = conexion;
    }

    public void cargaDatos(JComboBox<String> secciones, JComboBox<String> paises) {
        Statement sentencia = null;
        ResultSet rs = null;

        try {
            sentencia = conexion.getConexion().createStatement();

            String consultaSecciones = "SELECT DISTINCT SECCION FROM Articulos";
            rs = sentencia.executeQuery(consultaSecciones);
            while (rs.next()) {
                secciones.addItem(rs.getString(1));
            }

            String consultaPaises = "SELECT DISTINCT PAISDEORIGEN FROM Articulos";
            rs = sentencia.executeQuery(consultaPaises);
            while (rs.next()) {
                paises.addItem(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (sentencia != null) sentencia.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
