package ast;

public class TipoFloat extends Tipo {
    public TipoFloat(int f, int c) {
        super(f, c);
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoFloat");
    }
}
