package ast;

public class InstRead extends Instruccion {
    public Designador destino;

    public InstRead(Designador d, int f, int c) {
        super(f, c);
        this.destino = d;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── InstRead");
        destino.imprimir(indent + "    ");
    }
}
