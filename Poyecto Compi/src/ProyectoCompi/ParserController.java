package ProyectoCompi;

import Generado.Parser.Analizador;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import Generado.Scanner.*;


public class ParserController {
    
    private String path;
    
    public ParserController(String pPath){ 
        path = pPath;
    }
    
    public void parsear(){
        try{
            Reader reader = new BufferedReader(new FileReader(path));
            Lexer lexer = new Lexer(reader);
            Analizador parser = new Analizador(lexer);
            parser.parse();
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Hubo excepcion");
        }
    }
}
