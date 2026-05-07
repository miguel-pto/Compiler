package ast;

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

    public void imprimir(String indent) {
        System.out.println(indent + "└── DesignadorPuntero:");
        System.out.println(indent + "| └── Puntero:");
        puntero.imprimir(indent + "| | ");
    }
}
