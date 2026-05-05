package ast;

public class DesignadorPuntero extends Designador {
    public Nodo puntero;

    public DesignadorPuntero(Nodo p, int f, int c) { 
        super(f, c); 
        this.puntero = p; 
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── DesignadorPuntero:");
        System.out.println(indent + "| └── Puntero:");
        puntero.imprimir(indent + "| | ");
    }
}
