package ast;

public class InstAsignacion extends Instruccion {
    public Nodo destino; 
    public Nodo valor;    

    public InstAsignacion(Nodo d, Nodo v, int f, int c) {
        super(f, c);
        this.destino = d;
        this.valor = v;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION;
    }

    @Override
    public void vincular() {
        if (destino != null) {
            destino.vincular();
        }
        if (valor != null) {
            valor.vincular();
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── InstAsignacion:");
        System.out.println(indent + "| └── Destino:");
        destino.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Valor:");
        valor.imprimir(indent + "| | ");
    }
}
