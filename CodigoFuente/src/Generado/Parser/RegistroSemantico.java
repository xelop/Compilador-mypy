package Generado.Parser;

public class RegistroSemantico {
    public String tipo;//IDENTIFICADOR, TIPO
    public Object valor;//es el nommbre
    public String dato; //variable o funcion
    public String ambito;//local, global
    public int linea;
    public int columna;
    
    public RegistroSemantico(String pTipo, Object pValor, String pAmbito, String pDato, int pLinea, int pColumna){
        tipo = pTipo;
        valor = pValor;
        ambito = pAmbito;
        linea = pLinea;
        columna = pColumna;
        dato = pDato;
    }
    
    @Override
    public String toString(){
        return tipo + " | valor: " + valor.toString() + " | " +  dato + " | " + ambito;
    }
}
