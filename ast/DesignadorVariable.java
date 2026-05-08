package ast;

import asint.Main;

public class DesignadorVariable extends Designador {
    public String id;
    private Nodo definicion;

    public DesignadorVariable(String id, int f, int c) { 
        super(f, c); 
        this.id = id; 
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.DESIGNADOR;
    }

    @Override
    public void vincular() {
        this.definicion = vinculador.buscaId(id);        
    }

    @Override
    public void simplifica() {
        
    }

    @Override
    public void chequea() {
        if (definicion == null) {
            this.setTipo(null);
        } else if (!(definicion instanceof DeclaracionVariable || definicion instanceof Parametro)) {
            Main.gestor.errorSemantico(this.fila(), this.col(), "'" + id + "' no es una variable válida.");
            this.setTipo(null);
        } else {
            this.setTipo(definicion.getTipo());
        }
    }

    public Nodo getVinculo() {
        return definicion;
    }
    public void imprimir(String indent) {
        System.out.println(indent + "└── DesignadorVariable: " + id);
    }
}
