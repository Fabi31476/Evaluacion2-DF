/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Pais;
import Vista.vistaPrincipal;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ControladorPais {
    private final vistaPrincipal vista;
    private final ArrayList<Pais> listaPaises;

    public ControladorPais(vistaPrincipal vista) {
        this.vista = vista;
        this.listaPaises = new ArrayList<>();
        inicializar();
    }
    
    private void inicializar() {
        // Aquí se enlazan los botones
        vista.getBtnAgregar().addActionListener(e -> agregarPais());
        vista.getBtnBuscar().addActionListener(e -> buscarPais());
        vista.getBtnConsultar().addActionListener(e -> listarPorContinente());
        vista.getBtnModificar().addActionListener(e -> compararPaises());
    }

    private void agregarPais() {
        try {
            String codigo = vista.getTxtCodigo().getText();
            String nombre = vista.getTxtNombre().getText();
            String continente = vista.getTxtContinente().getText();
            int poblacion = Integer.parseInt(vista.getTxtPoblacion().getText());
            String capital = "Desconocida";

            Pais nuevo = new Pais(codigo, nombre, continente, poblacion, capital);
            listaPaises.add(nuevo);

            DefaultTableModel model = (DefaultTableModel) vista.getTablaPais().getModel();
            model.addRow(new Object[]{codigo, nombre, continente, poblacion});

            vista.limpiarCampos();
            JOptionPane.showMessageDialog(vista, "País agregado correctamente");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Error: la población debe ser un número");
        }
    }

    private void buscarPais() {
        String nombre = JOptionPane.showInputDialog(vista, "Ingrese el nombre del país a buscar:");
        for (Pais p : listaPaises) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                JOptionPane.showMessageDialog(vista, "País encontrado:\n" +
                        "Código: " + p.getCodigo() +
                        "\nNombre: " + p.getNombre() +
                        "\nContinente: " + p.getContinente() +
                        "\nPoblación: " + p.getPoblacion() +
                        "\nCapital: " + p.getCapital() +
                        "\nPIB: " + p.getPib() +
                        "\nForma de gobierno: " + p.getFormaGobierno());
                return;
            }
        }
        JOptionPane.showMessageDialog(vista, "País no encontrado");
    }

    private void listarPorContinente() {
        String continente = JOptionPane.showInputDialog(vista, "Ingrese el continente:");
        DefaultTableModel model = (DefaultTableModel) vista.getTablaPais().getModel();
        model.setRowCount(0);
        for (Pais p : listaPaises) {
            if (p.getContinente().equalsIgnoreCase(continente)) {
                model.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getContinente(), p.getPoblacion()});
            }
        }
    }

    private void compararPaises() {
        String pais1 = JOptionPane.showInputDialog(vista, "Ingrese el primer país a comparar:");
        String pais2 = JOptionPane.showInputDialog(vista, "Ingrese el segundo país a comparar:");

        Pais p1 = null, p2 = null;
        for (Pais p : listaPaises) {
            if (p.getNombre().equalsIgnoreCase(pais1)) p1 = p;
            if (p.getNombre().equalsIgnoreCase(pais2)) p2 = p;
        }

        if (p1 != null && p2 != null) {
            JOptionPane.showMessageDialog(vista, "Comparación de países:\n\n" +
                    p1.getNombre() + " vs " + p2.getNombre() + "\n" +
                    "Población: " + p1.getPoblacion() + " vs " + p2.getPoblacion() + "\n" +
                    "Continente: " + p1.getContinente() + " vs " + p2.getContinente() + "\n" +
                    "Capital: " + p1.getCapital() + " vs " + p2.getCapital());
        } else {
            JOptionPane.showMessageDialog(vista, "Uno o ambos países no encontrados");
        }
    }
}
