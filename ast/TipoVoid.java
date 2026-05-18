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

    @Override
    public void simplifica() {
        // Ya es tipo base
    }

    @Override
    public void chequea() {

    }

    @Override
    public int getTam() {
        return 0; 
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoVoid");
    }
}
