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

        @Override
    public void simplifica() {
        if (designador != null) {
            designador.simplifica();
        }
    }

    @Override
    public void chequea() {
        if (designador != null) {
            designador.chequea();
            Tipo tHijo = designador.getTipo();
            if (tHijo != null) {
                this.setTipo(new TipoPuntero(tHijo, fila(), col()));
            }
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpReferencia:");
        System.out.println(indent + "| └── Designador:");
        designador.imprimir(indent + "| | ");
    }
}
