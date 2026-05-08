package ast;

import asint.Main;

public class DesignadorArray extends Designador {
    public Designador designador; 
    public Expresion indice;
    
    public DesignadorArray(Nodo d, Nodo ind, int f, int c) {
        super(f, c); 
        this.designador = (Designador) d; 
        this.indice = (Expresion) ind;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.DESIGNADOR;
    }

    @Override
    public void vincular() {
        if (designador != null) {
            designador.vincular();
        }
        if (indice != null) {
            indice.vincular();
        }
    }

        @Override
    public void simplifica() {
        if (designador != null) designador.simplifica();
        if (indice != null) indice.simplifica();
    }

    @Override
    public void chequea() {
        designador.chequea();
        indice.chequea();

        if (designador.getTipo() == null || indice.getTipo() == null) {
            setTipo(null);
            return;
        }
        if (designador.getTipo().tipoKind() != TipoKind.ARRAY) {
            Main.gestor.errorSemantico(this.fila(), this.col(), "El designador no es de tipo array.");
            setTipo(null);
        } 
        else if (indice.getTipo().tipoKind() != TipoKind.INT) {
            Main.gestor.errorSemantico(this.fila(), this.col(), "El índice debe ser de tipo entero.");
            setTipo(null);
        } 
        else {
            TipoArray ta = (TipoArray) designador.getTipo();
            setTipo(ta.tipo);
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── DesignadorArray:");
        System.out.println(indent + "| └── Designador:");
        designador.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Indice:");
        indice.imprimir(indent + "| | ");
    }
}
