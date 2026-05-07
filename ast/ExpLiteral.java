package ast;

public class ExpLiteral extends Expresion {
    public String valor;
    public Tipo tipo; // "int", "float", "bool"
    public ExpLiteral(String v, Tipo t, int f, int c) {
        super(f, c); this.valor = v; this.tipo = t;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.EXPRESION;
    }

    @Override
    public void vincular() {
        
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpLiteral: " + valor + " (tipo: " + tipo + ")");
    }
}
