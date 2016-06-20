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
}
