package ast;

public class InstReturn extends Instruccion {
    private Nodo valor;

    public InstReturn(Nodo v, int f, int c) {
        super(f, c);
        this.valor = v;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION;
    }

    @Override
    public void vincular() {
        if (valor != null) {
            valor.vincular();
        }
    }

    @Override
    public void chequea() {
        if (valor != null) {
            valor.chequea();
            this.setTipo(valor.getTipo());
        } else {
            this.setTipo(new TipoVoid(fila(), col()));
        }
    }

    @Override
    public void codeI(StringBuilder sb) {
        if (valor != null) {
            ((Expresion)valor).codeE(sb);
        }
        sb.append("    global.get $MP\n");
        sb.append("    global.set $SP\n");
        sb.append("    global.get $MP\n");
        sb.append("    i32.load\n");
        sb.append("    global.set $MP\n");
        sb.append("    return\n");
    }


    public void imprimir(String indent) {
        System.out.println(indent + "└── InstReturn:");
        if (valor != null) {
            System.out.println(indent + "| └── Valor:");
            valor.imprimir(indent + "| | ");
        } else {
            System.out.println(indent + "| └── Sin valor (función void)");
        }
    }
}
