package ast;

public class TipoFloat extends Tipo {
    public TipoFloat(int f, int c) {
        super(f, c);
    }

        @Override
    public TipoKind tipoKind() {
        return TipoKind.FLOAT;
    }

    @Override
    public void vincular() {

    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoFloat");
    }
}
