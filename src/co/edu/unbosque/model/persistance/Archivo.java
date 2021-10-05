package co.edu.unbosque.model.persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Archivo {
	private InputStreamReader entrada;
	private ObjectOutputStream salida;
	private File archivo;

	public Archivo(File archivo) {

		this.archivo = archivo;
		if (this.archivo.exists()) {
			System.out.println("Existe");
		} else {
			System.out.println("No existe");
		}
	}



	/**
	 * 			try {
				archivo.createNewFile();

			} catch (IOException e) {
				e.printStackTrace();
			}
	 */
	public String leerArchivo() {
		String salidaS = "";
		if (archivo.length() != 0) {
			try {
				
				entrada = new InputStreamReader(new FileInputStream(archivo));
				int data = entrada.read();
				while(data != -1){
				    char theChar = (char) data;
				    data = entrada.read();
				    salidaS = salidaS+theChar;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return salidaS;

	}
}
