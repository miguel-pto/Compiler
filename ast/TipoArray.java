package ast;

public class TipoArray extends Tipo {
    public Nodo tam;
    public Tipo tipo;

    public TipoArray(Nodo size, Tipo t, int f, int c) {
        super(f, c);
        this.tam = size;
        this.tipo = t;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoArray:");
        System.out.println(indent + "| └── Tamaño:");
        tam.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Tipo de elementos:");
        tipo.imprimir(indent + "| | ");
    }
}
