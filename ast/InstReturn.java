package ast;

public class InstReturn extends Instruccion {
    public Nodo valor; // Puede ser null si la función es 'void'

    public InstReturn(Nodo v, int f, int c) {
        super(f, c);
        this.valor = v;
    }

        @Override
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION;
    }

    @Override
    public void vincular() {
        if (valor != null) {
            valor.vincular();
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── InstReturn:");
        if (valor != null) {
            System.out.println(indent + "| └── Valor:");
            valor.imprimir(indent + "| | ");
        } else {
            System.out.println(indent + "| └── Sin valor (función void)");
        }
    }
}
