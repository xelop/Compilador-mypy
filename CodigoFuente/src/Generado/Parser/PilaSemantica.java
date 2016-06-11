package Generado.Parser;

import java.util.ArrayList;


public class PilaSemantica {
    ArrayList<RegistroSemantico> lista;
    public String ambitoActual;//global, local, de clase, etc.
    
    public PilaSemantica(){
        lista = new ArrayList();
        ambitoActual = "";
    }
    
    public void push(RegistroSemantico pRegistro){
        lista.add(pRegistro);
    }
    public RegistroSemantico pop(){
        RegistroSemantico resultado = lista.get(lista.size() - 1);
        lista.remove(lista.size() - 1);
        return resultado;
    }
    public String imprimir(){
        String resultado = "";
        for(int i = lista.size() - 1; i>=0 ; i--){//imprime de atras para adelante
            resultado += lista.get(i).toString();
            resultado += "\n";
        }
        return resultado;
    }
    /* Se me ocurre que podemos hacer aquí las funciones que hicimos en clase */
    
    public void recuerdaTipo(Object pValor){
        RegistroSemantico registro =  new RegistroSemantico("TIPO",pValor,"");
        push(registro);
    }
    public void recuerdaId(Object pValor){
        RegistroSemantico registro = new RegistroSemantico("IDENTIFICADOR",pValor, ambitoActual);
        push(registro);
    }
}
