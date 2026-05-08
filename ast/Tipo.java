package ast;

public abstract class Tipo extends Nodo {
    public Tipo(int f, int c) {
        super(f, c);
    }

    public abstract TipoKind tipoKind();

    public boolean equals(Tipo otro) {
        if (otro == null) return false;
        return this.tipoKind() == otro.tipoKind();
    }


    @Override
    public NodeKind nodeKind() {
        return NodeKind.TIPO;
    }
}
