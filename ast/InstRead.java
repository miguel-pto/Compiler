package ast;

public class InstRead extends Instruccion {
    public Designador destino;

    public InstRead(Designador d, int f, int c) {
        super(f, c);
        this.destino = d;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION;
    }

    @Override
    public void vincular() {
        if (destino != null) {
            destino.vincular();
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── InstRead");
        destino.imprimir(indent + "    ");
    }
}
