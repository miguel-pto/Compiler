package ast;

import asint.Main;

public class DesignadorCampo extends Designador {
    public Designador registro;
    public String campo;
    private Nodo vinculacionCampo;

    public DesignadorCampo(Nodo r, String field, int f, int c) {
        super(f, c); 
        this.registro = (Designador) r; 
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
    public void simplifica() {
        if (registro != null) registro.simplifica();
    }

    @Override
    public void chequea() {
        registro.chequea();

        if (registro.getTipo() == null) {
            setTipo(null);
            return;
        }

        if (registro.getTipo().tipoKind() != TipoKind.STRUCT) {
            Main.gestor.errorSemantico(this.fila(), this.col(), "El designador debe ser de tipo struct.");
            setTipo(null);
        } else {
            TipoStruct ts = (TipoStruct) registro.getTipo();
            DeclaracionStruct def = ts.getDefinicion();
            if (def != null) {
                this.vinculacionCampo = def.buscaCampo(campo);
                if (this.vinculacionCampo == null) {
                    Main.gestor.errorSemantico(this.fila(), this.col(), "Miembro '" + campo + "' no existe en struct '" + ts.nombre + "'.");
                    setTipo(null);
                } else {
                    setTipo(this.vinculacionCampo.getTipo());
                }
            }
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
