package ast;

import asint.Main;

public class TipoStruct extends Tipo {
    private String nombre;
    private Nodo definicion;

    public TipoStruct(String id, int f, int c) {
        super(f, c);
        this.nombre = id;
    }

    public String getNombre(){
        return this.nombre;
    }

    @Override
    public TipoKind tipoKind() {
        return TipoKind.STRUCT;
    }

    @Override
    public void vincular() {
        Nodo def = vinculador.buscaId(nombre);
        if (def != null) {
            if (def instanceof DeclaracionStruct) {
                this.definicion = def;
            } else {
                Main.gestor.errorSemantico(this.fila(), this.col(), "'" + nombre + "' no es un Struct definido.");
                Vinculacion.hayErrorSemantico = true;
            }
        }
    }

    @Override
    public void chequea() {
        
    }

    @Override
    public int getTam() {
        if (definicion != null) {
            return ((DeclaracionStruct) definicion).getTam();
        }
        return 0;
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
