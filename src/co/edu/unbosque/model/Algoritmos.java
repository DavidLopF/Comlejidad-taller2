package co.edu.unbosque.model;


import java.util.ArrayList;

public class Algoritmos {

    public ArrayList<Integer> KMP(String cadena1, String subcadena) {
        ArrayList<Integer> lugares = new ArrayList<Integer>();
        int lugar = 0;
        for(int a=0;a<cadena1.length();a++){
            boolean comp = false;
            for(int b=0;b<subcadena.length();b++){
                if(cadena1.charAt(a+b)!=subcadena.charAt(b)){
                    b = a+subcadena.length();
                }
                else if(b==subcadena.length()-1){
                    comp = true;
                    lugar = a+1;
                }
            }
            if(comp == true){
                lugares.add(lugar);
            }
        }
        return lugares;
    }
}
