package Generado.Parser;

import java.util.ArrayList;

public class TablaSimbolos {
    public ArrayList<Simbolo> simbolos;
    public ArrayList<String> errores;
    public Integer numParametro;
    public GeneradorCodigo generador;
    public Boolean errorSintactico;
    
    public TablaSimbolos(){
        simbolos = new ArrayList();
        errores = new ArrayList();
        numParametro = 1;
        try{
           generador = new GeneradorCodigo(); 
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        errorSintactico = false;
    }
    
    public void setError(){
        errorSintactico = true;
    }
    
    
    public void agregarSimbolo(String pTipo, String pNombre, String pAmbito,String pTipoDato,String pFuncion, int pLinea, int pColumna){
        if(verificaRepetido(pTipo,pNombre,pAmbito,pFuncion)){
            errores.add("Identificador '" + pNombre +  "' repetido. Línea : " + pLinea);
        }
        else{
            Simbolo nuevoSimbolo = null;
            if (pAmbito.equals("PARAMETRO")){
                 nuevoSimbolo = new Simbolo(pTipo, pNombre, pAmbito, pTipoDato,pFuncion ,pLinea, pColumna,numParametro.toString());
                 numParametro++;
            }
            else{
                nuevoSimbolo = new Simbolo(pTipo, pNombre, pAmbito, pTipoDato,pFuncion ,pLinea, pColumna,"");
            }
            
            simbolos.add(nuevoSimbolo);
            if(nuevoSimbolo.ambito.equals("GLOBAL") && nuevoSimbolo.tipo.equals("VARIABLE") && errores.isEmpty() && errorSintactico == false){
                generador.insertarVariableGlobal(nuevoSimbolo.nombre, nuevoSimbolo.tipoDato);
            }
            if(nuevoSimbolo.tipo.equals("FUNCION") && errores.isEmpty() && errorSintactico == false){
                generador.insertarFuncion(nuevoSimbolo.nombre);
            }
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
                else if(pAmbito.equals("ATRIBUTO")){//caso atributos
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
    public void buscarFuncion(String pTipo,String pNombre, int pLinea){
        Boolean encontrada = false;
        for(int i = 0; i<=simbolos.size() - 1 ; i++){
            if(simbolos.get(i).tipo.equals(pTipo) && simbolos.get(i).nombre.equals(pNombre)){
                encontrada = true;
            }
        }
        if (!encontrada){
            errores.add("Funcion: '" + pNombre +  "' no definida en la linea : " + pLinea);
        }
        
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
        if(!errorSintactico){
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
                else if(rActual.ambito.equals("ATRIBUTO")){
                    agregarSimbolo(rActual.dato,rActual.valor.toString(),rActual.ambito,tipo.valor.toString(),"CLASE",
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
    }
    public void insertarFuncion(PilaSemantica pila){
        if(!errorSintactico){
            RegistroSemantico funcion = pila.pop();
            /*agregarSimbolo(funcion.tipo,funcion.valor.toString(),funcion.ambito,"","",
                            funcion.linea,funcion.columna);*/
            if(errores.isEmpty() && errorSintactico == false){
                generador.insertarCodigo(pila.codigoActual, "da igual", pila.numeroLineas);
            }
            pila.codigoActual = "";
            pila.numeroLineas = 0;//reseteamos
            numParametro = 1;
        }
    }
    
    public void buscarVariable(int pLinea,String pNombre,String pAmbito,String pFuncion){
        Simbolo simboloActual;
        Boolean encontrado = false;
        for(int i = simbolos.size() - 1; i>=0 ; i--){
            simboloActual = simbolos.get(i);
            if(simboloActual.nombre.equals(pNombre)){
                if(pAmbito.equals("LOCAL")){
                    if(!(simboloActual.tipo.equals("FUNCION"))
                                && (simboloActual.funcion.equals(pFuncion) 
                                || simboloActual.ambito.equals("GLOBAL") 
                                || simboloActual.ambito.equals("ATRIBUTO"))
                                ){

                        encontrado = true;
                    }
                }
                else if(pAmbito.equals("GLOBAL")){
                     if((simboloActual.ambito.equals("GLOBAL") 
                                || simboloActual.ambito.equals(""))){

                        encontrado = true; 
                    }
                }
            }
        }
        if (!encontrado){
            errores.add("Identificador: '" + pNombre +  "' no definida en la linea : " + pLinea);
        }
    }
}