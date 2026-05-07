package ast;

public class InstFree extends Instruccion {
    public Expresion puntero;

    public InstFree(Expresion p, int f, int c) {
        super(f, c);
        this.puntero = p;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION;
    }

    @Override
    public void vincular() {
        if (puntero != null) {
            puntero.vincular();
        }
    }

    @Override
    public void imprimir(String indent) {
        System.out.println(indent + "└── InstFree:");
        System.out.println(indent + "| └── Puntero:");
        puntero.imprimir(indent + "| | ");
    }
}
