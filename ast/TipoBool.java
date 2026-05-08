package ast;

public class TipoBool extends Tipo {
    public TipoBool(int f, int c) {
        super(f, c);
    }

    @Override
    public TipoKind tipoKind() {
        return TipoKind.BOOL;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoBool");
    }

    @Override
    public void vincular() {
        
    }

    @Override
    public void simplifica() {

    }

    @Override
    public void chequea() {

    }

}
