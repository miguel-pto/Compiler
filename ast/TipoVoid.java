package ast;

public class TipoVoid extends Tipo {
    public TipoVoid(int f, int c) {
        super(f, c);
    }

    @Override
    public TipoKind tipoKind() {
        return TipoKind.VOID;
    }

    @Override
    public void vincular() {
        
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoVoid");
    }
}
