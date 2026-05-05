package alex;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class UnidadLexica extends ComplexSymbol {

    public UnidadLexica(int fila, int columna, int clase, String lexema) {
        super(lexema, clase, 
              new Location(fila, columna), 
              new Location(fila, columna + lexema.length()), 
              lexema); 
    }

    public UnidadLexica(int fila, int columna, int clase) {
        super("", clase, 
              new Location(fila, columna), 
              new Location(fila, columna + 1), 
              null);
    }
    
    public int clase() { return sym; }
    public String lexema() { return (String) value; }    
    public int fila() { return xleft.getLine(); }
    public int columna() { return xleft.getColumn(); }
}
