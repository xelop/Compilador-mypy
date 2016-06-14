package Generado.Parser;

import java.util.ArrayList;

public class TablaSimbolos {
    public ArrayList<Simbolo> simbolos;
    public ArrayList<String> errores;
    
    public TablaSimbolos(){
        simbolos = new ArrayList();
        errores = new ArrayList();
    }
    
    
    public void agregarSimbolo(String pTipo, String pNombre, String pAmbito,String pTipoDato,String pFuncion, int pLinea, int pColumna){
        if(verificaRepetido(pTipo,pNombre,pAmbito,pFuncion)){
            errores.add("Identificador '" + pNombre +  "' repetido. Línea : " + pLinea);
            //crear variable ensamblador
            //crear rutina
        }
        else{
            Simbolo nuevoSimbolo = new Simbolo(pTipo, pNombre, pAmbito, pTipoDato,pFuncion ,pLinea, pColumna);
            simbolos.add(nuevoSimbolo);
        }
    }
    private Boolean verificaRepetido(String pTipo, String pNombre, String pAmbito,String pFuncion){
        Simbolo simboloActual;
        for(int i = simbolos.size() - 1; i>=0 ; i--){
            simboloActual = simbolos.get(i);
            if(pTipo.equals("FUNCION")){
                if(simboloActual.nombre.equals(pNombre) && simboloActual.tipo.equals(pTipo)){
                    return true;//ya existe
                }
            }
            else{//caso variables
                if(pAmbito.equals("GLOBAL")){//caso variable global
                    if(simboloActual.nombre.equals(pNombre) && simboloActual.tipo.equals(pTipo) && simboloActual.ambito.equals("GLOBAL")){
                        return true;//ya existe
                    } 
                }
                if(pAmbito.equals("ATRIBUTO")){//caso atributos
                    if(simboloActual.nombre.equals(pNombre) && simboloActual.tipo.equals(pTipo) && simboloActual.ambito.equals("ATRIBUTO")){
                        return true;//ya existe
                    } 
                }
                else{//es local o parametro
                    if(simboloActual.nombre.equals(pNombre) && simboloActual.tipo.equals(pTipo)
                            && simboloActual.funcion.equals(pFuncion) && (simboloActual.ambito.equals("LOCAL") || simboloActual.ambito.equals("PARAMETRO"))){
                        return true;//ya existe
                    }
                }
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
    
    public void insertarVariables(PilaSemantica pila){
        RegistroSemantico rActual = null;
        RegistroSemantico tipo = null;
        RegistroSemantico funcion = null;
        while(pila.getTope().equals("IDENTIFICADOR")){
            rActual = pila.pop();
            tipo = pila.getPrimerTipo();
            if(rActual.ambito.equals("GLOBAL")){
                agregarSimbolo(rActual.dato,rActual.valor.toString(),rActual.ambito,tipo.valor.toString(),"PROGRAMA",
                        rActual.linea,rActual.columna);
            }
            else{
                funcion = pila.getPrimeraFuncion();
                agregarSimbolo(rActual.dato,rActual.valor.toString(),rActual.ambito,tipo.valor.toString(),funcion.valor.toString(),
                        rActual.linea,rActual.columna);
            }
        }
        pila.pop();
    }
    public void insertarFuncion(PilaSemantica pila){
        RegistroSemantico funcion = pila.pop();
        agregarSimbolo(funcion.tipo,funcion.valor.toString(),funcion.ambito,"","",
                        funcion.linea,funcion.columna);
    }
}
