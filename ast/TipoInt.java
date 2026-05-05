package ast;

public class TipoInt extends Tipo {
    public TipoInt(int f, int c) {
        super(f, c);
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoInt");
    }
}
