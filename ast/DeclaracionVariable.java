package ast;

public class DeclaracionVariable extends Instruccion {
    public String id;
    public Tipo tipo;
    public Nodo valorInicial; // null si no tiene asignación directa

    public DeclaracionVariable(String id, Tipo t, Nodo v, int f, int c) {
        super(f, c);
        this.id = id;
        this.tipo = t;
        this.valorInicial = v;
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
        if (valorInicial != null) {
            valorInicial.vincular();
        }
        vinculador.insertaId(id, this);
    }


    public void imprimir(String indent) {
        System.out.println(indent + "└── DeclaracionVariable: " + id);
        System.out.println(indent + "| └── Tipo: " + tipo);
        if (valorInicial != null) {
            System.out.println(indent + "| └── Valor Inicial:");
            valorInicial.imprimir(indent + "| | ");
        }
    }
}
