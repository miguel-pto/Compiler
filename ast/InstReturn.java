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

    @Override
    public void simplifica() {
        if (valor != null) valor.simplifica();
    }

    @Override
    public void chequea() {
        if (valor != null) {
            valor.chequea();
            this.setTipo(valor.getTipo());
        } else {
            this.setTipo(new TipoVoid(fila(), col()));
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
