package Generado.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class GeneradorCodigo {
    Integer lineaA;//es para el codigo principal
    public PrintWriter writer;
    Integer cantidadVariables=0;
    
    public GeneradorCodigo() throws FileNotFoundException, UnsupportedEncodingException{
        writer = new PrintWriter("Prueba.asm", "UTF-8");
        writer.println(".MODEL small");
        writer.println(".486");
        writer.println(".STACK 1000h");
        writer.println("");
        writer.println(".DATA");
        writer.println("");
        writer.println(".CODE");
        writer.println("start PROC");
        writer.println("mov ax, @data");
        writer.println("mov ds, ax");
        writer.println();
        lineaA = 10;
        finalizarCodigo();
        
    }
    
    public void finalizarCodigo(){
        writer.println("");
        writer.println("    MOV ax, 4c00h");
        writer.println("    INT 21h");
        writer.println();
        writer.println("start ENDP");
        writer.println("end start");
        writer.close();
    }
    
    public void insertarVariableGlobal(String nombre, String tipo){
        try{
            Integer lineaVariable = 5;
            if (tipo.equals("int")){
                insertaN("    " + nombre + " dw 0    ; es un entero", lineaVariable);
            }
            if (tipo.equals("float")){
                insertaN("    " + nombre + " dq 0.0 ; es un float", lineaVariable);
            }
            if (tipo.equals("boolean")){
                insertaN("    " + nombre + " db 0    ; es un booleano", lineaVariable);
            }
            if (tipo.equals("string")){
                insertaN("    " + nombre + " db \"\"   ; es un string", lineaVariable);
            }
            if (tipo.equals("char")){
                insertaN("    " + nombre + " db ''     ; es un char", lineaVariable);
            }
            cantidadVariables++;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void insertarFuncion(String nombre){
        String funcion = "" + nombre + " proc near\n";
        funcion += "    ;guarda registros\n";
        funcion += "    ;cuerpo de la función:\n\n";
        funcion += "    ;saca registros\n";
        funcion += "" + nombre + " endp\n";
        try{
            insertaN(funcion,14);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void insertarCodigo(String codigo, String ambito, Integer aumentoLineas){
        try{
            if(ambito.equals("PROGRAMA")){
                insertaN(codigo,lineaA+cantidadVariables);
                lineaA += aumentoLineas;   
            }//es una funcion
            else{
                insertaN(codigo,18);
            }
                
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    private void insertaN(String texto, int numLinea) throws IOException{
        File archivo = new File("Prueba.asm"); 
        File temporal = File.createTempFile("temp", ".tmp");
        BufferedReader lector = new BufferedReader(new FileReader(archivo));
        PrintWriter escritor =  new PrintWriter(new FileWriter(temporal));
        String linea;
        int lineaActual = 0;
        while ((linea = lector.readLine()) != null) {
            escritor.println(linea);
            if(lineaActual==numLinea){
                escritor.println(texto);
            }
            lineaActual++;
        }
        lector.close();
        escritor.close();
        archivo.delete();
        temporal.renameTo(archivo);
    }
}
