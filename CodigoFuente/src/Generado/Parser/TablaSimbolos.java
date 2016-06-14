package Generado.Parser;

import java.util.ArrayList;

public class TablaSimbolos {
    public ArrayList<Simbolo> simbolos;
    public ArrayList<String> errores;
    
    public TablaSimbolos(){
        simbolos = new ArrayList();
        errores = new ArrayList();
    }
    
    
    public void agregarSimbolo(String pTipo, String pNombre, String pAmbito,String pTipoDato, int pLinea, int pColumna){
        if(verificaRepetido(pTipo,pNombre)){
            errores.add("Identificador '" + pNombre +  "' repetido. Línea : " + pLinea);
        }
        else{
            Simbolo nuevoSimbolo = new Simbolo(pTipo, pNombre, pAmbito, pTipoDato ,pLinea, pColumna);
            simbolos.add(nuevoSimbolo);
        }
    }
    private Boolean verificaRepetido(String pTipo, String pNombre){
        for(int i = simbolos.size() - 1; i>=0 ; i--){
            if(simbolos.get(i).nombre.equals(pNombre) && simbolos.get(i).tipo.equals(pTipo)){
                return true;//ya existe
            }
        }
        return false;
    }
    
    public String imprimir(){
        String resultado = "";
        for(int i = 0; i<=simbolos.size() - 1 ; i++){
            resultado += simbolos.get(i).toString();
            resultado += "\n ";
        }
        return resultado;
    }
    public String imprimirErrores(){
        String resultado = "";
        for(int i = 0; i<=errores.size() - 1 ; i++){//imprime de atras para adelante
            resultado += errores.get(i);
            resultado += "\n ";
        }
        return resultado;
    }
    
    public void insertarSimbolos(PilaSemantica pila){
        RegistroSemantico rActual = null;
        RegistroSemantico tipo = null;
        while(pila.getTope().equals("IDENTIFICADOR")){
            rActual = pila.pop();
            tipo = pila.getPrimerTipo();
            agregarSimbolo(rActual.dato,rActual.valor.toString(),rActual.ambito,tipo.valor.toString(),rActual.linea,rActual.columna);
        }
        pila.pop();
    }
}
