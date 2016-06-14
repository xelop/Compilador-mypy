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
            resultado += "\n ";
        }
        return resultado;
    }
    public String getTope(){//devuelve el tipo del registro semantico del tope de la pila
        return lista.get(lista.size()-1).tipo;
    }
    public RegistroSemantico getPrimerTipo(){//devuelve el primer tipo en la lista
        for(int i = lista.size() - 1; i>=0 ; i--){
            if (lista.get(i).tipo.equals("TIPO")){
                return lista.get(i);
            }
        }
        return null;
    }
    public RegistroSemantico getPrimeraFuncion(){//devuelve la primera funcion en la pila
        for(int i = lista.size() - 1; i>=0 ; i--){
            if (lista.get(i).tipo.equals("FUNCION")){
                return lista.get(i);
            }
        }
        return null;
    }
    
    /* Se me ocurre que podemos hacer aquí las funciones que hicimos en clase */
    
    public void recuerdaTipo(Object pValor, int pLinea, int pColumna){//int,string,char,list
        RegistroSemantico registro =  new RegistroSemantico("TIPO",pValor,"","",pLinea,pColumna);
        push(registro);
    }
    public void recuerdaId(Object pValor, String pDato,int pLinea, int pColumna){
        RegistroSemantico registro = new RegistroSemantico("IDENTIFICADOR",pValor, ambitoActual,pDato,pLinea,pColumna);
        push(registro);
    }
    public void recuerdaFuncion(Object pValor, int pLinea, int pColumna){
        RegistroSemantico registro = new RegistroSemantico("FUNCION",pValor, "","",pLinea,pColumna);
        push(registro);
    }
}
