package ast;

public class DesignadorCampo extends Designador {
    public Nodo registro;
    public String campo;
    private Nodo vinculacionCampo;

    public DesignadorCampo(Nodo r, String field, int f, int c) {
        super(f, c); 
        this.registro = r; 
        this.campo = field;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.DESIGNADOR;
    }

    @Override
    public void vincular() {
        if (registro != null) {
            registro.vincular();
        }
    }

    @Override
    public void imprimir(String indent) {
        System.out.println(indent + "└── DesignadorCampo:");
        System.out.println(indent + "| └── Registro:");
        registro.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Campo: " + campo);
    }
}
