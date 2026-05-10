package ast;

import asint.Main;

public class DesignadorVariable extends Designador {
    private String id;
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

    @Override
    public void codeD(StringBuilder sb) {        
        int desp = definicion.getDesplazamiento();
        int profundidad = definicion.getPa();
        if (profundidad == 0) {
            sb.append("    i32.const " + desp + "\n");
        } else {
            sb.append("    global.get $MP\n");
            sb.append("    i32.const " + desp + "\n");
            sb.append("    i32.add\n");
        }
        if (definicion instanceof Parametro) {
            Parametro p = (Parametro) definicion;
            if (p.getPorReferencia()) {
                sb.append("    i32.load\n"); // Seguimos el puntero
            }
        }
    }

    @Override
    public void codeE(StringBuilder sb) {
        this.codeD(sb);
        sb.append("    i32.load\n");
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── DesignadorVariable: " + id);
    }
}
