package ast;

import asint.Main;

public class ExpUnaria extends Expresion {
    public Nodo operando;
    public String op;
    public ExpUnaria(Nodo e, String o, int f, int c) {
        super(f, c); this.operando = e; this.op = o;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.EXPRESION;
    }

    @Override
    public void vincular() {
        if (operando != null) {
            operando.vincular();
        }
    }

        @Override
    public void simplifica() {
        if (operando != null) operando.simplifica();
    }

    @Override
    public void chequea() {
        if (operando != null) {
            operando.chequea();
            Tipo tHijo = operando.getTipo();
            if (tHijo != null) {
                if (op.equals("!")) {
                    if (tHijo.tipoKind() == TipoKind.BOOL) {
                        this.setTipo(new TipoBool(fila(), col()));
                    } else {
                        Main.gestor.errorSemantico(this.fila(), this.col(), "'!' requiere un booleano.");
                    }
                } 
                else if (op.equals("-")) {
                    if (tHijo.tipoKind() == TipoKind.INT) {
                        this.setTipo(new TipoInt(fila(), col()));
                    } else if (tHijo.tipoKind() == TipoKind.FLOAT) {
                        this.setTipo(new TipoFloat(fila(), col()));
                    } else {
                        Main.gestor.errorSemantico(this.fila(), this.col(), "'-' requiere un número.");
                    }
                } 
                else if (op.equals("&")) {
                    this.setTipo(new TipoPuntero(tHijo, fila(), col()));
                }
            }
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpUnaria: " + op);
        System.out.println(indent + "| └── Operando:");
        operando.imprimir(indent + "| | ");
    }
}
