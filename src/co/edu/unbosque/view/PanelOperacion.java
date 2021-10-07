package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * authors: David Lopez, Juan Ruiz, Jose Navas, Daniel Niño, Juan Camilo Diaz
 */

public class PanelOperacion extends JPanel {

    private JLabel[] labels;
    private JComboBox algortmos;
    private JLabel fondo;
    private JTextField palabra;
    private JButton boton;
    private JTextArea texto;

    /**
     * Método constructor de la clase PanelOperacion
     */
    public PanelOperacion() {
        setLayout(null);
        setVisible(false);
        setBackground(Color.RED);
        inicializarComponentes();
    }

    /**
     * Método encargado de buscar componentes de la vista.
     */

    private void inicializarComponentes() {
        labels = new JLabel[5];
        iniciarLabelTexto(0, "Algoritmo para realizar busqueda: ", 390, 80, 70, 300, 13, Color.white);

        algortmos = new JComboBox();
        algortmos.setBounds(410, 130, 200, 25);
        algortmos.addItem("");
        algortmos.addItem("KMP");
        algortmos.addItem("BM");
        add(algortmos);

        iniciarLabelTexto(0, "Ingrese palabra a buscar: ", 390, 150, 70, 300, 13, Color.white);
        iniciarLabelTexto(0, "Ingrese palabra a buscar: ", 390, 150, 70, 300, 13, Color.white);
        iniciarLabelTexto(1, "", 430, 350, 70, 300, 13, Color.white);
        iniciarLabelTexto(2, "", 430, 380, 70, 300, 13, Color.white);

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


    /**
     * Método encarcado de cargar un icon en un label
     *
     * @param src nombre del archivo
     * @param tipo tipo de archivo
     * @param escalax escala x del icon
     * @param escalay escala y del icon
     * @param b
     */

    public void devolverImagenLabel(String src, String tipo, int escalax, int escalay, JLabel b) {
        ImageIcon imagen1 = new ImageIcon(ClassLoader.getSystemResource("Images/" + src + "." + tipo));
        ImageIcon icon = new ImageIcon(imagen1.getImage().getScaledInstance(escalax, escalay, Image.SCALE_DEFAULT));
        b.setIcon(icon);
    }

    public void cambiarFondo(String palabra, String encontrada) {
        remove(fondo);
        labels[1].setText(palabra);
        labels[2].setText(encontrada);
        devolverImagenLabel("busqueda", "png", 700, 600, fondo);
        add(fondo);
        repaint();
    }



    public String validarBox() {
        String res = "";
        if (algortmos.getSelectedIndex() == 0) {
            res = null;
        } else if (algortmos.getSelectedIndex() == 1) {
            res = "KMP";
        } else if (algortmos.getSelectedIndex() == 2) {
            res = "BP";
        }

        return res;
    }

    /**
     * @param text
     */
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

    public void iniciarLabelTexto(int pos, String texto, int x, int y, int alto, int ancho, int tamañoLetra, Color colorLetra, Boolean visible) {
        labels[pos] = new JLabel(texto);
        labels[pos].setVisible(visible);
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

    public void subrrayarPalabra(ArrayList<Integer> posiciones, String palabra, String algoritmo) {
        DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
        Highlighter h = texto.getHighlighter();
        h.removeAllHighlights();
        try {
            if (algoritmo.equals("KMP")) {
                for (int i = 0; i < posiciones.size(); i++) {

                    int posicion = posiciones.get(i) - 1;
                    int fin = posicion + palabra.length();

                    h.addHighlight(posicion, fin, highlightPainter);

                }
                repaint();
            } else if (algoritmo.equals("BP")) {
                for (int i = 0; i < posiciones.size(); i++) {

                    int posicion = posiciones.get(i) - 1;
                    int fin = posicion + palabra.length() + 1;

                    h.addHighlight(posicion, fin, highlightPainter);

                }
            }
            repaint();

        } catch (BadLocationException e) {
            e.printStackTrace();
        }

    }

    public JButton getBoton() {
        return boton;
    }
}
