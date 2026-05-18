package ast;

import asint.Main;

public class DesignadorPuntero extends Designador {
    private Nodo puntero;

    public DesignadorPuntero(Nodo p, int f, int c) { 
        super(f, c); 
        this.puntero = p; 
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.DESIGNADOR;
    }

    @Override
    public void vincular() {
        if (puntero != null) {
            puntero.vincular();
        }
    }

    @Override
    public void simplifica() {
        if (puntero != null) puntero.simplifica();
    }

    @Override
    public void chequea() {
        puntero.chequea();
        if (puntero.getTipo() == null) {
            this.setTipo(null);
            return;
        }
        if (puntero.getTipo().tipoKind() == TipoKind.PUNTERO) {
            TipoPuntero tp = (TipoPuntero) puntero.getTipo();
            this.setTipo(tp.getTipoApuntado()); 
        } else {
            Main.gestor.errorSemantico(this.fila(), this.col(), "No se puede desreferenciar algo que no es un puntero.");
            this.setTipo(null);
        }
    }

    @Override
    public void codeD(StringBuilder sb) {
        puntero.codeD(sb);
        sb.append("    i32.load\n");
    }

    @Override
    public void codeE(StringBuilder sb) {
        this.codeD(sb);
        TipoKind k = this.getTipo().tipoKind();
        if (k == TipoKind.INT || k == TipoKind.BOOL || k == TipoKind.PUNTERO) {
            sb.append("    i32.load\n");
        } else if (k == TipoKind.FLOAT) {
            sb.append("    f32.load\n");
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── DesignadorPuntero:");
        System.out.println(indent + "| └── Puntero:");
        puntero.imprimir(indent + "| | ");
    }
}
