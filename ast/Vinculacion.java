package ast;

import java.util.Stack;
import java.util.List;

public class Vinculacion {
    private Stack<TablaSimbolos> pilaDeTablas;
    
    public static boolean hayErrorSemantico = false;

    public Vinculacion() {
        pilaDeTablas = new Stack<>();
        pilaDeTablas.push(new TablaSimbolos());
    }

    public void abreBloque() {
        pilaDeTablas.push(new TablaSimbolos());
    }

    public void cierraBloque() {
        if (pilaDeTablas.size() > 1) {
            pilaDeTablas.pop();
        }
    }

    public void insertaId(String id, Nodo nodo) {
        TablaSimbolos tablaActual = pilaDeTablas.peek();
        if (nodo.nodeKind() == NodeKind.FUNCION) {
            tablaActual.insertarFuncion(id, nodo);
        } else {
            if (tablaActual.contiene(id)) {
                System.err.println("Error Semántico: '" + id + "' ya declarado en este ámbito.");
                hayErrorSemantico = true;
            } else {
                tablaActual.insertar(id, nodo);
            }
        }
    }

    public Nodo buscaId(String id) {
        for (int i = pilaDeTablas.size() - 1; i >= 0; i--) {
            List<Nodo> lista = pilaDeTablas.get(i).buscar(id);
            if (lista != null && !lista.isEmpty()) {
                return lista.get(0);
            }
        }
        System.err.println("Error Semántico: Identificador '" + id + "' no declarado.");
        hayErrorSemantico = true;
        return null;
    }
}
