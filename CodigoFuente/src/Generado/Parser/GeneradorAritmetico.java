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
                generarMul(val1,val2,pila);
                break;
            case "%": 
                generarMul(val1,val2,pila);
                break;
            case "**": 
                generarMul(val1,val2,pila);
                break;
                
        }
    }
    private void evalParen(){
        
    }
    private void evalPot(){
        
    }
    private static RegistroSemantico evalTercerNivel(RegistroSemantico val1, RegistroSemantico val2){
        String result = "";
        if(val1.valor == "POP")
            result += popCode();
        else
            result += "mov ecx , " + val1.valor+"\n";
        
        result += "mov edx,ecx" +"\n";
        
        if(val2.valor == "POP")
            result += popCode();
        else
            result += "mov ecx , " + val2.valor +"\n";
        
        result += "mov eax,ecx\nmul dx\npush eax\n";
        
        val1.valor = "POP";
        System.out.println(result);
        return val1;
    }
    private void evalSuma(){
        
    }
    private static String popCode(){
        return "pop ecx\n ";
    }
    private static void generarSuma(String val1,String val2,PilaSemantica pila){
        if(val2.equals("POP"))
            pila.codigoActual += "pop ecx\n ";
        else
            pila.codigoActual += "mov ecx," + val2 +"\n";
        
        pila.numeroLineas++;
        
        if(val1.equals("POP"))
            pila.codigoActual += "pop eax\n ";
        else
            pila.codigoActual += "mov eax," + val1 + "\n";
        
        pila.numeroLineas++;
        pila.codigoActual += "add eax,ecx ; resultado en eax\n push eax\n";
        pila.numeroLineas++;
        pila.numeroLineas++;
        
    }
    
    private static void generarResta(String val1, String val2,PilaSemantica pila){
        if(val2.equals("POP"))
            pila.codigoActual += "pop ecx\n ";
        else
            pila.codigoActual += "mov ecx," + val2 +"\n";
        pila.numeroLineas++;
        
        if(val1.equals("POP"))
            pila.codigoActual += "pop eax\n ";
        else
            pila.codigoActual += "mov eax," + val1 + "\n";
        
        pila.numeroLineas++;
        pila.codigoActual += "sub eax,ecx ; resultado en eax\n push eax\n";
        pila.numeroLineas++;
        pila.numeroLineas++;
    }
    
    private static void generarMul(String val1, String val2,PilaSemantica pila){
        if(val2.equals("POP"))
            pila.codigoActual += "pop ecx\n ";
        else
            pila.codigoActual += "mov ecx," + val2 +"\n";
        pila.numeroLineas++;
        
        if(val1.equals("POP"))
            pila.codigoActual += "pop eax\n ";
        else
            pila.codigoActual += "mov eax," + val1 + "\n";
        
        pila.numeroLineas++;
        
        pila.codigoActual += "mul cx ; resultado en dx:ax\n mov cx, dx  ;move upper half(16 bits) of result in cx\n" +
       "shl ecx, 16 ;shift the contents of ecx 16 bits to the left\n" +
       "mov cx, ax  ;move lower half(16 bits) of result in cxpush ax\n"+
       "push ecx\n";
        pila.numeroLineas += 5;
    }
     private static void generarDiv(String val1, String val2,PilaSemantica pila){
        if(val2.equals("POP"))
            pila.codigoActual += "pop ecx\n ";
        else
            pila.codigoActual += "mov ecx," + val2 +"\n";
        pila.numeroLineas++;
        
        if(val1.equals("POP"))
            pila.codigoActual += "pop eax\n ";
        else
            pila.codigoActual += "mov eax," + val1 + "\n";
        
        pila.numeroLineas++;
        
        pila.codigoActual += "div ecx ; resultado en eax"+"push ecx\n";
        pila.numeroLineas += 2;
    }
     private static void generarModulo(String val1, String val2,PilaSemantica pila){
        if(val2.equals("POP"))
            pila.codigoActual += "pop ecx\n ";
        else
            pila.codigoActual += "mov ecx," + val2 +"\n";
        pila.numeroLineas++;
        
        if(val1.equals("POP"))
            pila.codigoActual += "pop eax\n ";
        else
            pila.codigoActual += "mov eax," + val1 + "\n";
        
        pila.numeroLineas++;
        
        pila.codigoActual += "div ecx ; resultado en edx"+"push edx\n";
        pila.numeroLineas += 2;
    }
     private static void generarPot(String val1, String val2,PilaSemantica pila){
        if(val2.equals("POP"))
            pila.codigoActual += "pop ecx\n ";
        else
            pila.codigoActual += "mov ecx," + val2 +"\n";
        
        
        pila.codigoActual += "shl ecx,16\nshr ecx,16 ;limpia ecx para que solo quede cx debido a un valor muy alto\n";
        pila.numeroLineas+=3;
        
        if(val1.equals("POP"))
            pila.codigoActual += "pop eax\n ";
        else
            pila.codigoActual += "mov eax," + val1 + "\n";
        
        pila.codigoActual += "shl eax,16\nshr eax,16 ;limpia eax para que solo quede ax debido a un valor muy alto\n";
        pila.codigoActual += "sig:\n mul ax \n loop sig \n";
        
        pila.numeroLineas += 6;
        
        pila.codigoActual += "; resultado en dx:ax\n mov cx, dx  ;move upper half(16 bits) of result in cx\n" +
       "shl ecx, 16 ;shift the contents of ecx 16 bits to the left\n" +
       "mov cx, ax  ;move lower half(16 bits) of result in cxpush ax\n"+
       "push ecx\n";
        pila.numeroLineas += 5;
    }
}
