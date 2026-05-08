package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TablaSimbolos {
    private HashMap<String, List<Nodo>> simbolos;

    public TablaSimbolos() {
        this.simbolos = new HashMap<>();
    }

    // Método para variables y otros (Identificación única)
    // Crea una lista nueva y machaca lo anterior en este nivel
    public void insertar(String id, Nodo nodo) {
        List<Nodo> lista = new ArrayList<>();
        lista.add(nodo);
        simbolos.put(id, lista);
    }

    // Método para funciones
    // Añade el nodo a la lista existente o crea una nueva si no había
    public void insertarFuncion(String id, Nodo nodo) {
    List<Nodo> listaExistente = simbolos.get(id);

    if (listaExistente == null) {
        // Creamos la lista, añadimos el primer nodo y la guardamos en el mapa
        List<Nodo> nuevaLista = new ArrayList<>();
        nuevaLista.add(nodo);
        simbolos.put(id, nuevaLista);
    } 
    else {
        // Recorremos para evitar meter exactamente el mismo nodo
        for (Nodo ast : listaExistente) {
            if (ast.equals(nodo)) {
                return; // Es un duplicado exacto, no hacemos nada
            }
        }
        // Si no es duplicado, lo añadimos a la lista que ya estaba en el mapa
        listaExistente.add(nodo);
    }
}

    public List<Nodo> buscar(String id) {
        return simbolos.get(id); // Devuelve la lista completa de definiciones
    }

    public boolean contiene(String id) {
        return simbolos.containsKey(id);
    }
}

