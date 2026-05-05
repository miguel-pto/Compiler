package ast;

public class TipoBool extends Tipo {
    public TipoBool(int f, int c) {
        super(f, c);
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoBool");
    }
}
