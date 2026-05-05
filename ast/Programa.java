package ast;

import java.util.LinkedList;

public class Programa extends Nodo {
    private LinkedList<Nodo> instrucciones;

    public Programa(LinkedList<Nodo> instrucciones, int fila, int col) {
        super(fila, col);
        this.instrucciones = instrucciones;
    }

    public LinkedList<Nodo> getInstrucciones() {
        return instrucciones;
    }

    @Override
    public void imprimir(String indent) {
        System.out.println(indent + "Programa:");
        for (Nodo i : instrucciones) {
            i.imprimir(indent + "  ");
        }
    }
}
