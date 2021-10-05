package co.edu.unbosque.controller;

import co.edu.unbosque.model.persistance.Archivo;
import co.edu.unbosque.view.VentanaPrincipal;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Controller extends Component implements ActionListener {
    private VentanaPrincipal view;
    private Archivo archivo;
    private File file;

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
            file = new File(view.getPanelInicio().abrirChooser());
            archivo = new Archivo(file);
            String texto = archivo.leerArchivo(file);
            System.out.println(texto);
        }

    }
}
