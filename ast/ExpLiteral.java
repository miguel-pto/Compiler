package ast;

public class ExpLiteral extends Expresion {
    private String valor;
    public ExpLiteral(String v, Tipo t, int f, int c) {
        super(f, c); this.valor = v; setTipo(t);
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.EXPRESION;
    }

    @Override
    public void vincular() {
        
    }

    @Override
    public void simplifica() {
        
    }

    @Override
    public void chequea() {
        if (tipo != null) {
            tipo.chequea();
        }
    }

    public String getValor(){
        return this.valor;
    }

    @Override
    public void codeE(StringBuilder sb) {
        TipoKind k = tipo.tipoKind();
        if (k == TipoKind.INT) {
            sb.append("    i32.const " + valor + "\n");
        } else if (k == TipoKind.FLOAT) {
            sb.append("    f32.const " + valor + "\n");
        } else if (k == TipoKind.BOOL) {
            if (valor.equals("true")) {
                sb.append("    i32.const 1\n");
            } else {
                sb.append("    i32.const 0\n");
            }
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpLiteral: " + valor + " (tipo: " + tipo + ")");
    }
}
