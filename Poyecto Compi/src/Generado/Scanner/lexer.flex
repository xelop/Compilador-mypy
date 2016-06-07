package Generado.Scanner;
import static Generado.Scanner.Token.*;
import Generado.Parser.sym;
import java_cup.runtime.*;
%%

%public
%class Lexer
%type Token
%line
%column
%implements java_cup.runtime.Scanner
%function next_token
%type java_cup.runtime.Symbol
%eofval{
  return new java_cup.runtime.Symbol(sym.EOF);
%eofval}
%eofclose
%state MYSTRING
%state MYCHAR
%state COMENTARIOBLOQUE
%state COMENTARIOBLOQUE2

%{
    //esto se copia directamente

    StringBuffer string = new StringBuffer();
    Boolean cambioLinea = false;

    public String lexeme;
    public int getLine(){
        return yyline;
    }

    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}

Letra = [a-zA-Z_]
Numero = [0-9]
Binario = (0|1)
Octal = [0-7]
Operador = [+,-] /*No se si tener todos los valores de operador servira para arreglar el ER de identificador */
Hexadecimal = ([0-9]|[a-f])
WhiteSpace = {LineTerminator} | [ ] | "\t"
LineTerminator = (\r\n|\r|\n)
InputCharacter = [^\r\n] /* todos los caracteres que no son el enter */
identificadorInvalido = ([^\x00-\x7F])

Comentario = {ComentarioDeLinea}
ComentarioDeLinea = "#" {InputCharacter}* {LineTerminator}?
ComentarioDeBloque = \"\"\"([\s\S]*)\"\"\"

var = ("int"|"float"|"string"|"boolean"|"char"|"list")
palabraRerservada = ("assert"|"del"|"except"|"exec"|"from"|"global"|"import"|"is"|"lambda"|"pass"|"raise"|"return"|"None")

opAritmeticos = "+"|"-"|"*"|"/"|"//"|"%"|"**"
opComparadores = "=="|"!="|"<>"|">"|"<"|">="|"<="
opLogicos = "and"|"or"|"not"
opBits = ">>"|"<<"|"&"|"^"|"~"|\u007C
opAsignaciones = "+="|"-="|"*="|"/="|"**="|"//="|"="
opDelimitadores ="."|":"|"{"|"}" /* ojo que ya no se va a usar el TAB*/
opPuntoComa = ";"
%%
/* Comentarios y espacios en blanco son ignorados */


<YYINITIAL> {
    \"\"\"                         { string.setLength(0); yybegin(COMENTARIOBLOQUE);}
    \'\'\'                         { string.setLength(0); yybegin(COMENTARIOBLOQUE2);}
    \"                             { string.setLength(0); cambioLinea = false; yybegin(MYSTRING); }
    {WhiteSpace}                   {/* ignore */}
    {Comentario}                   {/* ignore */}
    0("b"|"B")                     {Binario}+ {lexeme=yytext(); return symbol(sym.INT, lexeme);}
    0("o"|"O")                     {Octal}+  {lexeme=yytext(); return symbol(sym.INT, lexeme);}
    0("x"|"X")                     {Hexadecimal}+  {lexeme=yytext(); return symbol(sym.INT, lexeme);}
    {Numero}+({Letra}+{Numero}*)+ {lexeme = yytext(); return symbol(sym.INT, lexeme);}
    {Numero}+ {lexeme=yytext(); return symbol(sym.INT, lexeme);}
    ({Numero}+"."{Numero}+) {lexeme=yytext(); return symbol(sym.FLOAT, lexeme);}

    \' {string.setLength(0); yybegin(MYCHAR);} 


   /* Delimitadores */
   ":"                 {lexeme = yytext(); return symbol(sym.dosPuntos, lexeme);}
   ";"                 {lexeme = yytext(); return symbol(sym.puntoComa, lexeme);}
   "("                 {lexeme = yytext(); return symbol(sym.parenAbierto, lexeme);}
   ")"                 {lexeme = yytext(); return symbol(sym.parenCerrado, lexeme);}
   "["                 {lexeme = yytext(); return symbol(sym.cuadradoAbierto, lexeme);}
   "]"                 {lexeme = yytext(); return symbol(sym.cuadradoCerrado, lexeme);}
   ","                 {lexeme = yytext(); return symbol(sym.coma, lexeme);}
   "."                 {lexeme = yytext(); return symbol(sym.punto, lexeme);} 

   /* Operadores aritmeticos */
   "+"                {lexeme = yytext(); return symbol(sym.opSuma, lexeme);}
   "-"                {lexeme = yytext(); return symbol(sym.opResta, lexeme);}
   "*"                {lexeme = yytext(); return symbol(sym.opMultiplicacion, lexeme);}
   "//"               {lexeme = yytext(); return symbol(sym.opDivisionE, lexeme);}
   "/"                {lexeme = yytext(); return symbol(sym.opDivision, lexeme);}
   "%"                {lexeme = yytext(); return symbol(sym.opModulo, lexeme);}
   "**"               {lexeme = yytext(); return symbol(sym.opPotencia, lexeme);}

   /* Operadores Logicos */ 
   "and"              {lexeme = yytext(); return symbol(sym.opAnd, lexeme);}
   "or"               {lexeme = yytext(); return symbol(sym.opOr, lexeme);}
   "not"              {lexeme = yytext(); return symbol(sym.opNot, lexeme);}

   /* Operadores */
   {opAritmeticos}     {lexeme = yytext(); return symbol(sym.opAritmeticos, lexeme);}
   {opComparadores}    {lexeme = yytext(); return symbol(sym.opComparadores, lexeme);}
   {opLogicos}         {lexeme = yytext(); return symbol(sym.opLogicos, lexeme);}
   {opBits}            {lexeme = yytext(); return symbol(sym.opBits, lexeme);}
   {opAsignaciones}    {lexeme = yytext(); return symbol(sym.opAsignaciones, lexeme);}
   {opDelimitadores}   {lexeme = yytext(); return symbol(sym.opDelimitadores, lexeme);}

   /* Palabras reservadas */
   {var}               {lexeme = yytext(); return symbol(sym.var, lexeme);}
   "class"             {lexeme = yytext(); return symbol(sym.clas, lexeme);}
   
   "def"               {lexeme = yytext(); return symbol(sym.def, lexeme);}
   "False"             {lexeme = yytext(); return symbol(sym.ffalse, lexeme);}
   "True"              {lexeme = yytext(); return symbol(sym.ttrue, lexeme);}
   "if"                {lexeme = yytext(); return symbol(sym.iif, lexeme);}
   "else"              {lexeme = yytext(); return symbol(sym.eelse, lexeme);}
   "elif"              {lexeme = yytext(); return symbol(sym.elif, lexeme);}
   "for"               {lexeme = yytext(); return symbol(sym.ffor, lexeme);}
   "in"                {lexeme = yytext(); return symbol(sym.in, lexeme);}
   "range"             {lexeme = yytext(); return symbol(sym.range, lexeme);}
   "while"             {lexeme = yytext(); return symbol(sym.wwhile, lexeme);}
   "print"             {lexeme = yytext(); return symbol(sym.pprint, lexeme);}
   "input"             {lexeme = yytext(); return symbol(sym.iinput, lexeme);}
   "try"               {lexeme = yytext(); return symbol(sym.ttry, lexeme);}
   "except"            {lexeme = yytext(); return symbol(sym.eexcept, lexeme);}
   "finally"           {lexeme = yytext(); return symbol(sym.ffinally, lexeme);}
  "break"              {lexeme = yytext(); return symbol(sym.bbreak, lexeme);} 
  "continue"           {lexeme = yytext(); return symbol(sym.ccontinue, lexeme);}      

   {palabraRerservada} {lexeme = yytext(); return symbol(sym.palabraReservada, lexeme);}
   {Letra}(({Letra}|{Numero})*({identificadorInvalido})+({Letra}|{Numero})*)+ {lexeme=yytext(); return symbol(sym.ERROR, lexeme);} 
   {Letra}({Letra}|{Numero})* {lexeme=yytext(); return symbol(sym.identificador, lexeme);}

}

<MYCHAR> {

    \'                              {yybegin(YYINITIAL); lexeme = "'"+ string.toString()+"'"; 
                                     if(string.length()>1)
                                        return symbol(sym.ERROR, lexeme);
                                     else
                                        return symbol(sym.CHAR, lexeme);   }
    <<EOF>>                          { yybegin(YYINITIAL); lexeme = "Char sin terminar: " + string.toString(); return symbol(sym.ERROR);}
    \S                               { string.append( yytext() );}
    \s                               { string.append( yytext() );}


}
<MYSTRING> {
    {LineTerminator}               { cambioLinea = true; string.append('\n');}
    \"                             { yybegin(YYINITIAL);
                                     lexeme = "\"" +string.toString()+"\"";
                                     if(cambioLinea){
                                          return symbol(sym.ERROR, lexeme);
                                     }else{
                                          return symbol(sym.STRING, lexeme);
                                     }} /*Numero linea = adonde terminO*/
   <<EOF>>                         { yybegin(YYINITIAL); lexeme = "String sin terminar: " + string.toString(); return symbol(sym.ERROR);}
    \t                             { string.append('\t'); } 
    \u0020                         {string.append(' ');}
    \\t                            { string.append('\t'); }
    \\n                            { string.append('\n'); }
    \\r                            { string.append('\r'); }
    \\\"                           { string.append('\"'); }
    \S                             { string.append( yytext() ); }

}

<COMENTARIOBLOQUE> {
    \"\"\"                           { yybegin(YYINITIAL);}
    \S                               { string.append( yytext() ); }
    <<EOF>>                          { yybegin(YYINITIAL); lexeme = "Comentario de bloque sin terminar: " + "\"\"\"" + string.toString(); return symbol(sym.ERROR, lexeme);}
    \\t                              { string.append('\t'); }
    \\n                              { string.append('\n'); }
    \\r                              { string.append('\r'); }
    \\\"                             { string.append('\"'); }
    [ ]                              { string.append(' '); }
    {LineTerminator}                 { string.append(yytext()); }
    \s                               { string.append(yytext()); }
}
<COMENTARIOBLOQUE2> {
    \'\'\'                           { yybegin(YYINITIAL);}
    \S                               { string.append( yytext() ); }
    <<EOF>>                          { yybegin(YYINITIAL); lexeme = "Comentario de bloque sin terminar: " + "\'\'\'" + string.toString(); return symbol(sym.ERROR, lexeme);}
    \\t                              { string.append('\t'); }
    \\n                              { string.append('\n'); }
    \\r                              { string.append('\r'); }
    \\\"                             { string.append('\"'); }
    [ ]                              { string.append(' '); }
    {LineTerminator}                 { string.append(yytext()); }
    \s                               { string.append(yytext()); }
}


/* Error */
. {lexeme = yytext();return symbol(sym.ERROR, lexeme);}