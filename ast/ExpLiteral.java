package ast;

public class ExpLiteral extends Expresion {
    public String valor;
    public String tipo; // "int", "float", "bool"
    public ExpLiteral(String v, String t, int f, int c) {
        super(f, c); this.valor = v; this.tipo = t;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpLiteral: " + valor + " (tipo: " + tipo + ")");
    }
}
