package Generado.Parser;

import java.util.ArrayList;


public class InfoFuncion {
    //clase con métodos estáticos
    //hecha para obtener información de una funcion de una tabla de simobolos
    
    public static Integer getNumParametros(String nombreFuncion, TablaSimbolos tabla){
        //consigue el numero de parametros de una funcion dada
        ArrayList<Simbolo> simbolos = tabla.simbolos;
        Integer cantidadParametros = 0;
        for (Simbolo s: simbolos){
            if(s.ambito.equals("PARAMETRO") && s.funcion.equals(nombreFuncion)){
                cantidadParametros++;
            }
        }
        return cantidadParametros;
    }
    public static String getParametroN(String nombreFuncion, Integer pos, TablaSimbolos tabla){
        //devuelve el tipo de un parámetro en posicion n
        String resultado = "";
        ArrayList<Simbolo> simbolos = tabla.simbolos;
        for (Simbolo s: simbolos){
            if(s.ambito.equals("PARAMETRO") && s.funcion.equals(nombreFuncion)
                    ||s.numParametro.equals(pos)){
                resultado = s.tipoDato;
            }
        }
        return resultado;
    }
    
    public static String getTipoVariable(String nombre, String ambito, String funcion, TablaSimbolos tabla){
        //consigue el tipo de una variable dada
        String resultado = "";
        ArrayList<Simbolo> simbolos = tabla.simbolos;
        for (Simbolo s: simbolos){
            if(ambito.equals("GLOBAL")){
                if(s.nombre.equals(nombre) && s.ambito.equals("GLOBAL")){
                    resultado = s.tipo;
                }
            }
            else if(ambito.equals("ATRIBUTO")){
                if(s.nombre.equals(nombre) && s.ambito.equals("ATRIBUTO")){
                    resultado = s.tipo;
                }
            }
            else{//caso locales y parámetros
                if(s.nombre.equals(nombre) && s.funcion.equals(funcion)){
                    resultado = s.tipo;
                }
            }
        }
        return resultado;
    }  
    
    //ahora para obtener informacion de una funcion en una pilaSemantica
    
    public static Integer getNumParametros(String nombreFuncion, PilaSemantica pila){
        //consigue el numero de parametros de una funcion dada
        ArrayList<RegistroSemantico> lista = pila.lista;
        Integer cantidadParametros = 0;
        for(int i = lista.size() - 1; i>=0 ; i--){
            if(lista.get(i).tipo.equals("FUNCIONE") && lista.get(i).valor.toString().equals(nombreFuncion)){
                break;
            }
            cantidadParametros++;
        }
        return cantidadParametros;
    }
}
