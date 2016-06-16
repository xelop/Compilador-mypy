package Generado.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class GeneradorCodigo {
    
    public PrintWriter writer;
    public GeneradorCodigo() throws FileNotFoundException, UnsupportedEncodingException{
        writer = new PrintWriter("Prueba.asm", "UTF-8");
        writer.println("pila segment stack 'stack'");
        writer.println("    dw 256 dup (?)");
        writer.println("pila ends");
        writer.println("");
        writer.println("datos segment");
        writer.println("");
        writer.println("datos ends");
        writer.println("");
        writer.println("codigo segment");
        writer.println("    assume cs:codigo, ds:datos, ss:pila");
        writer.println("");
        writer.println("inicio");
        writer.println("    mov ax, datos");
        writer.println("    mov ds, ax");
        finalizarCodigo();
        
    }
    
    public void finalizarCodigo(){
        writer.println("");
        writer.println("    mov ax, 4c00h");
        writer.println("    int 21h");
        writer.println("codigo ends");
        writer.println("end inicio");
        writer.close();
    }
    
    public void insertarVariableGlobal(String nombre, String tipo){
        try{
            if (tipo.equals("int")){
                insertaN("    " + nombre + " dw 0    ; es un entero", 6);
            }
            if (tipo.equals("float")){
                insertaN("    " + nombre + " dq 0.0 ; es un float", 6);
            }
            if (tipo.equals("boolean")){
                insertaN("    " + nombre + " db 0    ; es un booleano", 6);
            }
            if (tipo.equals("string")){
                insertaN("    " + nombre + " db \"\"   ; es un string", 6);
            }
            if (tipo.equals("char")){
                insertaN("    " + nombre + " db ''     ; es un char", 6);
            }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    private void insertaN(String texto, int numLinea) throws IOException{
        File archivo = new File("Prueba.asm"); 
        File temporal = File.createTempFile("temp", ".tmp");
        BufferedReader lector = new BufferedReader(new FileReader( archivo ));
        PrintWriter escritor =  new PrintWriter(new FileWriter( temporal ));
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
