package ast;

public class ExpReferencia extends Nodo {
    public Nodo designador; 

    public ExpReferencia(Nodo d, int f, int c) {
        super(f, c);
        this.designador = d;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.EXPRESION;
    }

    @Override
    public void vincular() {
        if (designador != null) {
            designador.vincular();
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpReferencia:");
        System.out.println(indent + "| └── Designador:");
        designador.imprimir(indent + "| | ");
    }
}
