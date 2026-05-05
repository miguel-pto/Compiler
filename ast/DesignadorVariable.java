package ast;

public class DesignadorVariable extends Designador {
    public String id;

    public DesignadorVariable(String id, int f, int c) { 
        super(f, c); 
        this.id = id; 
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── DesignadorVariable: " + id);
    }
}
