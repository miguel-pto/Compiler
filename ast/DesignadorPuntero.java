package ast;

import asint.Main;

public class DesignadorPuntero extends Designador {
    public Nodo puntero;

    public DesignadorPuntero(Nodo p, int f, int c) { 
        super(f, c); 
        this.puntero = p; 
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.DESIGNADOR;
    }

    @Override
    public void vincular() {
        if (puntero != null) {
            puntero.vincular();
        }
    }

    @Override
    public void simplifica() {
        if (puntero != null) puntero.simplifica();
    }

    @Override
    public void chequea() {
        puntero.chequea();
        if (puntero.getTipo() == null) {
            this.setTipo(null);
            return;
        }
        if (puntero.getTipo().tipoKind() == TipoKind.PUNTERO) {
            TipoPuntero tp = (TipoPuntero) puntero.getTipo();
            this.setTipo(tp.tipoApuntado); 
        } else {
            Main.gestor.errorSemantico(this.fila(), this.col(), "No se puede desreferenciar algo que no es un puntero.");
            this.setTipo(null);
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── DesignadorPuntero:");
        System.out.println(indent + "| └── Puntero:");
        puntero.imprimir(indent + "| | ");
    }
}
