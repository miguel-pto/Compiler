package ast;

public class InstAsignacion extends Instruccion {
    public Nodo destino; 
    public Nodo valor;    

    public InstAsignacion(Nodo d, Nodo v, int f, int c) {
        super(f, c);
        this.destino = d;
        this.valor = v;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── InstAsignacion:");
        System.out.println(indent + "| └── Destino:");
        destino.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Valor:");
        valor.imprimir(indent + "| | ");
    }
}
