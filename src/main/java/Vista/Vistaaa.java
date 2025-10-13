/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Controlador;
import Modelo.Pais;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class Vistaaa extends JFrame {

    private JTextField txtCodigo, txtNombre, txtContinente, txtPoblacion, txtBuscar, txtFiltro;
    private JButton btnAgregar, btnEliminar, btnBuscar, btnFiltrar, btnTop;
    private JTable tabla;
    private DefaultTableModel modelo;
    private final Controlador controlador;
    private int codigoSeleccionado = -1;

    public Vistaaa() {
        controlador = new Controlador();
        setTitle("Gestión de Países");
        setSize(650, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        initComponentes();
        setVisible(true);
    }

    private void initComponentes() {
        // Etiquetas y campos de texto
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 20, 80, 25);
        add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(100, 20, 100, 25);
        add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 50, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 50, 100, 25);
        add(txtNombre);

        JLabel lblCont = new JLabel("Continente:");
        lblCont.setBounds(20, 80, 80, 25);
        add(lblCont);

        txtContinente = new JTextField();
        txtContinente.setBounds(100, 80, 100, 25);
        add(txtContinente);

        JLabel lblPob = new JLabel("Población:");
        lblPob.setBounds(20, 110, 80, 25);
        add(lblPob);

        txtPoblacion = new JTextField();
        txtPoblacion.setBounds(100, 110, 100, 25);
        add(txtPoblacion);

        // Botones
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(220, 20, 100, 25);
        add(btnAgregar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(220, 50, 100, 25);
        add(btnEliminar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(350, 20, 120, 25);
        add(txtBuscar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(480, 20, 120, 25);
        add(btnBuscar);

        txtFiltro = new JTextField();
        txtFiltro.setBounds(350, 50, 120, 25);
        add(txtFiltro);

        btnFiltrar = new JButton("Filtrar Continente");
        btnFiltrar.setBounds(480, 50, 150, 25);
        add(btnFiltrar);

        btnTop = new JButton("Top Población");
        btnTop.setBounds(350, 80, 250, 25);
        add(btnTop);

        // Tabla
        modelo = new DefaultTableModel(new String[]{"Código", "Nombre", "Continente", "Población"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 160, 600, 180);
        add(scroll);

        // Eventos
        btnAgregar.addActionListener((ActionEvent e) -> agregarPais());
        btnEliminar.addActionListener((ActionEvent e) -> eliminarPais());
        btnBuscar.addActionListener((ActionEvent e) -> buscarPais());
        btnFiltrar.addActionListener((ActionEvent e) -> filtrarPorContinente());
        btnTop.addActionListener((ActionEvent e) -> topPoblados());

        tabla.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                codigoSeleccionado = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            }
        });
    }

    private void agregarPais() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            String nombre = txtNombre.getText();
            String cont = txtContinente.getText();
            int poblacion = Integer.parseInt(txtPoblacion.getText());

            Pais p = new Pais(codigo, nombre, cont, poblacion);
            controlador.agregarPais(p);
            actualizarTabla(controlador.listarPaises());

            txtCodigo.setText("");
            txtNombre.setText("");
            txtContinente.setText("");
            txtPoblacion.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos numéricos.");
        }
    }

    private void eliminarPais() {
        if (codigoSeleccionado != -1) {
            controlador.eliminarPais(codigoSeleccionado);
            actualizarTabla(controlador.listarPaises());
            codigoSeleccionado = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un país para eliminar.");
        }
    }

    private void buscarPais() {
        String nombre = txtBuscar.getText().trim();
        Pais encontrado = controlador.buscarPaisPorNombre(nombre);
        if (encontrado != null) {
            ArrayList<Pais> resultado = new ArrayList<>();
            resultado.add(encontrado);
            actualizarTabla(resultado);
        } else {
            JOptionPane.showMessageDialog(this, "País no encontrado.");
        }
    }

    private void filtrarPorContinente() {
        String cont = txtFiltro.getText().trim();
        ArrayList<Pais> filtrados = controlador.filtrarPorContinente(cont);
        if (filtrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay países en ese continente.");
        } else {
            actualizarTabla(filtrados);
        }
    }

    private void topPoblados() {
        ArrayList<Pais> top = controlador.topPaisesPoblados(5);
        actualizarTabla(top);
    }

    private void actualizarTabla(ArrayList<Pais> lista) {
        modelo.setRowCount(0);
        for (Pais p : lista) {
            modelo.addRow(new Object[]{p.getCodigo(), p.getNombrePais(), p.getContinente(), p.getPoblacion()});
        }
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Vistaaa.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }


/**
 *
 * @author fchmm
 */

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    }   
           

     

        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

