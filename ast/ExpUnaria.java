package ast;

public class ExpUnaria extends Nodo {
    public Nodo operando;
    public String op;
    public ExpUnaria(Nodo e, String o, int f, int c) {
        super(f, c); this.operando = e; this.op = o;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpUnaria: " + op);
        System.out.println(indent + "| └── Operando:");
        operando.imprimir(indent + "| | ");
    }
}
