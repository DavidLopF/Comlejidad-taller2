package co.edu.unbosque.controller;

import co.edu.unbosque.model.Algoritmos;
import co.edu.unbosque.model.persistance.Archivo;
import co.edu.unbosque.view.VentanaPrincipal;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Controller extends Component implements ActionListener {

    private VentanaPrincipal view;
    private Archivo archivo;

    public Controller() {
        view = new VentanaPrincipal();
        asignarListeners();

    }
    

    
    public void asignarListeners() {
        view.getPanelInicio().getChoose().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("CHOOSE")) {
           try{
               File file = new File(view.getPanelInicio().abrirChooser());
               archivo =  new Archivo(file);
               String a = archivo.leerArchivo();
               view.getPanelOperacion().cargarTextoTxT(a);
               cambiarPanel(view.getPanelOperacion());


           }catch (Exception a){
               view.mensajeAlerta("Error", "Formato erroneo o no se selecciono archivo"
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
