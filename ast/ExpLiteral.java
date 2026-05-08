package ast;

public class ExpLiteral extends Expresion {
    public String valor;
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

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpLiteral: " + valor + " (tipo: " + tipo + ")");
    }
}
