package ProyectoCompi;

import Generado.Parser.Analizador;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import Generado.Scanner.*;


public class ParserController {
    
    private String path;
    private Consola console;
    
    public ParserController(String pPath,Consola console){ 
        path = pPath;
        this.console = console;
    }
    
    public void parsear(){
        try{
            Reader reader = new BufferedReader(new FileReader(path));
            Lexer lexer = new Lexer(reader);
            Analizador parser = new Analizador(lexer,true);
            parser.parse();
            for(String error : parser.retornarLista()){
                console.impirmir(error);
            }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Hubo excepcion");
        }
    }
}
