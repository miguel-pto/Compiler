package ast;

public class ExpBinaria extends Expresion {
    public Nodo izq, der;
    public String op;
    public ExpBinaria(Nodo i, Nodo d, String o, int f, int c) {
        super(f, c); this.izq = i; this.der = d; this.op = o;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpBinaria: " + op);
        System.out.println(indent + "| └── Izquierda:");
        izq.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Derecha:");
        der.imprimir(indent + "| | ");
    }
}
