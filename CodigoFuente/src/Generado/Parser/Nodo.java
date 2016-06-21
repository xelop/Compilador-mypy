/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generado.Parser;

/**
 *
 * @author Adrian
 */
public class Nodo {
    Nodo nodoIzq;
    Nodo nodoDer;
    Object valor;
    
    static String result;
    
    public Nodo(Object pNodoIzq, Object pNodoDer, Object pValor){
        nodoDer = (Nodo)pNodoDer;
        nodoIzq = (Nodo)pNodoIzq;
        valor = pValor;
    }
    public void print(){
       
        if(nodoDer != null){
            nodoDer.print();
        }       
        if (nodoIzq != null){
            nodoIzq.print();
        }
        System.out.println(valor);
        
    }
    public Nodo generarCodigo(PilaSemantica pila){
        if(nodoDer == null){
            return this;
        }else{
            Nodo op1 = nodoDer.generarCodigo(pila);
            Nodo op2 = nodoIzq.generarCodigo(pila);
            GeneradorAritmetico.generarOperacion(valor.toString(),op2.valor.toString(), op1.valor.toString(),pila);
            valor = "POP";
            return this;
        }
    }
}
