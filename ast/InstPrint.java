package ast;

public class InstPrint extends Instruccion {
    public Nodo expresion;

    public InstPrint(Nodo e, int f, int c) {
        super(f, c);
        this.expresion = e;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── InstPrint");
        expresion.imprimir(indent + "    ");
    }
}
