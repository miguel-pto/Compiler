package ast;

public class DesignadorArray extends Designador {
    public Nodo designador; 
    public Nodo indice;
    
    public DesignadorArray(Nodo d, Nodo ind, int f, int c) {
        super(f, c); 
        this.designador = d; 
        this.indice = ind;
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

    public void imprimir(String indent) {
        System.out.println(indent + "└── DesignadorArray:");
        System.out.println(indent + "| └── Designador:");
        designador.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Indice:");
        indice.imprimir(indent + "| | ");
    }
}
