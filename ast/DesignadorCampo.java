package ast;

import asint.Main;

public class DesignadorCampo extends Designador {
    private Designador registro;
    private String campo;
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
                    Main.gestor.errorSemantico(this.fila(), this.col(), "Miembro '" + campo + "' no existe en struct '" + ts.getNombre() + "'.");
                    setTipo(null);
                } else {
                    setTipo(this.vinculacionCampo.getTipo());
                }
            }
        }
    }

    @Override
    public void codeD(StringBuilder sb) {        
        registro.codeD(sb);
        int desplCampo = vinculacionCampo.getDesplazamiento();
        sb.append("    i32.const ").append(desplCampo).append("\n");
        sb.append("    i32.add\n");
    }

    @Override
    public void codeE(StringBuilder sb) {
        this.codeD(sb);
        TipoKind k = this.getTipo().tipoKind();
        
        if (k == TipoKind.INT || k == TipoKind.BOOL || k == TipoKind.PUNTERO) {
            sb.append("    i32.load\n");
        } else if (k == TipoKind.FLOAT) {
            sb.append("    f32.load\n");
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
