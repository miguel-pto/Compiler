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
    public void chequea() {

    }

    @Override
    public void vincular() {
        
    }

    @Override
    public int getTam() {
        return 4;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoInt");
    }
}
