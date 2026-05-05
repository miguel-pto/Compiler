package ast;

public class ExpReferencia extends Nodo {
    public Nodo designador; 

    public ExpReferencia(Nodo d, int f, int c) {
        super(f, c);
        this.designador = d;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpReferencia:");
        System.out.println(indent + "| └── Designador:");
        designador.imprimir(indent + "| | ");
    }
}
