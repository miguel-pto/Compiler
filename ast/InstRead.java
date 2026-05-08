package ast;

import asint.Main;

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

    @Override
    public void simplifica() {
        if (destino != null) {
            destino.simplifica();
        }
    }

    @Override
    public void chequea() {
        if (destino != null) {
            destino.chequea();
            if (destino.getTipo() != null) {
                TipoKind kind = destino.getTipo().tipoKind();
                if (kind != TipoKind.INT && kind != TipoKind.FLOAT && kind != TipoKind.BOOL) {
                    Main.gestor.errorSemantico(this.fila(), this.col(), "No se puede leer un valor de tipo " + kind + ".");
                }
            }
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── InstRead");
        destino.imprimir(indent + "    ");
    }
}
