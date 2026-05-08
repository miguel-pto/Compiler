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
    @Override
    public void simplifica() {
        // Tipo base, no se simplifica
    }

    @Override
    public void chequea() {
        // No tiene restricciones internas
    }
    
    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoFloat");
    }
}
