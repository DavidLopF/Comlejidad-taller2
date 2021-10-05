package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private PanelInicio panelInicio;
    private PanelOperacion panelOperacion;

    public VentanaPrincipal() {
        setTitle("Internacionale FC");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout(10, 10));
        inicializarComponentes();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void inicializarComponentes() {
        panelInicio = new PanelInicio();


        panelOperacion = new PanelOperacion();
        add(panelInicio);

    }

    public void mensajeAlerta(String title, String message, Icon icon) {

        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public PanelInicio getPanelInicio() {
        return panelInicio;
    }

    public PanelOperacion getPanelOperacion() {
        return panelOperacion;
    }

    public ImageIcon devolverImagenButton(String src, String tipo, int escalax, int escalay) {
        ImageIcon imagen1 = new ImageIcon("Images/" +src + "." + tipo);
        ImageIcon icon = new ImageIcon(imagen1.getImage().getScaledInstance(escalax, escalay, Image.SCALE_DEFAULT));
        return icon;
    }

}
