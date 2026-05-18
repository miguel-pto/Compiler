package ast;

import asint.Main;

public class ExpUnaria extends Expresion {
    private Nodo operando;
    private String op;
    
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
            }
        }
    }

    @Override
    public void codeE(StringBuilder sb) {
        if (op.equals("&")) {
            ((Designador)operando).codeD(sb);
        } else {
            ((Expresion)operando).codeE(sb);
            TipoKind k = operando.getTipo().tipoKind();

            if (op.equals("!")) {
                sb.append("    i32.eqz\n");
            } 
            else if (op.equals("-")) {
                if (k == TipoKind.FLOAT) {
                    sb.append("    f32.neg\n");
                } else {
                    sb.append("    i32.const -1\n");
                    sb.append("    i32.mul\n");
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
