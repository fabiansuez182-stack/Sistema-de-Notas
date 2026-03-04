package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import clases.ModeloDatos;
import clases.Procesos;
import entidades.Estudiante;

public class VentanaPromedio extends JFrame implements ActionListener {
    
    private JTextField txtDocumento;
    private JTextField txtNombre;
    private JTextField txtMateria;
    private JTextField txtNota1;
    private JTextField txtNota2;
    private JTextField txtNota3;
    private JButton btnCalcular;
    private JButton btnLimpiar;
    private JButton btnConsultaIndividual;
    private JButton btnLista;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JLabel lblResultadoTitulo;
    private JTextArea txtAreaResultado;
    private JScrollPane scrollResultado;
    
    Procesos misProcesos;
    ModeloDatos miModeloDatos;

    public VentanaPromedio() {
        misProcesos = new Procesos();
        miModeloDatos = new ModeloDatos();
        setTitle("Promedio estudiantes");
        setSize(659, 572); 
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JLabel lblTitulo = new JLabel("SISTEMA CONTROL DE NOTAS");
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 25));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(10, 26, 606, 59);
        add(lblTitulo);

        JLabel lblDocumento = new JLabel("Documento:");
        lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblDocumento.setBounds(24, 85, 72, 22);
        add(lblDocumento);

        txtDocumento = new JTextField();
        txtDocumento.setBounds(106, 88, 201, 19);
        add(txtDocumento);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNombre.setBounds(24, 117, 72, 22);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setColumns(10);
        txtNombre.setBounds(106, 120, 201, 19);
        add(txtNombre);

        JLabel lblMateria = new JLabel("Materia:");
        lblMateria.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblMateria.setBounds(348, 117, 72, 22);
        add(lblMateria);

        txtMateria = new JTextField();
        txtMateria.setBounds(430, 120, 180, 19);
        add(txtMateria);

        JLabel lblNota1 = new JLabel("Nota1:");
        lblNota1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNota1.setBounds(24, 159, 72, 22);
        add(lblNota1);

        txtNota1 = new JTextField();
        txtNota1.setColumns(10);
        txtNota1.setBounds(106, 162, 96, 19);
        add(txtNota1);

        JLabel lblNota2 = new JLabel("Nota2:");
        lblNota2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNota2.setBounds(227, 162, 72, 22);
        add(lblNota2);

        txtNota2 = new JTextField();
        txtNota2.setColumns(10);
        txtNota2.setBounds(309, 165, 96, 19);
        add(txtNota2);

        JLabel lblNota3 = new JLabel("Nota3:");
        lblNota3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNota3.setBounds(431, 162, 72, 22);
        add(lblNota3);

        txtNota3 = new JTextField();
        txtNota3.setColumns(10);
        txtNota3.setBounds(513, 165, 96, 19);
        add(txtNota3);

        lblResultadoTitulo = new JLabel("Opciones disponibles:");
        lblResultadoTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblResultadoTitulo.setBounds(30, 195, 586, 22);
        add(lblResultadoTitulo);

        btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(40, 235, 112, 21);
        btnCalcular.addActionListener(this);
        add(btnCalcular);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(170, 235, 112, 21);
        btnLimpiar.addActionListener(this);
        add(btnLimpiar);

        btnConsultaIndividual = new JButton("Consultar");
        btnConsultaIndividual.setBounds(375, 235, 112, 21);
        btnConsultaIndividual.addActionListener(this);
        add(btnConsultaIndividual);

        btnLista = new JButton("Lista");
        btnLista.setBounds(498, 235, 112, 21);
        btnLista.addActionListener(this);
        add(btnLista);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(375, 270, 112, 21);
        btnActualizar.addActionListener(this);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(498, 270, 112, 21);
        btnEliminar.addActionListener(this);
        add(btnEliminar);

        txtAreaResultado = new JTextArea();
        txtAreaResultado.setEditable(false); 
        txtAreaResultado.setFont(new Font("Tahoma", Font.BOLD, 14));
        scrollResultado = new JScrollPane(txtAreaResultado);
        scrollResultado.setBounds(30, 310, 586, 190);
        add(scrollResultado);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular) calcular(false);
        else if (e.getSource() == btnActualizar) calcular(true);
        else if (e.getSource() == btnLimpiar) limpiar();
        else if (e.getSource() == btnConsultaIndividual) consultaIndividual();
        else if (e.getSource() == btnLista) consultarLista();
        else if (e.getSource() == btnEliminar) eliminar();
    }

    private void calcular(boolean esActualizacion) {
        try {
            if (txtDocumento.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El Documento y el Nombre son obligatorios.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Estudiante miEstudiante = new Estudiante();
            miEstudiante.setDocumento(txtDocumento.getText().trim());
            miEstudiante.setNombre(txtNombre.getText().trim());
            miEstudiante.setMateria(txtMateria.getText().trim());
            miEstudiante.setNota1(Double.parseDouble(txtNota1.getText()));
            miEstudiante.setNota2(Double.parseDouble(txtNota2.getText()));
            miEstudiante.setNota3(Double.parseDouble(txtNota3.getText()));
            
            double promedio = misProcesos.calcularPromedio(miEstudiante);
            
            if (promedio != -1) {
                miEstudiante.setPromedio(promedio);
                
                String mensajeBD = esActualizacion ? miModeloDatos.actualizarEstudiante(miEstudiante) : miModeloDatos.registrarEstudiante(miEstudiante);
                
                String textoResultado = "Hola " + miEstudiante.getNombre() + ".\n"
                                      + "Su promedio es: " + String.format("%.2f", promedio) + "\n\n"
                                      + "Estado del Sistema: " + mensajeBD;
                
                if (promedio < 3.5) {
                    txtAreaResultado.setForeground(Color.RED);
                    txtAreaResultado.setText(textoResultado + "\n(REPROBADO)");
                } else {
                    txtAreaResultado.setForeground(Color.BLUE);
                    txtAreaResultado.setText(textoResultado + "\n(APROBADO)");
                }
                
            } else {
                txtAreaResultado.setForeground(Color.RED);
                txtAreaResultado.setText("Error: Revise los datos.\nLas notas no pueden ser negativas ni mayores a 5.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos en las notas.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiar() {
        txtDocumento.setText("");
        txtNombre.setText("");
        txtMateria.setText("");
        txtNota1.setText("0");
        txtNota2.setText("0");
        txtNota3.setText("0");
        txtAreaResultado.setForeground(Color.BLACK);
        txtAreaResultado.setText("Formulario limpiado. Listo para un nuevo registro.");
    }
    
    private void consultarLista() {
        String listaConsultada = miModeloDatos.imprimirListaEstudiantes();
        VentanaLista vLista = new VentanaLista(listaConsultada);
        vLista.setVisible(true);
    }
    
    private void consultaIndividual() {
        String doc = JOptionPane.showInputDialog(this, "Ingrese el documento del estudiante a consultar:");
        if (doc != null && !doc.trim().isEmpty()) {
            Estudiante est = miModeloDatos.consultaEstudiante(doc.trim());
            if (est != null) {
                txtDocumento.setText(est.getDocumento());
                txtNombre.setText(est.getNombre());
                txtMateria.setText(est.getMateria());
                txtNota1.setText(String.valueOf(est.getNota1()));
                txtNota2.setText(String.valueOf(est.getNota2()));
                txtNota3.setText(String.valueOf(est.getNota3()));
                
                txtAreaResultado.setForeground(Color.BLACK);
                txtAreaResultado.setText("Estudiante cargado correctamente desde la memoria.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encuentra el estudiante", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void eliminar() {
        String doc = JOptionPane.showInputDialog(this, "Ingrese el documento del estudiante a eliminar:");
        if (doc != null && !doc.trim().isEmpty()) {
            if (miModeloDatos.eliminarEstudiante(doc.trim())) {
                limpiar();
                txtAreaResultado.setForeground(Color.BLACK);
                txtAreaResultado.setText("El estudiante con documento " + doc + " fue eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el estudiante para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}