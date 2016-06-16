package Generado.Parser;

import java.util.ArrayList;
import java.util.Objects;
import sun.net.www.content.text.plain;


public class PilaSemantica {
    ArrayList<RegistroSemantico> lista;
    public String ambitoActual;//global, local, de clase, etc.
    public int contadorExp = 0;
    
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
        if(lista.isEmpty())
            return "";
        else
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
    public void recuerdaFuncion(Object pValor, int pLinea, int pColumna,TablaSimbolos tabla){
        RegistroSemantico registro = new RegistroSemantico("FUNCION",pValor, "","",pLinea,pColumna);
        tabla.agregarSimbolo(registro.tipo,registro.valor.toString(),registro.ambito,"","",
                        registro.linea,registro.columna);
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
    public void registrarOperador(Object pValor, String tipo ,int pLinea, int pColumna){
        //tipo : int, float, list, string, boolean y char
        //voy a meter el tipo en dato
       
        RegistroSemantico registro = new RegistroSemantico(tipo,pValor,"","OPERADOR",pLinea,pColumna);
        push(registro);
    }
    public void registrarOperadorBinario(Object pValor, String tipo ,int pLinea, int pColumna){
        //tipo : int, float, list, string, boolean y char
        //voy a meter el tipo en dato
        RegistroSemantico registro = new RegistroSemantico("BINARIO",pValor,"",tipo,pLinea,pColumna);
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
            //ahora vamos a chequear tipos
            String tipo1 = "";//tipo de la llamada
            String tipo2 = "";//tipo real
            for(int i = canParametrosActual; i>0; i--){
                tipo2 = InfoFuncion.getParametroN(funcionE.valor.toString(), i, tabla);
                tipo1 = InfoFuncion.getParametroN(funcionE.valor.toString(), i, this, tabla);
                if(!tipo2.equals(tipo1)){
                    tabla.errores.add("Llamada a funcion: '" + funcionE.valor.toString() + "' con tipo incorrecto de parametro"
                        + ". Línea: " + funcionE.linea);
                }
            }
        }
        
        //lineas para sacar parametros y funcion de pila
        vaciarPilaN(canParametrosActual); 
        
    }
    public void evalExpresion(TablaSimbolos tabla){
        RegistroSemantico Exp1 = transformarIdent(pop(),tabla);
        boolean comparacion = false;
        contadorExp-=1;
        while(contadorExp!=0){
            if(getTope().equals("OPERADOR") 
                    || getTope().equals("SUMA")
                    || getTope().equals("COMPGENERAL")
                    || getTope().equals("COMPESPECIFICO")){
                RegistroSemantico op = pop();
                RegistroSemantico Exp2 = transformarIdent(pop(),tabla);
                contadorExp -=1;
                if(op.tipo.equals("SUMA")|| op.tipo.equals("COMPESPECIFICO")){
                    
                    if(Exp1.dato.equals("string") && Exp2.dato.equals("char")){
                            Exp2.dato="string";
                            push(Exp2);
                            contadorExp++;
                    }
                    else if(Exp1.dato.equals("char") && (Exp2.dato.equals("string"))){
                            Exp2.dato="string";
                            push(Exp2);
                            contadorExp++;
                    }
                    else if(!validarTipos(Exp2, Exp1, tabla)){
                        tabla.errores.add("Error en los tipos de las expresiones. En el valor: "+ Exp1.valor+ " Linea: " + Exp1.linea);
                        vaciarPilaN(contadorExp+contadorExp);
                        push(Exp2);
                        contadorExp = 1;
                    }
                    if(op.tipo.equals("COMPESPECIFICO"))
                        comparacion = true; //para que cambie el resultado a bool al final
                    
                }else if(op.tipo.equals("COMPGENERAL")){
                    push(Exp2);
                    contadorExp++;
                    comparacion = true;
                }else if(!validarTipos(Exp2, Exp1, tabla)){
                     tabla.errores.add("Error en los tipos de las expresiones. En el valor: "+ Exp1.valor+ " Linea: " + Exp1.linea);
                     vaciarPilaN(contadorExp+contadorExp);
                     push(Exp2);
                     contadorExp = 1;
                }
            }
            else if(getTope().equals("BINARIO")){
                RegistroSemantico op = pop();
                RegistroSemantico Exp2 = transformarIdent(pop(),tabla);
                contadorExp -=1;
                
                if(Exp1.dato.equals("int") && Exp2.dato.equals("int")){
                    push(Exp2);
                    
                    contadorExp++;
                }else{
                    tabla.errores.add("Error en los tipos de las expresiones. En el valor: "+ Exp1.valor+ " Linea: " + Exp1.linea);
                    vaciarPilaN(contadorExp+contadorExp);
                    push(Exp2);
                    contadorExp = 1;
                }
                    
            }
            Exp1 = transformarIdent(pop(),tabla);
            contadorExp-=1;
            
      
        }
        if (getTope().equals("UNARIO")){
                if(Exp1.dato.equals("string")||Exp1.dato.equals("char")){
                    tabla.errores.add("Error en los tipos de las expresiones. En el valor: "+ Exp1.valor+ " Linea: " + Exp1.linea);
                }
                pop();
                
        }else if(getTope().equals("NOT")){
            pop();
            comparacion = true;
        }
        if(comparacion){
            Exp1.dato="boolean";
        }
        push(Exp1);
    }
    //Metodo para limpiar pila cuando se ejecuta desde CualquierCosa
    public void finExpresion(){
        pop();
    }
    private boolean validarTipos(RegistroSemantico Exp2, RegistroSemantico Exp1, TablaSimbolos tabla){
        if(Exp1.dato.equals(Exp2.dato)){
                Exp2 = floatorBool(Exp1, Exp2);
                push(Exp2);
                contadorExp++;
                return true;
        }
        else if(Exp1.dato.equals("int") && (Exp2.dato.equals("float") || Exp2.dato.equals("boolean"))){
                Exp2 = floatorBool(Exp1, Exp2);
                push(Exp2);
                contadorExp++;
                return true;
        }
        else if(Exp1.dato.equals("float") && (Exp2.dato.equals("int") || Exp2.dato.equals("boolean"))){
                Exp2 = floatorBool(Exp1, Exp2);
                push(Exp2);
                contadorExp++;
                return true;
        }
        else if(Exp1.dato.equals("boolean") && (Exp2.dato.equals("int") || Exp2.dato.equals("float"))){
                Exp2 = floatorBool(Exp1, Exp2);
                push(Exp2);
                contadorExp++;
                return true;
        }
        else{
            return false;
        }
    }
    private void vaciarPilaN(int n){
        while(n>0){
            pop();
            n--;
        }
    }
    private RegistroSemantico transformarIdent(RegistroSemantico registro,TablaSimbolos tabla){
        if(registro.tipo.equals("IDENTIFICADORE")){
            String dato = registro.dato;
            RegistroSemantico funcion = getPrimeraFuncion();
            if(funcion == null)
                registro.dato = InfoFuncion.getTipoVariable( registro.valor.toString(), ambitoActual,
                    "PROGRAMA", tabla);
            else
                registro.dato = InfoFuncion.getTipoVariable( registro.valor.toString(), ambitoActual,
                    funcion.valor.toString(), tabla);
            registro.tipo = dato;
            return registro;
        }
        else
            return registro;
    }
    private RegistroSemantico floatorBool(RegistroSemantico r1,RegistroSemantico r2){
        if(r1.dato.equals("float")||r2.dato.equals("float"))
            r2.dato = "float";
        else if(r1.dato.equals("boolean") && r2.dato.equals("boolean"))
            r2.dato = "int";
        else if(r1.dato.equals("int")||r2.dato.equals("int"))
            r2.dato = "int";
        return r2;
    }
}


