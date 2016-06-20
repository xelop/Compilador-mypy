package Generado.Parser;

import java.util.ArrayList;
import java.util.Objects;
import sun.net.www.content.text.plain;


public class PilaSemantica {
    ArrayList<RegistroSemantico> lista;
    public String ambitoActual;//global, local, de clase, etc.
    public int contadorExp = 0;
    public int contador;//para generar etiquetas
    public String codigoActual;
    public Integer numeroLineas;//numero de lineas a aumentar
    public boolean errorSintactico;
    
    public PilaSemantica(){
        lista = new ArrayList();
        ambitoActual = "";
        contador = 0;
        codigoActual = "";
        numeroLineas = 0;
        errorSintactico = false;
    }
    
    public void setError(){
        errorSintactico = true;
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
    public RegistroSemantico getPrimerIF(){//devuelve la primera funcionE en la pila
        for(int i = lista.size() - 1; i>=0 ; i--){
            if (lista.get(i).tipo.equals("IF")){
                return lista.get(i);
            }
        }
        return null;
    }
    
    /* Se me ocurre que podemos hacer aquí las funciones que hicimos en clase */
    
    public void recuerdaTipo(Object pValor, int pLinea, int pColumna){//int,string,char,list
        if(!errorSintactico){
            RegistroSemantico registro =  new RegistroSemantico("TIPO",pValor,"","",pLinea,pColumna);
            push(registro);
        }
    }
    public void recuerdaId(Object pValor, String pDato,int pLinea, int pColumna){
        if(!errorSintactico){
            RegistroSemantico registro = new RegistroSemantico("IDENTIFICADOR",pValor, ambitoActual,pDato,pLinea,pColumna);
            push(registro);
        }
    }
    public void recuerdaFuncion(Object pValor, int pLinea, int pColumna,TablaSimbolos tabla){
        if(!errorSintactico){
            RegistroSemantico registro = new RegistroSemantico("FUNCION",pValor, "","",pLinea,pColumna);
            tabla.agregarSimbolo(registro.tipo,registro.valor.toString(),registro.ambito,"","",
                            registro.linea,registro.columna);
            push(registro);
        }
    }
    public void registrarFuncion(Object pValor, int pLinea, int pColumna, TablaSimbolos tabla){
        if(!errorSintactico){
            RegistroSemantico registro = new RegistroSemantico("FUNCIONE",pValor, "","",pLinea,pColumna);
            tabla.buscarFuncion("FUNCION",pValor.toString(),pLinea);
            push(registro);
        }
    }
    public void registrarId(Object pValor, int pLinea, int pColumna, TablaSimbolos tabla){
        if(!errorSintactico){
            RegistroSemantico registro = new RegistroSemantico("IDENTIFICADORE",pValor,ambitoActual,"",pLinea,pColumna);
            RegistroSemantico funcion = getPrimeraFuncion();
            if (funcion != null)
                tabla.buscarVariable(pLinea,pValor.toString(),ambitoActual,getPrimeraFuncion().valor.toString());
            else
                tabla.buscarVariable(pLinea,pValor.toString(),ambitoActual,"PROGRAMA");
            push(registro);
        }
    }
    public void registrarLiteral(Object pValor, String tipo ,int pLinea, int pColumna){
        //tipo : int, float, list, string, boolean y char
        //voy a meter el tipo en dato
        if(!errorSintactico){
            RegistroSemantico registro = new RegistroSemantico("LITERAL",pValor,"",tipo,pLinea,pColumna);
            push(registro);
        }
    }
    public void registrarOperador(Object pValor, String tipo ,int pLinea, int pColumna){
        //tipo : int, float, list, string, boolean y char
        //voy a meter el tipo en dato
        if(!errorSintactico){
            RegistroSemantico registro = new RegistroSemantico(tipo,pValor,"","OPERADOR",pLinea,pColumna);
            push(registro);
        }
    }
    public void registrarOperadorBinario(Object pValor, String tipo ,int pLinea, int pColumna){
        //tipo : int, float, list, string, boolean y char
        //voy a meter el tipo en dato
        if(!errorSintactico){
            RegistroSemantico registro = new RegistroSemantico(tipo,pValor,"","OPERADOR",pLinea,pColumna);
            push(registro);
        }
    }
    public void evalFuncion(TablaSimbolos tabla){
        //evalua si una funcion cumple con numero de parmaetros y tipo adecuado
        if(!errorSintactico){
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
        
    }
    public void evalExpresion(TablaSimbolos tabla, Object arbol){
        if(!errorSintactico){
            boolean primerLiteral = false;
            boolean comparacion = false;
            boolean floatFound = false;
            boolean binary = false;
            String error = "";
            String literal = "";
            RegistroSemantico primerRegistro = null;
            //Cambiar idents por su tipo
            for(int index = lista.size()-contadorExp; index < lista.size(); index++){
                lista.set(index,transformarIdent(lista.get(index), tabla));
            }

            System.out.println(lista);

            for(int index = lista.size()-contadorExp; index < lista.size(); index++){
                RegistroSemantico reg = lista.get(index);
                if(!primerLiteral && reg.tipo.equals("LITERAL")){
                    literal = reg.dato;
                    primerRegistro= reg;
                    primerLiteral = true;
                }else{
                    if(reg.tipo.equals("LITERAL")){
                        if((literal.equals("int") || literal.equals("boolean") || literal.equals("float")) && 
                                (reg.dato.equals("float") || (reg.dato.equals("int")) || (reg.dato.equals("boolean")) )){
                            error = "";
                        }
                        else if((literal.equals("char") || literal.equals("string") ) && 
                                (reg.dato.equals("char") || (reg.dato.equals("string")))){
                            error = "";
                        } else if (!reg.dato.equals(literal)){
                            error = "3Error en los tipos de las expresiones. En el valor: "+ reg.valor+ " Linea: " + reg.linea;
                            break;
                        }
                        if(reg.dato.equals("float") || literal.equals("float") ){
                            floatFound = true;
                            if(binary){
                               error = "4Error en los tipos de las expresiones. En el valor: "+ reg.valor+ " Linea: " + reg.linea;
                               break;
                            }
                        }

                    }else if (reg.dato.equals("OPERADOR")){

                        if(reg.tipo.equals("COMPESPECIFICO")){
                            comparacion = true;
                        }

                        if(reg.tipo.equals("COMPGENERAL")){
                            comparacion = true;
                            error = "";
                            primerLiteral = false;
                        }
                        else if((literal.equals("char") || literal.equals("string") ) && !(reg.tipo.equals("SUMA")|| reg.tipo.equals("COMPESPECIFICO"))){
                            error = "5Error en los tipos de las expresiones. En el valor: "+ reg.valor+ " Linea: " + reg.linea;
                        } else if(reg.tipo.equals("BINARIO")){

                            if(floatFound){
                                error = "6Error en los tipos de las expresiones. En el valor: "+ reg.valor+ " Linea: " + reg.linea;
                                break;
                            }else
                                binary = true;

                        }
                    }
                }

            }
            if(comparacion){
                primerRegistro.dato = "boolean";
            }
            if(!error.isEmpty()){
                tabla.errores.add(error);
            }
            vaciarPilaN(contadorExp);
            System.out.println(lista);
            push(primerRegistro);
            contadorExp = 1;
            System.out.println(lista);
        }
    }
    public void evalExpresion2(TablaSimbolos tabla,Object arbol){
        if(!errorSintactico){
            System.out.println(lista);
             Nodo arb = (Nodo) arbol;
                    arb.print();
            RegistroSemantico Exp1 = transformarIdent(pop(),tabla);
            boolean comparacion = false;
            boolean generar = true;
            boolean unaExp = true;
            ArrayList<RegistroSemantico> aritmetica = new ArrayList<>();
            contadorExp-=1;
            while(contadorExp!=0){
                unaExp = false;
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
                                generar = false;
                                contadorExp++;
                        }
                        else if(Exp1.dato.equals("char") && (Exp2.dato.equals("string"))){
                                Exp2.dato="string";
                                push(Exp2);
                                generar = false;
                                contadorExp++;
                        }
                        else if(Exp1.dato.equals(Exp2.dato) && (Exp1.dato.equals("char")||Exp1.dato.equals("string"))){
                            push(Exp2);
                            generar = false;
                            contadorExp++;
                        } else if(!validarTipos(Exp2, Exp1, tabla,aritmetica,op)){
                            tabla.errores.add("Error en los tipos de las expresiones. En el valor: "+ Exp1.valor+ " Linea: " + Exp1.linea);
                            vaciarPilaN(contadorExp+contadorExp);
                            push(Exp2);
                            generar = false;
                            contadorExp = 1;
                        } else if(op.tipo.equals("SUMA"))
                            generar = generarTrue(generar);
                        if(op.tipo.equals("COMPESPECIFICO"))
                            comparacion = true; //para que cambie el resultado a bool al final

                    }else if(op.tipo.equals("COMPGENERAL")){
                        push(Exp2);
                        contadorExp++;
                        generar = false;
                        comparacion = true;
                    }else if(!validarTipos(Exp2, Exp1, tabla,aritmetica,op)){
                         tabla.errores.add("Error en los tipos de las expresiones. En el valor: "+ Exp1.valor+ " Linea: " + Exp1.linea);
                         vaciarPilaN(contadorExp+contadorExp);
                         push(Exp2);
                         generar = false;
                         contadorExp = 1;
                    }else
                        generar = generarTrue(generar);
                }
                else if(getTope().equals("BINARIO")){
                    RegistroSemantico op = pop();
                    RegistroSemantico Exp2 = transformarIdent(pop(),tabla);
                    contadorExp -=1;

                    if(Exp1.dato.equals("int") && Exp2.dato.equals("int")){
                        push(Exp2);
                        generar =false;

                        contadorExp++;
                    }else{
                        tabla.errores.add("Error en los tipos de las expresiones. En el valor: "+ Exp1.valor+ " Linea: " + Exp1.linea);
                        vaciarPilaN(contadorExp+contadorExp);
                        push(Exp2);
                        generar =false;
                        contadorExp = 1;
                    }

                }else if(getTope().equals("ASIGNACION")){
                    RegistroSemantico op = pop();
                    RegistroSemantico Exp2 = transformarIdent(pop(),tabla);
                    contadorExp -=1;
                    if(Exp1.dato.equals(Exp2.dato)){
                        push(Exp2);
                        generar = false;
                        contadorExp++;
                    }else{
                        tabla.errores.add("Error en los tipos de la asignacion. En el valor: "+ Exp1.valor+ " Linea: " + Exp1.linea);
                        vaciarPilaN(contadorExp+contadorExp);
                        push(Exp2);
                        generar =false;
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
                generar = false;
            }
            push(Exp1);

            if(generar && !unaExp){
                    aritmetica.add(Exp1);
                    //System.out.println(aritmetica);
                    //Nodo arb = (Nodo) arbol;
                    arb.print();
                    //GeneradorAritmetico.generarExpresion(aritmetica);

            }
        }
    }
    //Metodo para limpiar pila cuando se ejecuta desde CualquierCosa
    public void finExpresion(){
        if(!errorSintactico){
            contadorExp--;
            pop();
        }
    }
    private boolean generarTrue(boolean val){
        if(!errorSintactico){
            if(val == false)
                return false;
            return true;
        }
        return false;
    }
    private boolean validarTipos(RegistroSemantico Exp2, RegistroSemantico Exp1, TablaSimbolos tabla,ArrayList<RegistroSemantico> lista,RegistroSemantico op){
        if(!errorSintactico){
            if(Exp1.dato.equals(Exp2.dato) && !(Exp1.dato.equals("string") || Exp1.dato.equals("char"))){
                    Exp2 = floatorBool(Exp1, Exp2);
                    push(Exp2);
                    contadorExp++;
                    lista.add(Exp1);
                    lista.add(op);
                    return true;
            }
            else if(Exp1.dato.equals("int") && (Exp2.dato.equals("float") || Exp2.dato.equals("boolean"))){
                    Exp2 = floatorBool(Exp1, Exp2);
                    push(Exp2);
                    contadorExp++;
                    lista.add(Exp1);
                    lista.add(op);
                    return true;
            }
            else if(Exp1.dato.equals("float") && (Exp2.dato.equals("int") || Exp2.dato.equals("boolean"))){
                    Exp2 = floatorBool(Exp1, Exp2);
                    push(Exp2);
                    contadorExp++;
                    lista.add(Exp1);
                    lista.add(op);
                    return true;
            }
            else if(Exp1.dato.equals("boolean") && (Exp2.dato.equals("int") || Exp2.dato.equals("float"))){
                    Exp2 = floatorBool(Exp1, Exp2);
                    push(Exp2);
                    lista.add(Exp1);
                    lista.add(op);
                    contadorExp++;
                    return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    private void vaciarPilaN(int n){
        if(!errorSintactico){
            while(n>0){
                pop();
                n--;
            }
        }
    }
    private RegistroSemantico transformarIdent(RegistroSemantico registro,TablaSimbolos tabla){
        if(!errorSintactico){
            if(registro.tipo.equals("IDENTIFICADORE")){
                String dato = registro.dato;
                System.out.println(dato);
                RegistroSemantico funcion = getPrimeraFuncion();
                if(funcion == null)
                    registro.dato = InfoFuncion.getTipoVariable( registro.valor.toString(), ambitoActual,
                        "PROGRAMA", tabla);
                else
                    registro.dato = InfoFuncion.getTipoVariable( registro.valor.toString(), ambitoActual,
                        funcion.valor.toString(), tabla);
                registro.tipo = "LITERAL";
                return registro;
            }
            else
                return registro;
        }
        return null;
    }
    private RegistroSemantico floatorBool(RegistroSemantico r1,RegistroSemantico r2){
        if(!errorSintactico){
            if(r1.dato.equals("float")||r2.dato.equals("float"))
                r2.dato = "float";
            else if(r1.dato.equals("boolean") && r2.dato.equals("boolean"))
                r2.dato = "int";
            else if(r1.dato.equals("int")||r2.dato.equals("int"))
                r2.dato = "int";
            return r2;
        }
        return null;
    }
    /*--------IF ELSE--------*/
    public void startIf(Object pValor, int pLinea, int pColumna){
        if(!errorSintactico){
            RegistroSemantico funcion = getPrimeraFuncion();
            String ambito = "PROGRAMA"; 
            if (funcion !=  null){
                ambito = funcion.dato.toString();
            } 
            RegistroSemantico registro = new RegistroSemantico("IF",pValor.toString(),ambito,"",pLinea,pColumna);
            registro.elseLabel = "elseLabel" + contador;
            contador++;
            registro.exitLabel = "exitLabel" + contador;
            contador++;
            push(registro);
        }
    }
    
    public void testIf(){
        if(!errorSintactico){
            RegistroSemantico r;
            while(!this.getTope().equals("IF")){
                r = pop();//sacamos toda la expresion. Da igual. No hay que generar este código.
            }
            RegistroSemantico iff =  this.getPrimerIF();
            codigoActual += "    MOV ax,0\n";
            numeroLineas++;
            codigoActual += "    CMP ax,0\n";
            numeroLineas++;
            codigoActual += "    JNZ " + iff.elseLabel + " ; ahora viene codigo de if\n";
            numeroLineas++;
        }
    }
    
    public void startElse(){
        if(!errorSintactico){
            RegistroSemantico iff =  this.getPrimerIF();
            codigoActual += "    JMP " + iff.exitLabel + "\n";
            numeroLineas++;
            codigoActual += "    " + iff.elseLabel + ": ; ahora viene codigo de else\n";
            numeroLineas++;
        }
        
    }
    
    public void endIf(TablaSimbolos tabla){
        if(!errorSintactico){
            RegistroSemantico iff =  this.getPrimerIF();
            codigoActual += "    " + iff.exitLabel + ": ;termina bloque if-else\n";
            numeroLineas++;
            numeroLineas++;
            if (iff.ambito.equals("PROGRAMA")){
                if(tabla.errores.isEmpty() && errorSintactico == false){
                    tabla.generador.insertarCodigo(codigoActual, iff.ambito, numeroLineas);
                }
                codigoActual = "";
                numeroLineas = 0;//reseteamos
            }
            pop();
        }
    }
}


