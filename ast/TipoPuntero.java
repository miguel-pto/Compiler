package ast;

public class TipoPuntero extends Tipo {
    public Tipo tipo;

    public TipoPuntero(Tipo t, int f, int c) {
        super(f, c);
        this.tipo = t;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoPuntero:");
        System.out.println(indent + "| └── Tipo apuntado:");
        tipo.imprimir(indent + "| | ");
    }
}
