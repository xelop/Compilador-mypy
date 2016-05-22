package ProyectoCompi;
import java.io.File;
import java.io.IOException;

public class PoyectoCompi {

    public static void main(String[] args) throws IOException{
        generarScanner();
        generarCup();
        ScannerController controllers = new ScannerController("Prueba.mypy");
        controllers.Scan();
        ParserController controller = new ParserController("Prueba.mypy");
        controller.parsear();
    }
    
    public static void generarCup(){
        String opciones[] = new String[5];
        //Seleccionamos la opción de dirección de destino
        opciones[0] = "-destdir";
        //Le damos la dirección
        opciones[1] = "src\\Generado\\Parser\\";
        //Seleccionamos la opción de nombre de archivo
        opciones[2] = "-parser";
        //Le damos el nombre que queremos que tenga
        opciones[3] = "Analizador";
        //Le decimos donde se encuentra el archivo .cup
        opciones[4] = "src\\Generado\\Parser\\parser.cup";
        try {
            java_cup.Main.main(opciones);
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public static void generarScanner(){
        jflex.Main.generate(new File("src\\Generado\\Scanner\\lexer.flex"));
    }

    
}
