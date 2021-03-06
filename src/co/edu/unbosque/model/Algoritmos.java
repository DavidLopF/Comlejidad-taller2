package co.edu.unbosque.model;


import java.util.ArrayList;

/**
 * authors: David Lopez, Juan Ruiz, Jose Navas, Daniel Niño, Juan Camilo Diaz
 */

public class Algoritmos {

    private int NO_OF_CHARS;

    /**
     * metodo para inicializar variables
     */
    public Algoritmos() {
        NO_OF_CHARS = 10000;
    }

    /**
     * Método encargado de hacer algoritmo de busqueda por fuerza bruta KMP
     *
     * @param cadena1   texto string.
     * @param subcadena palabra a buscar.
     * @return Posiciones en las que se encuentra la palabra
     */
    public ArrayList<Integer> KMP(String cadena1, String subcadena) {
        ArrayList<Integer> lugares = new ArrayList<Integer>();
        int lugar = 0;
        for (int a = 0; a < cadena1.length(); a++) {
            boolean comp = false;
            for (int b = 0; b < subcadena.length(); b++) {
                if (cadena1.charAt(a + b) != subcadena.charAt(b)) {
                    b = a + subcadena.length();
                } else if (b == subcadena.length() - 1) {
                    comp = true;
                    lugar = a + 1;
                }
            }
            if (comp == true) {
                lugares.add(lugar);
            }
        }
        return lugares;
    }


    /**
     * Método encargado el maximo entre dos números
     *
     * @param a número 1
     * @param b número 2
     * @return Boolean
     */
    public int max(int a, int b) {
        return (a > b) ? a : b;
    }

    /**
     * metodo para compararhasta los 256
     *
     * @param str
     * @param size
     * @param badchar
     */

    public void badCharHeuristic(char[] str, int size, int badchar[]) {
        for (int i = 0; i < NO_OF_CHARS; i++)
            badchar[i] = -1;
        for (int i = 0; i < size; i++)
            badchar[(int) str[i]] = i;
    }

    /**
     * Metodo para buscar el algoritmo por posicion
     *
     * @param txt string con el texto
     * @param pat palabra a buscar
     * @return posiciones con en las que coinciden el texto
     */

    public String search(char txt[], char pat[]) {
        int m = pat.length;
        int n = txt.length;
        String text = "";
        int cont = 0;

        int badchar[] = new int[NO_OF_CHARS];

        badCharHeuristic(pat, m, badchar);
        int s = 0;

        while (s <= (n - m)) {
            int j = m - 1;
            while (j >= 0 && pat[j] == txt[s + j])
                j--;
            if (j < 0) {
                cont++;
                text = text + s + "-";
                s += (s + m < n) ? m - badchar[txt[s + m]] : 1;

            } else
                s += max(1, j - badchar[txt[s + j]]);
        }
        return text + cont;
    }


}
