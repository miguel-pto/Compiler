package asint;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoMilu;
import ast.*;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;

public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 ComplexSymbolFactory sf = new ComplexSymbolFactory(); 
	 AnalizadorLexicoMilu alex = new AnalizadorLexicoMilu(input);
	 AnalizadorSintacticoMilu asint = new AnalizadorSintacticoMilu(alex, sf);
	 Symbol root = asint.parse();
	 Nodo arbol = (Nodo) root.value;
	 arbol.imprimir("");
 }
}   
   
