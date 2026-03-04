package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VentanaLista extends JFrame implements ActionListener {
    
    private JTextArea txtAreaLista;
    private JScrollPane scrollLista;
    private JButton btnCerrar;

    public VentanaLista(String datosLista) {
        setTitle("Lista Consolidada de Estudiantes");
        setSize(659, 572);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        iniciarComponentes(datosLista);
    }

    private void iniciarComponentes(String datosLista) {
        JLabel lblTitulo = new JLabel("LISTA DE ESTUDIANTES");
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 25));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(10, 26, 606, 59);
        add(lblTitulo);

        txtAreaLista = new JTextArea();
        txtAreaLista.setText(datosLista);
        txtAreaLista.setEditable(false);
        txtAreaLista.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        scrollLista = new JScrollPane(txtAreaLista);
        scrollLista.setBounds(30, 100, 586, 380);
        add(scrollLista);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(273, 495, 112, 21);
        btnCerrar.addActionListener(this);
        add(btnCerrar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCerrar) {
            this.dispose();
        }
    }
}