package ast;

public class TipoPuntero extends Tipo {
    public Tipo tipo;

    public TipoPuntero(Tipo t, int f, int c) {
        super(f, c);
        this.tipo = t;
    }

        @Override
    public TipoKind tipoKind() {
        return TipoKind.PUNTERO;
    }

    @Override
    public void vincular() {
        if (tipo != null) {
            tipo.vincular();
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoPuntero:");
        System.out.println(indent + "| └── Tipo apuntado:");
        tipo.imprimir(indent + "| | ");
    }
}
