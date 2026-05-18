package ast;

import asint.Main;

public class ExpBinaria extends Expresion {
    private Nodo izq, der;
    private String op;

    public ExpBinaria(Nodo i, Nodo d, String o, int f, int c) {
        super(f, c); this.izq = i; this.der = d; this.op = o;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.EXPRESION;
    }

    @Override
    public void vincular() {
        if (izq != null) {
            izq.vincular();
        }
        if (der != null) {
            der.vincular();
        }
    }

    @Override
    public void simplifica() {
        if (izq != null) izq.simplifica();
        if (der != null) der.simplifica();
    }

    @Override
    public void chequea() {
        if (izq != null && der != null) {
            izq.chequea();
            der.chequea();
            Tipo tIzq = izq.getTipo();
            Tipo tDer = der.getTipo();
            if (tIzq != null && tDer != null) {
                // ARITMÉTICA: +, -, *, /, %
                if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("%")) {
                    if (tIzq.tipoKind() == TipoKind.INT && tDer.tipoKind() == TipoKind.INT) {
                        this.setTipo(new TipoInt(fila(), col()));
                    } else if (tIzq.tipoKind() == TipoKind.FLOAT && tDer.tipoKind() == TipoKind.FLOAT) {
                        this.setTipo(new TipoFloat(fila(), col()));
                    } else {
                        Main.gestor.errorSemantico(this.fila(), this.col(), "Operación '" + op + "' requiere tipos numéricos iguales.");
                    }
                }
                // LÓGICA: &&, ||
                else if (op.equals("&&") || op.equals("||")) {
                    if (tIzq.tipoKind() == TipoKind.BOOL && tDer.tipoKind() == TipoKind.BOOL) {
                        this.setTipo(new TipoBool(fila(), col()));
                    } else {
                        Main.gestor.errorSemantico(this.fila(), this.col(), "Operación '" + op + "' requiere booleanos.");
                    }
                }
                // COMPARACIÓN: ==, !=, <, >, <=, >=
                else {
                    if (tIzq.equals(tDer)) {
                        this.setTipo(new TipoBool(fila(), col()));
                    } else {
                        Main.gestor.errorSemantico(this.fila(), this.col(), "Tipos no comparables con '" + op + "'.");
                    }
                }
            }
        }
    }

    @Override
    public void codeE(StringBuilder sb) {
        ((Expresion)izq).codeE(sb);
        ((Expresion)der).codeE(sb);
        String tipoWasm;
        if (izq.getTipo().tipoKind() == TipoKind.FLOAT) {
            tipoWasm = "f32";
        } else {
            tipoWasm = "i32";
        }
        switch (op) {
            case "+": sb.append("    " + tipoWasm + ".add\n"); break;
            case "-": sb.append("    " + tipoWasm + ".sub\n"); break;
            case "*": sb.append("    " + tipoWasm + ".mul\n"); break;
            case "/": 
                if (tipoWasm.equals("i32")) sb.append("    i32.div_s\n"); 
                else sb.append("    f32.div\n");
                break;
            case "%": sb.append("    i32.rem_s\n"); break;
            case "&&": sb.append("    i32.and\n"); break;
            case "||": sb.append("    i32.or\n"); break;
            case "==": sb.append("    " + tipoWasm + ".eq\n"); break;
            case "!=": sb.append("    " + tipoWasm + ".ne\n"); break;
            case "<": 
                if (tipoWasm.equals("i32")) sb.append("    i32.lt_s\n"); 
                else sb.append("    f32.lt\n");
                break;
            case ">": 
                if (tipoWasm.equals("i32")) sb.append("    i32.gt_s\n"); 
                else sb.append("    f32.gt\n");
                break;
            case "<=": 
                if (tipoWasm.equals("i32")) sb.append("    i32.le_s\n"); 
                else sb.append("    f32.le\n");
                break;
            case ">=": 
                if (tipoWasm.equals("i32")) sb.append("    i32.ge_s\n"); 
                else sb.append("    f32.ge\n");
                break;
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpBinaria: " + op);
        System.out.println(indent + "| └── Izquierda:");
        izq.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Derecha:");
        der.imprimir(indent + "| | ");
    }
}
