package Generado.Parser;

public class Simbolo {
    //es para la tabla de simbolos
    String tipo;//variable, funcion
    String nombre;//x, y , caca
    String ambito;//local, global, parámetro
    String tipoDato;//string, char, list
    int linea;
    int columna;
    
    public Simbolo(String pTipo, String pNombre, String pAmbito, String pTipoDato, int pLinea, int pColumna){
        tipo = pTipo;
        nombre = pNombre;
        ambito = pAmbito;
        linea = pLinea;
        columna = pColumna;
        tipoDato = pTipoDato;
    }
    
    @Override
    public String toString(){
        return nombre + " | " + tipo + " | " + tipoDato + " | " + ambito;
    }
}
