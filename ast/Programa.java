package ast;

import java.util.LinkedList;

public class Programa extends Nodo {
    private LinkedList<Nodo> instrucciones;

    public Programa(LinkedList<Nodo> instrucciones, int fila, int col) {
        super(fila, col);
        this.instrucciones = instrucciones;
        vinculador = new Vinculacion();
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.PROGRAMA;
    }

    @Override
    public void vincular() {
        vinculador.abreBloque();
        for (Nodo i : instrucciones) {
            if (i != null) {
                i.vincular(); 
            }
        }
        vinculador.cierraBloque();
    }

        @Override
    public void simplifica() {
        for (Nodo i : instrucciones) {
            i.simplifica();
        }
    }

    @Override
    public void chequea() {
        for (Nodo i : instrucciones) {
            i.chequea();
        }
    }

    @Override
    public void imprimir(String indent) {
        System.out.println(indent + "Programa:");
        for (Nodo i : instrucciones) {
            i.imprimir(indent + "  ");
        }
    }
}
