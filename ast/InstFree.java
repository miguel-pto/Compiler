package ast;

import asint.Main;

public class InstFree extends Instruccion {
    private Expresion puntero;

    public InstFree(Expresion p, int f, int c) {
        super(f, c);
        this.puntero = p;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION;
    }

    @Override
    public void vincular() {
        if (puntero != null) {
            puntero.vincular();
        }
    }

        @Override
    public void simplifica() {
        if (puntero != null) {
            puntero.simplifica();
        }
    }

    @Override
    public void chequea() {
        if (puntero != null) {
            puntero.chequea();
            if (puntero.getTipo() != null) {
                if (puntero.getTipo().tipoKind() != TipoKind.PUNTERO) {
                    Main.gestor.errorSemantico(this.fila(), this.col(), "La instrucción 'free' requiere un puntero, pero recibió " + puntero.getTipo().tipoKind() + ".");
                }
            }
        }
    }

    @Override
    public void codeI(StringBuilder sb) {
        if (puntero != null) {
            puntero.codeE(sb);
            sb.append("    drop\n");
        }
    }


    @Override
    public void imprimir(String indent) {
        System.out.println(indent + "└── InstFree:");
        System.out.println(indent + "| └── Puntero:");
        puntero.imprimir(indent + "| | ");
    }
}
