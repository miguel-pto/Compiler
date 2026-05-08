package ast;

import asint.Main;

public class DeclaracionVariable extends Instruccion {
    public String id;
    public Expresion valorInicial; // null si no tiene asignación directa

    public DeclaracionVariable(String id, Tipo t, Nodo v, int f, int c) {
        super(f, c);
        this.id = id;
        setTipo(t);
        this.valorInicial = (Expresion) v;
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


    public void imprimir(String indent) {
        System.out.println(indent + "└── DeclaracionVariable: " + id);
        System.out.println(indent + "| └── Tipo: " + tipo);
        if (valorInicial != null) {
            System.out.println(indent + "| └── Valor Inicial:");
            valorInicial.imprimir(indent + "| | ");
        }
    }
}
