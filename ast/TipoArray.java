package ast;

import asint.Main;

public class TipoArray extends Tipo {
    private Expresion tam;
    private Tipo tipoElementos;

    public TipoArray(Nodo size, Tipo t, int f, int c) {
        super(f, c);
        this.tam = (Expresion) size;
        this.tipoElementos = t;
    }

    public Tipo getTipoElementos(){
        return this.tipoElementos;
    }

    @Override
    public TipoKind tipoKind() {
        return TipoKind.ARRAY;
    }

    @Override
    public void vincular() {
        if (this.tam != null) this.tam.vincular();
        if (this.tipoElementos != null) this.tipoElementos.vincular();
    }

    @Override
    public void chequea() {
        if (this.tipoElementos != null) {
            this.tipoElementos.chequea();
        }
        
        if (tam != null) {
            tam.chequea();
            if (tam.getTipo() != null && tam.getTipo().tipoKind() != TipoKind.INT) {
                Main.gestor.errorSemantico(this.fila(), this.col(), "El tamaño del array debe ser un entero.");
            }
        }
    }

    @Override
    public int getTam() {
        if (tam instanceof ExpLiteral) {
            return Integer.parseInt(((ExpLiteral)tam).getValor()) * tipoElementos.getTam();
        } 
        return 8; 
    }

    @Override
    public boolean equals(Tipo otro) {
        if (otro == null) return false;
        if (otro.tipoKind() != TipoKind.ARRAY) return false;
        
        TipoArray otroA = (TipoArray) otro;
        return this.tipoElementos.equals(otroA.tipoElementos);
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoArray:");
        System.out.println(indent + "| └── Tamaño:");
        tam.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Tipo de elementos:");
        tipoElementos.imprimir(indent + "| | ");
    }
}
