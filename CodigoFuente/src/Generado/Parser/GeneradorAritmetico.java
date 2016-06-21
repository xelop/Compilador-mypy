/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generado.Parser;

import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public class GeneradorAritmetico {
    public static void generarOperacion(String operacion, String val1, String val2, PilaSemantica pila){
        System.out.println(operacion);
        switch(operacion){
            case "+": 
                generarSuma(val1,val2,pila);
                break;
            case "-": 
                generarResta(val1,val2,pila);
                break;
            case "*": 
                generarMul(val1,val2,pila);
                break;
            case "/": 
                generarDiv(val1,val2,pila);
                break;
            case "%": 
                generarModulo(val1,val2,pila);
                break;
            case "**": 
                generarPot(val1,val2,pila);
                break;
                
        }
    }

    private static RegistroSemantico evalTercerNivel(RegistroSemantico val1, RegistroSemantico val2){
        String result = "";
        if(val1.valor == "POP")
            result += popCode();
        else
            result += "    MOV ecx , " + val1.valor+"\n";
        
        result += "    MOV edx,ecx" +"\n";
        
        if(val2.valor == "POP")
            result += popCode();
        else
            result += "    MOV ecx , " + val2.valor +"\n";
        
        result += "    MOV eax,ecx\n    MUL dx\n    PUSH eax\n";
        
        val1.valor = "POP";
        System.out.println(result);
        return val1;
    }

    private static String popCode(){
        return "    POP ecx\n ";
    }
    private static void generarSuma(String val1,String val2,PilaSemantica pila){
        if(val2.equals("POP"))
            pila.codigoActual += "    POP ecx\n";
        else
            pila.codigoActual += "    MOV ecx," + val2 +"\n";
        
        pila.numeroLineas++;
        
        if(val1.equals("POP"))
            pila.codigoActual += "    POP eax\n";
        else
            pila.codigoActual += "    MOV eax," + val1 + "\n";
        
        pila.numeroLineas++;
        pila.codigoActual += "    ADD eax,ecx ; resultado en eax\n    PUSH eax\n";
        pila.numeroLineas++;
        pila.numeroLineas++;
        
    }
    
    private static void generarResta(String val1, String val2,PilaSemantica pila){
        if(val2.equals("POP"))
            pila.codigoActual += "    POP ecx\n";
        else
            pila.codigoActual += "    MOV ecx," + val2 +"\n";
        pila.numeroLineas++;
        
        if(val1.equals("POP"))
            pila.codigoActual += "    POP eax\n";
        else
            pila.codigoActual += "    MOV eax," + val1 + "\n";
        
        pila.numeroLineas++;
        pila.codigoActual += "    SUB eax,ecx ; resultado en eax\n    PUSH eax\n";
        pila.numeroLineas++;
        pila.numeroLineas++;
    }
    
    private static void generarMul(String val1, String val2,PilaSemantica pila){
        if(val2.equals("POP"))
            pila.codigoActual += "    POP ecx\n";
        else
            pila.codigoActual += "    MOV ecx," + val2 +"\n";
        pila.numeroLineas++;
        
        if(val1.equals("POP"))
            pila.codigoActual += "    POP eax\n";
        else
            pila.codigoActual += "    MOV eax," + val1 + "\n";
        
        pila.numeroLineas++;
        
        pila.codigoActual += "    MUL cx ; resultado en dx:ax\n    MOV cx, dx  ;move upper half(16 bits) of result in cx\n" +
       "    SHL ecx, 16 ;shift the contents of ecx 16 bits to the left\n" +
       "    MOV cx, ax  ;move lower half(16 bits) of result in cxpush ax\n"+
       "    PUSH ecx\n";
        pila.numeroLineas += 5;
    }
     private static void generarDiv(String val1, String val2,PilaSemantica pila){
        if(val2.equals("POP"))
            pila.codigoActual += "    POP ecx\n";
        else
            pila.codigoActual += "    MOV ecx," + val2 +"\n";
        pila.numeroLineas++;
        
        if(val1.equals("POP"))
            pila.codigoActual += "    POP eax\n";
        else
            pila.codigoActual += "    MOV eax," + val1 + "\n";
        
        pila.numeroLineas++;
        
        pila.codigoActual += "    DIV ecx ; resultado en eax\n"+"    PUSH ecx\n";
        pila.numeroLineas += 2;
    }
     private static void generarModulo(String val1, String val2,PilaSemantica pila){
        if(val2.equals("POP"))
            pila.codigoActual += "    POP ecx\n";
        else
            pila.codigoActual += "    MOV ecx," + val2 +"\n";
        pila.numeroLineas++;
        
        if(val1.equals("POP"))
            pila.codigoActual += "    POP eax\n";
        else
            pila.codigoActual += "    MOV eax," + val1 + "\n";
        
        pila.numeroLineas++;
        
        pila.codigoActual += "    DIV ecx ; resultado en edx\n"+"    PUSH edx\n";
        pila.numeroLineas += 2;
    }
     private static void generarPot(String val1, String val2,PilaSemantica pila){
        if(val2.equals("POP"))
            pila.codigoActual += "    POP ecx\n";
        else
            pila.codigoActual += "    MOV ecx," + val2 +"\n";
        
        
        pila.codigoActual += "    SHL ecx,16\n    SHR ecx,16 ;limpia ecx para que solo quede cx debido a un valor muy alto\n";
        pila.numeroLineas+=3;
        
        if(val1.equals("POP"))
            pila.codigoActual += "    POP eax\n";
        else
            pila.codigoActual += "    MOV eax," + val1 + "\n";
        
        pila.codigoActual += "    SHL eax,16\n    SHR eax,16 ;limpia eax para que solo quede ax debido a un valor muy alto\n";
        pila.codigoActual += "    sig:\n    MUL ax \n    LOOP sig \n";
        
        pila.numeroLineas += 6;
        
        pila.codigoActual += "; resultado en dx:ax\n    MOV cx, dx  ;move upper half(16 bits) of result in cx\n" +
       "    SHL ecx, 16 ;shift the contents of ecx 16 bits to the left\n" +
       "    MOV cx, ax  ;move lower half(16 bits) of result in cxpush ax\n"+
       "    PUSH ecx\n";
        pila.numeroLineas += 5;
    }
}
