package errors;

import alex.UnidadLexica;

public class GestionErroresMilu {

   private boolean errorSemantico = false;

   public void errorLexico(int fila, int columna, String lexema) {
     System.out.println("ERROR fila "+fila+" columna "+columna+": Caracter inesperado: "+lexema); 
     System.exit(1);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     if (unidadLexica.lexema() != null) {
       System.out.println("ERROR fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado \""+unidadLexica.lexema()+"\"");
     } else {
       System.out.println("ERROR fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado");
     }
     System.exit(1);
   }

   public void errorSemantico(int fila, int columna, String mensaje) {
     System.out.println("ERROR SEMÁNTICO fila " + fila + " columna " + columna + ": " + mensaje); 
     errorSemantico = true; 
   }

   public boolean hayErroresSemanticos() {
     return errorSemantico;
   }

}
