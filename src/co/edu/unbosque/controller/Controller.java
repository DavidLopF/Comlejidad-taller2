package co.edu.unbosque.controller;

import co.edu.unbosque.model.Algoritmos;
import co.edu.unbosque.model.persistance.Archivo;
import co.edu.unbosque.view.VentanaPrincipal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class Controller extends Component implements ActionListener {

    private VentanaPrincipal view;
    private Archivo archivo;
    private Algoritmos algoritmos;
    private String texto;

    public Controller() {
        view = new VentanaPrincipal();
        algoritmos = new Algoritmos();
        texto = "";
        asignarListeners();

    }


    public void asignarListeners() {
        view.getPanelInicio().getChoose().addActionListener(this);
        view.getPanelOperacion().getBoton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("CHOOSE")) {
            try {
                File file = new File(view.getPanelInicio().abrirChooser());
                archivo = new Archivo(file);
                String temp = archivo.leerArchivo();
                texto = temp;
                view.getPanelOperacion().cargarTextoTxT(temp);
                cambiarPanel(view.getPanelOperacion());


            } catch (Exception a) {
                view.mensajeAlerta("Error", "Formato erroneo o no se selecciono archivo"
                        , view.devolverImagenButton("error", "png", 50, 50));
            }
        } else if (command.equals("BUSCAR")) {
            String select = view.getPanelOperacion().validarBox();
            if (select != null) {
                if (select == "KMP") { //validaciones kmp
                    String palabra = view.getPanelOperacion().obtenerPalabra();
                    if (palabra != null) {
                        palabra = palabra.toLowerCase(Locale.ROOT);
                        texto = texto.toLowerCase(Locale.ROOT);


                        System.out.println(texto);
                        ArrayList<Integer> resultado = algoritmos.KMP(texto, palabra);

                        for (int i = 0; i < resultado.size(); i++) {

                            if(i > 0){
                                int posicion = resultado.get(i);
                                int fin = posicion + palabra.length();

                                System.out.println(texto.substring(posicion - 1, fin - 1));
                            }
                        }


                    } else {
                        view.mensajeAlerta("Error", "Por favor ingrese palabra a buscar :)."
                                , view.devolverImagenButton("error", "png", 50, 50));
                    }

                } else if (select == "BP") { //validaciones del bp :)
                    String palabra = view.getPanelOperacion().obtenerPalabra();
                    if (palabra != null) {

                    } else {
                        view.mensajeAlerta("Error", "Por favor ingrese palabra a buscar :)."
                                , view.devolverImagenButton("error", "png", 50, 50));
                    }
                }

            } else {
                view.mensajeAlerta("Error", "No se ha seleccionado ningun algoritmo de busqueda."
                        , view.devolverImagenButton("error", "png", 50, 50));
            }
        }

    }

    public void cambiarPanel(Component panel) {
        view.getContentPane().removeAll();
        view.getContentPane().add(panel);
        panel.setVisible(true);
        view.getContentPane().repaint();
    }
}
