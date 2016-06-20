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
    public static void generarExpresion(ArrayList<RegistroSemantico> lista){
        RegistroSemantico r1;
        RegistroSemantico r2;
        
            for(int index = 0; index < lista.size(); index++){
                if(lista.get(index).valor.equals("*")){
                    lista.set(index-1, evalTercerNivel(lista.get(index-1),lista.get(index+1)));
                    lista.remove(index);
                    lista.remove(index);
                    index--;
                } 
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
        
        result += "mov eax,ecx\nmul dx\npush eax";
        
        val1.valor = "POP";
        System.out.println(result);
        return val1;
    }
    private void evalSuma(){
        
    }
    private static String popCode(){
        return "pop ecx\n ";
    }
}


