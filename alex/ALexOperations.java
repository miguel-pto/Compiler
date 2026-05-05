package alex;

import asint.ClaseLexica;

public class ALexOperations {
  private AnalizadorLexicoMilu alex; 

  public ALexOperations(AnalizadorLexicoMilu alex) {
     this.alex = alex;   
  }

  // Identificadores y Números (con valor)
  public UnidadLexica unidadId() { 
     return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IDENTIFICADOR, alex.lexema()); 
  } 
  public UnidadLexica unidadIntNum() { 
     return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INTNUM, alex.lexema()); 
  } 
  public UnidadLexica unidadFloatNum() { 
     return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FLOATNUM, alex.lexema()); 
  } 

  // Palabras reservadas
  public UnidadLexica unidadVar() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.VAR); }
  public UnidadLexica unidadDef() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DEF); }
  public UnidadLexica unidadIf() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IF); }
  public UnidadLexica unidadElse() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ELSE); }
  public UnidadLexica unidadWhile() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WHILE); }
  public UnidadLexica unidadFor() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FOR); }
  public UnidadLexica unidadReturn() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RETURN); }
  public UnidadLexica unidadAs() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AS); }
  public UnidadLexica unidadStruct() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRUCT); }
  public UnidadLexica unidadNew() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NEW); }
  public UnidadLexica unidadDe() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DE); }
  public UnidadLexica unidadFree() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FREE); }
  
  // Tipos y valores constantes
  public UnidadLexica unidadInt() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INT); }
  public UnidadLexica unidadBool() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BOOL); }
  public UnidadLexica unidadFloat() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FLOAT); }
  public UnidadLexica unidadVoid() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.VOID); }
  public UnidadLexica unidadArray() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ARRAY); }
  public UnidadLexica unidadTrue() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRUE); }
  public UnidadLexica unidadFalse() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSE); }

  // Operadores Aritméticos y Lógicos
  public UnidadLexica unidadSuma() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SUMA); }
  public UnidadLexica unidadResta() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RESTA); }
  public UnidadLexica unidadMult() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MULT); }
  public UnidadLexica unidadDiv() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIV); }
  public UnidadLexica unidadMod() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MOD); }
  public UnidadLexica unidadNeg() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NEG); }
  public UnidadLexica unidadAnd() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AND); }
  public UnidadLexica unidadOr() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OR); }
  public UnidadLexica unidadRef() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REF); }

  // Comparaciones
  public UnidadLexica unidadIgual() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL); }
  public UnidadLexica unidadDistinto() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DISTINTO); }
  public UnidadLexica unidadMenor() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR); }
  public UnidadLexica unidadMayor() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR); }
  public UnidadLexica unidadMenorIgual() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL); }
  public UnidadLexica unidadMayorIgual() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL); }

  // Puntuación, Asignación y Acceso
  public UnidadLexica unidadAsig() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ASIG); }
  public UnidadLexica unidadPAp() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAP); }
  public UnidadLexica unidadPCierre() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PCIERRE); }
  public UnidadLexica unidadCAp() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CAP); }
  public UnidadLexica unidadCCierre() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CCIERRE); }
  public UnidadLexica unidadBAp() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BAP); }
  public UnidadLexica unidadBCierre() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BCIERRE); }
  public UnidadLexica unidadComa() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMMA); }
  public UnidadLexica unidadColon() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COLON); }
  public UnidadLexica unidadSColon() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SCOLON); }
  public UnidadLexica unidadPunto() { return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO); }

  // Fin de fichero
  public UnidadLexica unidadEof() { 
     return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF); 
  }
}
