package ast;

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

    public Nodo getVinculo() {
        return definicion;
    }
    public void imprimir(String indent) {
        System.out.println(indent + "└── DesignadorVariable: " + id);
    }
}
