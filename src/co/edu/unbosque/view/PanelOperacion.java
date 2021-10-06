package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelOperacion extends JPanel {

    private JLabel[] labels;
    private JComboBox algortmos;
    private JLabel fondo;
    private JTextField palabra;
    private JButton boton;
    private JTextArea texto;


    public PanelOperacion() {
        setLayout(null);
        setVisible(false);
        setBackground(Color.RED);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        labels = new JLabel[2];
        iniciarLabelTexto(0, "Algoritmo para realizar busqueda: ", 390, 80, 70, 300, 13, Color.white);

        algortmos = new JComboBox();
        algortmos.setBounds(410, 130, 200, 25);
        algortmos.addItem("");
        algortmos.addItem("KMP");
        algortmos.addItem("BM");
        add(algortmos);

        iniciarLabelTexto(0, "Ingrese palabra a buscar: ", 390, 150, 70, 300, 13, Color.white);

        palabra = new JTextField();
        palabra.setBounds(410, 200, 200, 25);
        add(palabra);

        boton = new JButton("Buscar palabra");
        boton.setBackground(new Color(248, 244, 232, 255));
        boton.setActionCommand("BUSCAR");
        boton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBounds(470, 240, 80, 30);

        boton.setFont(new Font("Century Gothic", Font.PLAIN, 10));
        MouseListener ml = new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                Component c = evt.getComponent();
                c.setBackground(new Color(22, 106, 184, 255));
                c.setForeground(new Color(248, 244, 232, 255));
            }

            public void mouseExited(MouseEvent evt) {
                Component c = evt.getComponent();
                c.setBackground(new Color(248, 244, 232, 255));
                c.setForeground(new Color(22, 106, 184, 255));
            }
        };
        boton.addMouseListener(ml);
        add(boton);

        texto = new JTextArea();
        texto.setBounds(40, 40, 290, 500);
        texto.setBackground(new Color(248, 244, 232, 255));
        JScrollPane jp = new JScrollPane(texto);
        jp.setBounds(40, 40, 290, 500);
        jp.setBackground(new Color(248, 244, 232, 255));
        jp.setBorder(null);
        add(jp);

        fondo = new JLabel();
        devolverImagenLabel("panel2", "png", 700, 600, fondo);
        fondo.setBounds(0, 0, 700, 600);
        add(fondo);
    }


    public void devolverImagenLabel(String src, String tipo, int escalax, int escalay, JLabel b) {
        ImageIcon imagen1 = new ImageIcon(ClassLoader.getSystemResource("Images/" + src + "." + tipo));
        ImageIcon icon = new ImageIcon(imagen1.getImage().getScaledInstance(escalax, escalay, Image.SCALE_DEFAULT));
        b.setIcon(icon);
    }

    public String validarBox() {
        String res = "";
        if (algortmos.getSelectedIndex() == 0) {
            res = null;
        } else if (algortmos.getSelectedIndex() == 1) {
            res = "KMP";
        } else if (algortmos.getSelectedIndex() == 2) {
            res = "BM";
        }

        return res;
    }

    public void cargarTextoTxT(String text) {
        texto.append(text);
    }

    public void iniciarLabelTexto(int pos, String texto, int x, int y, int alto, int ancho, int tamañoLetra, Color colorLetra) {
        labels[pos] = new JLabel(texto);
        labels[pos].setBounds(x, y, ancho, alto);
        labels[pos].setFont(new Font("Century Gothic", Font.BOLD, tamañoLetra));
        labels[pos].setForeground(colorLetra);
        add(labels[pos]);
    }

    public String obtenerPalabra() {
        String res = palabra.getText();

        if (!res.isEmpty() && !res.equals(" ")) {
            return res;
        } else {
            return null;
        }
    }

    public JButton getBoton() {
        return boton;
    }
}
