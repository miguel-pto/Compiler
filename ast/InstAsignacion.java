package ast;

import asint.Main;

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

    @Override
    public void simplifica() {
        if (destino != null) {
            destino.simplifica();
        }
        if (valor != null) {
            valor.simplifica();
        }
    }

    @Override
    public void chequea() {
        if (destino != null) destino.chequea();
        if (valor != null) valor.chequea();

        if (destino != null && valor != null) {
            Tipo tDestino = destino.getTipo();
            Tipo tValor = valor.getTipo();
            if (tDestino != null && tValor != null) {
                // Regla Diapositiva 40
                if (!tDestino.equals(tValor)) {
                    Main.gestor.errorSemantico(this.fila(), this.col(), "No se puede asignar " + tValor.tipoKind() + " a " + tDestino.tipoKind());
                }
            }
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
