package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.File;

public class PanelInicio extends JPanel {

    private JLabel[] labels;
    private JLabel fondo;
    private JButton choose;

    public PanelInicio() {
        setLayout(null);
        setVisible(true);
        setBackground(Color.RED);
        inicializarComponentes();
    }


    private void inicializarComponentes() {

        labels = new JLabel[3];
        iniciarLabelTexto(0, "¡¡¡ Bienvenido !!!", 240, 130, 70, 300, 30, Color.white);
        iniciarLabelTexto(1, "Este programa toma un archivo txt, lo lee", 165, 250, 70, 500, 18, Color.black);
        iniciarLabelTexto(2, "y busca palabras en el archivo.", 205, 280, 70, 500, 18, Color.black);


        choose = new JButton("Buscar txt");
        choose.setBackground(new Color(248, 244, 232, 255));
        choose.setActionCommand("CHOOSE");
        choose.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        choose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        choose.setBounds(300, 350, 80, 50);

        choose.setFont(new Font("Century Gothic", Font.PLAIN, 10));
        MouseListener ml = new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Component c = evt.getComponent();
                c.setBackground(new Color(22, 106, 184, 255));
                c.setForeground(new Color(248, 244, 232, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                Component c = evt.getComponent();
                c.setBackground(new Color(248, 244, 232, 255));
                c.setForeground(new Color(22, 106, 184, 255));
            }
        };
        choose.addMouseListener(ml);
        add(choose);

        fondo = new JLabel();
        devolverImagenLabel("fondo", "png", 700, 600, fondo);
        fondo.setBounds(0, 0, 700, 600);
        add(fondo);
    }


    public void devolverImagenLabel(String src, String tipo, int escalax, int escalay, JLabel b) {
        ImageIcon imagen1 = new ImageIcon(ClassLoader.getSystemResource("Images/" + src + "." + tipo));
        ImageIcon icon = new ImageIcon(imagen1.getImage().getScaledInstance(escalax, escalay, Image.SCALE_DEFAULT));
        b.setIcon(icon);
    }

    public void iniciarLabelTexto(int pos, String texto, int x, int y, int alto, int ancho, int tamañoLetra, Color colorLetra) {
        labels[pos] = new JLabel(texto);
        labels[pos].setBounds(x, y, ancho, alto);
        labels[pos].setFont(new Font("Century Gothic", Font.BOLD, tamañoLetra));
        labels[pos].setForeground(colorLetra);
        add(labels[pos]);
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public String abrirChooser() {
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jf.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo TXT", "txt");
        jf.setFileFilter(filter);
        //mostrar el gestor de archivos y no deja hacer nada hasta que se selcione el archivo o me salga con cancelar
        jf.showOpenDialog(this);
        //agarre lo que seleciona
        File seleccion_ruta = jf.getSelectedFile();
        return seleccion_ruta.getAbsolutePath();

    }

    public JButton getChoose() {
        return choose;
    }

}

