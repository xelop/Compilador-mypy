package ProyectoCompi;

import Generado.Parser.Analizador;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import Generado.Scanner.*;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ParserController {
    
    private String path;
    private Consola console;
    
    public ParserController(String pPath,Consola console){ 
        path = pPath;
        this.console = console;
    }
    
    public void parsear(){
            Reader reader = null;
        try {
            reader= new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParserController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Lexer lexer = new Lexer(reader);
            Analizador parser = new Analizador(lexer,true);
        try{
            parser.parse();
            for(String error : parser.retornarLista()){
                console.impirmir(error);
            }
            console.impirmir("");
            console.impirmir("Pila Semantica:");
            console.impirmir(parser.retornarPila().imprimir());
            console.impirmir("Tabla de Simbolos:");
            console.impirmir(parser.retornarTabla().imprimir());
            console.impirmir("Errores Semánticos:");
            console.impirmir(parser.retornarTabla().imprimirErrores());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Hubo excepcion");
            console.impirmir("Error: Se llego al final del archivo. Falto un ; o : ");
            for(String error : parser.retornarLista()){
                console.impirmir(error);
            }
        }
    }
}
