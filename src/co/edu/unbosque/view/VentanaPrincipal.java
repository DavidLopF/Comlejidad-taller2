package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

/**
 * authors: David Lopez, Juan Ruiz, Jose Navas, Daniel Niño, Juan Camilo Diaz
 */


public class VentanaPrincipal extends JFrame {

    private PanelInicio panelInicio;
    private PanelOperacion panelOperacion;

    /**
     * metodo para incializar el panel de la ventana Principal
     */
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
    /**
     * metodo para incializar el panel de la ventana Principal
     */
    private void inicializarComponentes() {
        panelInicio = new PanelInicio();


        panelOperacion = new PanelOperacion();
        add(panelInicio);

    }

    /**
     * metodo para el mensaje
     * @param title
     * @param message
     * @param icon
     */
    public void mensajeAlerta(String title, String message, Icon icon) {

        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public PanelInicio getPanelInicio() {
        return panelInicio;
    }

    public PanelOperacion getPanelOperacion() {
        return panelOperacion;
    }

    /**
     * metodo para devolver la imagen del boton
     * @param src
     * @param tipo
     * @param escalax
     * @param escalay
     * @return
     */
    public ImageIcon devolverImagenButton(String src, String tipo, int escalax, int escalay) {
        ImageIcon imagen1 = new ImageIcon("Images/" +src + "." + tipo);
        ImageIcon icon = new ImageIcon(imagen1.getImage().getScaledInstance(escalax, escalay, Image.SCALE_DEFAULT));
        return icon;
    }

}
