package ast;

public class Parametro extends Nodo {
    public String id;
    public Tipo tipo;
    public boolean porReferencia; 

    public Parametro(String id, Tipo t, boolean ref, int f, int c) {
        super(f, c);
        this.id = id;
        this.tipo = t;
        this.porReferencia = ref;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.DECLARACION;
    }

    @Override
    public void vincular() {
        if (tipo != null) {
            tipo.vincular();
        }
        vinculador.insertaId(id, this);
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── Parametro: " + id);
        System.out.println(indent + "| └── Tipo: ");
        tipo.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Por referencia: " + porReferencia);
    }
}
