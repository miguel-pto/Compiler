package ast;

import asint.Main;

public class DeclaracionVariable extends Instruccion {
    private String id;
    private Expresion valorInicial; // null si no tiene asignación directa

    public DeclaracionVariable(String id, Tipo t, Nodo v, int f, int c) {
        super(f, c);
        this.id = id;
        setTipo(t);
        this.valorInicial = (Expresion) v;
    }

    public String getId(){
        return this.id;
    }

    @Override
    public int calcularMemoria(int despActual, int profundidad) {
        this.desplazamiento = despActual;
        this.pa = profundidad;
        return despActual + getTipo().getTam();
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

    @Override
    public void simplifica() {
        if (tipo != null) tipo.simplifica();
        if (valorInicial != null) valorInicial.simplifica();
    }

    @Override
    public void chequea() {
        if (tipo != null) {
            tipo.chequea();
        }

        if (valorInicial != null) {
            valorInicial.chequea();
            if (tipo != null && valorInicial.getTipo() != null) {
                if (!tipo.equals(valorInicial.getTipo())) {
                    Main.gestor.errorSemantico(this.fila(), this.col(), "El tipo del valor inicial no coincide con el tipo declarado para '" + id + "'.");
                }
            }
        }
    }

    @Override
    public void codeD(StringBuilder sb) {
        if (this.pa == 0) {
            sb.append("    i32.const ").append(this.desplazamiento).append("\n");
        } else {
            sb.append("    global.get $MP\n");
            sb.append("    i32.const ").append(this.desplazamiento).append("\n");
            sb.append("    i32.add\n");
        }
    }

    @Override
    public void codeI(StringBuilder sb) {
        if (valorInicial != null) {            
            this.codeD(sb);
            valorInicial.codeE(sb);
            if (this.getTipo().tipoKind() == TipoKind.FLOAT) {
                sb.append("    f32.store\n");
            } else {
                sb.append("    i32.store\n");
            }
        }
    }

    @Override
    public void codeE(StringBuilder sb) {
        this.codeD(sb);
        if (this.getTipo().tipoKind() == TipoKind.FLOAT) {
            sb.append("    f32.load\n");
        } else {
            sb.append("    i32.load\n");
        }
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
