package Generado.Parser;

import java.util.ArrayList;
import java.util.Objects;
import sun.net.www.content.text.plain;


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
    public RegistroSemantico getPrimeraFuncionE(){//devuelve la primera funcionE en la pila
        for(int i = lista.size() - 1; i>=0 ; i--){
            if (lista.get(i).tipo.equals("FUNCIONE")){
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
    public void registrarFuncion(Object pValor, int pLinea, int pColumna, TablaSimbolos tabla){
        RegistroSemantico registro = new RegistroSemantico("FUNCIONE",pValor, "","",pLinea,pColumna);
        tabla.buscarFuncion("FUNCION",pValor.toString(),pLinea);
        push(registro);
    }
    public void registrarId(Object pValor, int pLinea, int pColumna, TablaSimbolos tabla){
        RegistroSemantico registro = new RegistroSemantico("IDENTIFICADORE",pValor,ambitoActual,"",pLinea,pColumna);
        RegistroSemantico funcion = getPrimeraFuncion();
        if (funcion != null)
            tabla.buscarVariable(pLinea,pValor.toString(),ambitoActual,getPrimeraFuncion().valor.toString());
        else
            tabla.buscarVariable(pLinea,pValor.toString(),ambitoActual,"PROGRAMA");
        push(registro);
    }
    public void registrarLiteral(Object pValor, String tipo ,int pLinea, int pColumna){
        //tipo : int, float, list, string, boolean y char
        //voy a meter el tipo en dato
        RegistroSemantico registro = new RegistroSemantico("LITERAL",pValor,"",tipo,pLinea,pColumna);
        push(registro);
    }
    public void evalFuncion(TablaSimbolos tabla){
        //evalua si una funcion cumple con numero de parmaetros y tipo adecuado
        RegistroSemantico funcionE = getPrimeraFuncionE();
        Integer canParametrosActual = InfoFuncion.getNumParametros(funcionE.valor.toString(), this);
        Integer canParametrosReal = InfoFuncion.getNumParametros(funcionE.valor.toString(), tabla);
        if(!Objects.equals(canParametrosReal, canParametrosActual)){
            tabla.errores.add("Llamada a funcion: '" + funcionE.valor.toString() + "' con cantidad incorrecta de parametros"
            + ". Línea: " + funcionE.linea);
        }
        else{
            
        }
        System.out.println(canParametrosReal);
    }
}
