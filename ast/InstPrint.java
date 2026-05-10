package ast;

import asint.Main;

public class InstPrint extends Instruccion {
    private Nodo expresion;

    public InstPrint(Nodo e, int f, int c) {
        super(f, c);
        this.expresion = e;
    }

        @Override
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION;
    }

    @Override
    public void vincular() {
        if (expresion != null) {
            expresion.vincular();
        }
    }

        @Override
    public void simplifica() {
        if (expresion != null) {
            expresion.simplifica();
        }
    }

    @Override
    public void chequea() {
        if (expresion != null) {
            expresion.chequea();
            if (expresion.getTipo() != null) {
                TipoKind kind = expresion.getTipo().tipoKind();
                if (kind == TipoKind.ARRAY || kind == TipoKind.STRUCT || kind == TipoKind.VOID) {
                    Main.gestor.errorSemantico(this.fila(), this.col(), "No se puede imprimir un valor de tipo " + kind + ".");
                }
            }
        }
    }

    @Override
    public void codeI(StringBuilder sb) {
        if (expresion != null) {
            ((Expresion)expresion).codeE(sb);
            sb.append("    call $print\n");
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── InstPrint");
        expresion.imprimir(indent + "    ");
    }
}
