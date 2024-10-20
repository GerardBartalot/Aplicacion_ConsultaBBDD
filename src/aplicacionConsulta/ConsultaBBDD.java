package aplicacionConsulta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class ConsultaBBDD {

    private ConexionBBDD conexion;

    public ConsultaBBDD(ConexionBBDD conexion) {
        this.conexion = conexion;
    }

    public void ejecutaConsulta(JComboBox<String> secciones, JComboBox<String> paises, JTextArea resultado) {
        ResultSet rs = null;
        PreparedStatement enviaConsulta = null;
        resultado.setText(""); 

        try {
            String seccion = (String) secciones.getSelectedItem();
            String pais = (String) paises.getSelectedItem();

            String consultaArticulos = "SELECT NOMBREARTICULO, SECCION, PRECIO, PAISDEORIGEN FROM Articulos WHERE (? IS NULL OR SECCION=?) AND (? IS NULL OR PAISDEORIGEN=?)";
            enviaConsulta = conexion.getConexion().prepareStatement(consultaArticulos);

            if ("Todos".equals(seccion)) {
                enviaConsulta.setString(1, null);
                enviaConsulta.setString(2, null);
            } else {
                enviaConsulta.setString(1, seccion);
                enviaConsulta.setString(2, seccion);
            }

            if ("Todos".equals(pais)) {
                enviaConsulta.setString(3, null);
                enviaConsulta.setString(4, null);
            } else {
                enviaConsulta.setString(3, pais);
                enviaConsulta.setString(4, pais);
            }

            rs = enviaConsulta.executeQuery();

            while (rs.next()) {
                resultado.append(rs.getString(1));    
                resultado.append(", ");              
                resultado.append(rs.getString(2));              
                resultado.append(", ");              
                resultado.append(rs.getString(3));              
                resultado.append(", ");              
                resultado.append(rs.getString(4));              
                resultado.append("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (enviaConsulta != null) enviaConsulta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
