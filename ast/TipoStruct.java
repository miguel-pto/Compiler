package ast;

import asint.Main;

public class TipoStruct extends Tipo {
    public String nombre;
    private Nodo definicion;

    public TipoStruct(String id, int f, int c) {
        super(f, c);
        this.nombre = id;
    }

    @Override
    public TipoKind tipoKind() {
        return TipoKind.STRUCT;
    }

    @Override
    public void vincular() {
        Nodo def = vinculador.buscaId(nombre);
        if (def != null) {
            // Verificamos que el identificador sea realmente un Struct
            if (def instanceof DeclaracionStruct) {
                this.definicion = def;
            } else {
                Main.gestor.errorSemantico(this.fila(), this.col(), "'" + nombre + "' no es un Struct definido.");
                Vinculacion.hayErrorSemantico = true;
            }
        }
    }

    @Override
    public void simplifica() {

    }

    @Override
    public void chequea() {
        
    }

    @Override
    public boolean equals(Tipo otro) {
        if (otro == null) return false;
        if (otro.tipoKind() != TipoKind.STRUCT) return false;
        
        TipoStruct otroS = (TipoStruct) otro;
        return this.nombre.equals(otroS.nombre);
    }

    public DeclaracionStruct getDefinicion() {
        return (DeclaracionStruct) definicion;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoStruct: " + nombre);
    }
}
