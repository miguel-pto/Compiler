package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TablaSimbolos {
    private HashMap<String, List<Nodo>> simbolos;

    public TablaSimbolos() {
        this.simbolos = new HashMap<>();
    }

    public void insertar(String id, Nodo nodo) {
        List<Nodo> lista = new ArrayList<>();
        lista.add(nodo);
        simbolos.put(id, lista);
    }

    public void insertarFuncion(String id, Nodo nodo) {
        List<Nodo> listaExistente = simbolos.get(id);
        if (listaExistente == null) {
            List<Nodo> nuevaLista = new ArrayList<>();
            nuevaLista.add(nodo);
            simbolos.put(id, nuevaLista);
        } 
        else {
            for (Nodo ast : listaExistente) {
                if (ast.equals(nodo)) {
                    return; 
                }
            }
            listaExistente.add(nodo);
        }
    }

    public List<Nodo> buscar(String id) {
        return simbolos.get(id);
    }

    public boolean contiene(String id) {
        return simbolos.containsKey(id);
    }
}

