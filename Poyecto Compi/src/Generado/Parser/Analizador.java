
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20150930 (SVN rev 66)
//----------------------------------------------------

package Generado.Parser;

import java_cup.runtime.*;
import java.util.ArrayList;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20150930 (SVN rev 66) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Analizador extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public Analizador() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Analizador(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Analizador(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\034\000\002\002\004\000\002\002\003\000\002\002" +
    "\003\000\002\005\003\000\002\010\004\000\002\007\004" +
    "\000\002\007\002\000\002\006\010\000\002\011\004\000" +
    "\002\011\002\000\002\012\005\000\002\012\002\000\002" +
    "\004\003\000\002\003\004\000\002\013\004\000\002\013" +
    "\002\000\002\022\004\000\002\022\004\000\002\016\004" +
    "\000\002\014\003\000\002\017\003\000\002\020\003\000" +
    "\002\015\006\000\002\015\004\000\002\023\003\000\002" +
    "\024\003\000\002\025\003\000\002\026\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\051\000\010\003\011\023\016\024\004\001\002\000" +
    "\004\004\042\001\002\000\004\002\uffff\001\002\000\010" +
    "\003\ufff2\022\ufff2\026\ufff2\001\002\000\004\002\000\001" +
    "\002\000\004\002\uffee\001\002\000\010\003\uffe9\022\uffe9" +
    "\026\uffe9\001\002\000\004\002\uffed\001\002\000\004\002" +
    "\040\001\002\000\004\002\ufffe\001\002\000\004\002\uffec" +
    "\001\002\000\004\004\023\001\002\000\006\002\ufffb\024" +
    "\004\001\002\000\006\002\ufffb\024\004\001\002\000\004" +
    "\002\ufffd\001\002\000\004\002\ufffc\001\002\000\004\025" +
    "\024\001\002\000\010\003\ufff2\022\ufff2\026\ufff2\001\002" +
    "\000\004\002\uffeb\001\002\000\010\003\027\022\030\026" +
    "\034\001\002\000\006\002\uffe7\022\uffe7\001\002\000\004" +
    "\004\037\001\002\000\012\002\ufff3\003\ufff3\022\ufff3\026" +
    "\ufff3\001\002\000\006\002\ufff2\022\ufff2\001\002\000\004" +
    "\002\uffef\001\002\000\006\002\ufff2\022\ufff2\001\002\000" +
    "\006\002\ufff1\022\030\001\002\000\006\002\ufff0\022\030" +
    "\001\002\000\016\002\ufff4\003\ufff4\022\ufff4\026\ufff4\030" +
    "\ufff4\031\ufff4\001\002\000\004\002\001\001\002\000\004" +
    "\002\uffea\001\002\000\004\027\043\001\002\000\006\022" +
    "\030\030\ufff8\001\002\000\006\030\ufff6\031\050\001\002" +
    "\000\004\030\046\001\002\000\004\025\047\001\002\000" +
    "\006\002\ufffa\024\ufffa\001\002\000\004\022\030\001\002" +
    "\000\004\030\ufff9\001\002\000\006\030\ufff6\031\050\001" +
    "\002\000\004\030\ufff7\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\051\000\024\002\012\005\006\006\016\010\013\014" +
    "\004\015\014\017\007\020\011\023\005\001\001\000\002" +
    "\001\001\000\002\001\001\000\006\013\025\016\040\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\006\006\017\007\020" +
    "\001\001\000\006\006\017\007\021\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\006\013\025" +
    "\016\024\001\001\000\002\001\001\000\010\003\030\022" +
    "\032\025\031\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\013\035\001\001\000\002\001" +
    "\001\000\004\013\034\001\001\000\004\003\030\001\001" +
    "\000\004\003\030\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\006\003\043" +
    "\011\044\001\001\000\004\012\050\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\004\003\051" +
    "\001\001\000\002\001\001\000\004\012\052\001\001\000" +
    "\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Analizador$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Analizador$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Analizador$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



  public boolean syntaxErrors;

  ArrayList<String> errores;

  public Analizador(java_cup.runtime.Scanner s, boolean type) {
    super(s);
    errores = new ArrayList<String>();
  }
  
  public void addError(String error){
    errores.add(error);
    /*ProyectoCompi.PoyectoCompi.consola.impirmir(error);*/
  }

  public ArrayList<String> retornarLista(){
    return errores;
  }
  public void syntax_error(Symbol s){
    System.out.println("Error en la linea : " + s.left);
  }
/*public void unrecovered_syntax_error(Symbol s){
    System.out.println("Error en la linea : " + s.left);
  }*/



/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Analizador$actions {



  private final Analizador parser;

  /** Constructor */
  CUP$Analizador$actions(Analizador parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Analizador$do_action_part00000000(
    int                        CUP$Analizador$act_num,
    java_cup.runtime.lr_parser CUP$Analizador$parser,
    java.util.Stack            CUP$Analizador$stack,
    int                        CUP$Analizador$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Analizador$result;

      /* select the action based on the action number */
      switch (CUP$Analizador$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= Programa EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)).value;
		RESULT = start_val;
              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Analizador$parser.done_parsing();
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // Programa ::= Funcional 
            {
              Object RESULT =null;
		 ProyectoCompi.PoyectoCompi.consola.impirmir("Parseo realizado exitosamente. De tipo Funcional."); 
              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Programa",0, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // Programa ::= OOP 
            {
              Object RESULT =null;
		 ProyectoCompi.PoyectoCompi.consola.impirmir("Parseo realizado exitosamente. De tipo OOP."); 
              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Programa",0, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // Funcional ::= FuncionesFact 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Funcional",3, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // FuncionesFact ::= Funcion Funciones 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("FuncionesFact",6, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // Funciones ::= Funcion Funciones 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Funciones",5, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // Funciones ::= 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Funciones",5, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // Funcion ::= def identificador parenAbierto ParametrosFact parenCerrado dosPuntos 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Funcion",4, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-5)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // ParametrosFact ::= Variable Parametros 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("ParametrosFact",7, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // ParametrosFact ::= 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("ParametrosFact",7, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // Parametros ::= coma Variable Parametros 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Parametros",8, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-2)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // Parametros ::= 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Parametros",8, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // CodigoPrincipal ::= identificador 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("CodigoPrincipal",2, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // Variable ::= var identificador 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Variable",1, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // Variables ::= Variables Variable 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Variables",9, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // Variables ::= 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Variables",9, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // PuntoComa ::= puntoComa Variables 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("PuntoComa",16, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // PuntoComa ::= errPuntoYComa Variables 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("PuntoComa",16, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // VariablesOOP ::= Variables PuntoComa 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("VariablesOOP",12, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // OOP ::= Clase 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("OOP",10, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // Clase ::= BloqueOOP 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Clase",13, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // BloqueOOP ::= HeaderOOP 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("BloqueOOP",14, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // HeaderOOP ::= clas identificador dosPuntos VariablesOOP 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("HeaderOOP",11, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-3)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // HeaderOOP ::= errHeaderOOP VariablesOOP 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("HeaderOOP",11, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // errHeaderOOP ::= error 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$Analizador$stack.peek()).value;
		 parser.addError("Hay un error en el header del fuente de tipo OOP. Línea: "  + eleft+ ". Columna: " + eright); 
              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("errHeaderOOP",17, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // errDecVariables ::= error 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$Analizador$stack.peek()).value;
		 parser.addError("Hay un error al declarar variables. Línea: "  + eleft+ ". Columna: " + eright); 
              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("errDecVariables",18, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // errPuntoYComa ::= error 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$Analizador$stack.peek()).value;
		 parser.addError("Falto cerrar un bloque con ';'. Línea: "  + eleft+ ". Columna: " + eright); 
              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("errPuntoYComa",19, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // errClase ::= error 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$Analizador$stack.peek()).value;
		 parser.addError("Hay un error en la clase. Línea: "  + eleft+ ". Columna: " + eright); 
              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("errClase",20, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Analizador$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Analizador$do_action(
    int                        CUP$Analizador$act_num,
    java_cup.runtime.lr_parser CUP$Analizador$parser,
    java.util.Stack            CUP$Analizador$stack,
    int                        CUP$Analizador$top)
    throws java.lang.Exception
    {
              return CUP$Analizador$do_action_part00000000(
                               CUP$Analizador$act_num,
                               CUP$Analizador$parser,
                               CUP$Analizador$stack,
                               CUP$Analizador$top);
    }
}

}
