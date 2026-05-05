package ast;

public class TipoVoid extends Tipo {
    public TipoVoid(int f, int c) {
        super(f, c);
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoVoid");
    }
}
