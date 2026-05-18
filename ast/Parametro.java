package ast;

public class Parametro extends Nodo {
    private String id;
    private boolean porReferencia; 
    private int desplazamiento;
    private int pa;

    public Parametro(String id, Tipo t, boolean ref, int f, int c) {
        super(f, c);
        this.id = id;
        setTipo(t);
        this.porReferencia = ref;
    }

    public boolean getPorReferencia(){
        return this.porReferencia;
    }

    @Override
    public int calcularMemoria(int despActual, int profundidad) {
        this.desplazamiento = despActual;
        this.pa = profundidad;
        
        int tamano;
        if (this.porReferencia) {
            tamano = 4;
        } else {
            tamano = getTipo().getTam();
        }
        return despActual + tamano;
    }

    public int getDesplazamiento() { 
        return desplazamiento; 
    }
    
    public int getPa() { 
        return pa; 
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

    @Override
    public void chequea() {
        if (tipo != null) {
            tipo.chequea();
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── Parametro: " + id);
        System.out.println(indent + "| └── Tipo: ");
        tipo.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Por referencia: " + porReferencia);
    }
}
