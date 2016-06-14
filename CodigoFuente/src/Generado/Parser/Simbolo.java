package Generado.Parser;

public class Simbolo {
    //es para la tabla de simbolos
    String tipo;//variable, funcion
    String nombre;//x, y , caca
    String ambito;//local, global, parámetro
    String tipoDato;//string, char, list
    String funcion;//si pertenece a una funcion
    int linea;
    int columna;
    
    public Simbolo(String pTipo, String pNombre, String pAmbito, String pTipoDato, String pFuncion, int pLinea, int pColumna){
        tipo = pTipo;
        nombre = pNombre;
        ambito = pAmbito;
        linea = pLinea;
        columna = pColumna;
        tipoDato = pTipoDato;
        funcion = pFuncion;
        
    }
    
    @Override
    public String toString(){
        return tipo + " | " + nombre + " | " + tipoDato + " | " + ambito + " | " + funcion;
    }
}
