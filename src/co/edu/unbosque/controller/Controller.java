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
                        palabra = limpiarString(palabra);
                        String text =  limpiarString(texto);
                        ArrayList<Integer> resultado = algoritmos.KMP(text, palabra);
                        view.getPanelOperacion().modificarTexto(text);
                        for (int i = 0; i < resultado.size(); i++) {
                            if(i > 0){
                                view.getPanelOperacion().subrrayarPalabra(resultado , palabra);
                            }
                        }



                    } else {
                        view.mensajeAlerta("Error", "Por favor ingrese palabra a buscar :)."
                                , view.devolverImagenButton("error", "png", 50, 50));
                    }

                } else if (select == "BP") { //validaciones del bp :)
                    String palabra = view.getPanelOperacion().obtenerPalabra();
                    if (palabra != null) {
                        palabra = palabra.toLowerCase(Locale.ROOT);
                        char textoLista[] = texto.toLowerCase(Locale.ROOT).toCharArray();
                        char pat[] = palabra.toCharArray();
                        String posiciones[] = (algoritmos.search(textoLista, pat)).split("-");
                        int cantidadRepetido = 0;
                        for (int i = posiciones.length - 1; i < posiciones.length; i++) {
                            cantidadRepetido = Integer.parseInt(posiciones[i]);
                            posiciones[i] = "";
                        }



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

    public String limpiarString(String text){
        text = text.toLowerCase(Locale.ROOT);
        text =  text.replace("á" , "a");
        text =  text.replace("é" , "e");
        text =  text.replace("í" , "i");
        text =  text.replace("ó" , "o");
        text =  text.replace("ú" , "u");
        return text;
    }



    public void cambiarPanel(Component panel) {
        view.getContentPane().removeAll();
        view.getContentPane().add(panel);
        panel.setVisible(true);
        view.getContentPane().repaint();
    }
}
