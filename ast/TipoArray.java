package ast;

import asint.Main;

public class TipoArray extends Tipo {
    public Expresion tam;
    public Tipo tipo;

    public TipoArray(Nodo size, Tipo t, int f, int c) {
        super(f, c);
        this.tam = (Expresion) size;
        this.tipo = t;
    }

    @Override
    public TipoKind tipoKind() {
        return TipoKind.ARRAY;
    }

    @Override
    public void vincular() {
        if (this.tam != null) this.tam.vincular();
        if (this.tipo != null) this.tipo.vincular();
    }


    @Override
    public void simplifica() {
        if (this.tam != null) this.tam.simplifica();
        if (this.tipo != null) this.tipo.simplifica();
    }

    @Override
    public void chequea() {
        if (this.tipo != null) {
            this.tipo.chequea();
        }
        
        if (tam != null) {
            tam.chequea();
            if (tam.getTipo() != null && tam.getTipo().tipoKind() != TipoKind.INT) {
                Main.gestor.errorSemantico(this.fila(), this.col(), "El tamaño del array debe ser un entero.");
            }
        }
    }

    @Override
    public boolean equals(Tipo otro) {
        if (otro == null) return false;
        if (otro.tipoKind() != TipoKind.ARRAY) return false;
        
        TipoArray otroA = (TipoArray) otro;
        return this.tipo.equals(otroA.tipo);
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoArray:");
        System.out.println(indent + "| └── Tamaño:");
        tam.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Tipo de elementos:");
        tipo.imprimir(indent + "| | ");
    }
}
