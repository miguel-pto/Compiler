package ast;

public class TipoInt extends Tipo {
    public TipoInt(int f, int c) {
        super(f, c);
    }

    @Override
    public TipoKind tipoKind() {
        return TipoKind.INT;
    }

    @Override
    public void vincular() {
        
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoInt");
    }
}
