package ast;

public abstract class Tipo extends Nodo {
    public Tipo(int f, int c) {
        super(f, c);
    }

    public abstract TipoKind tipoKind();

    @Override
    public NodeKind nodeKind() {
        return NodeKind.TIPO;
    }
}
