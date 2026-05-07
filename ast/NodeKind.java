package ast;

public enum NodeKind {
    PROGRAMA,
    DECLARACION, // Incluye variables, funciones y tipos (lo que va a la Tabla de Símbolos)
    INSTRUCCION, // If, While, Asignación... (no devuelven valor)
    EXPRESION,   // Sumas, literales... (tienen tipo y devuelven valor)
    DESIGNADOR,   // Accesos a memoria (variables, arrays, punteros)
    TIPO,
    FUNCION
}