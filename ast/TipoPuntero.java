package ast;

public class TipoPuntero extends Tipo {
    public Tipo tipoApuntado;

    public TipoPuntero(Tipo t, int f, int c) {
        super(f, c);
        this.tipoApuntado = t;
    }

        @Override
    public TipoKind tipoKind() {
        return TipoKind.PUNTERO;
    }

    @Override
    public void vincular() {
        if (tipoApuntado != null) {
            tipoApuntado.vincular();
        }
    }

        @Override
    public void simplifica() {
        if (tipoApuntado != null) {
            tipoApuntado.simplifica();
        }
    }

    @Override
    public void chequea() {
        if (tipoApuntado != null) {
            tipoApuntado.chequea();
        }
    }

    @Override
    public boolean equals(Tipo otro) {
        if (otro == null) return false;
        if (otro.tipoKind() != TipoKind.PUNTERO) return false;
        
        TipoPuntero otroP = (TipoPuntero) otro;
        // Dos punteros son iguales si apuntan al mismo tipo
        return this.tipoApuntado.equals(otroP.tipoApuntado);
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoPuntero:");
        System.out.println(indent + "| └── Tipo apuntado:");
        tipoApuntado.imprimir(indent + "| | ");
    }
}
