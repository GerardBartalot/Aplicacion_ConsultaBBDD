package aplicacionConsulta;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Marco_Aplicacion extends JFrame {

    private JComboBox<String> secciones;
    private JComboBox<String> paises;
    private JTextArea resultado;
    private ConexionBBDD conexion;
    private ConsultaBBDD consultaBBDD;

    public Marco_Aplicacion() {

        setTitle("Consulta BBDD");
        setBounds(500, 300, 400, 400);
        setLayout(new BorderLayout());

        JPanel menus = new JPanel();
        menus.setLayout(new FlowLayout());

        secciones = new JComboBox<>();
        secciones.setEditable(false);
        secciones.addItem("Todos");

        paises = new JComboBox<>();
        paises.setEditable(false);
        paises.addItem("Todos");

        resultado = new JTextArea(4, 50);
        resultado.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultado);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        menus.add(secciones);
        menus.add(paises);

        add(menus, BorderLayout.NORTH);

        JButton botonConsulta = new JButton("Consulta");

        botonConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultaBBDD.ejecutaConsulta(secciones, paises, resultado);
            }
        });

        add(botonConsulta, BorderLayout.SOUTH);

        conexion = new ConexionBBDD();
        conexion.conectar();

        consultaBBDD = new ConsultaBBDD(conexion);  // AÑADIR esta línea

        new CargaComboBoxes(conexion).cargaDatos(secciones, paises);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                conexion.cerrar();
            }
        });
        
    }
    
}