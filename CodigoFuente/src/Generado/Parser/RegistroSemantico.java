package Generado.Parser;

public class RegistroSemantico {
    public String tipo;//en lugar de hacer toda una jerarquía... me da pereza
    public Object valor;
    public String ambito;
    
    public RegistroSemantico(String pTipo, Object pValor, String pAmbito){
        tipo = pTipo;
        valor = pValor;
        ambito = pAmbito;
    }
    
    @Override
    public String toString(){
        return tipo + " | valor: " + valor.toString() + " | " + ambito;
    }
}
