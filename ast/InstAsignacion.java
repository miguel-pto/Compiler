package ast;

import asint.Main;

public class InstAsignacion extends Instruccion {
    private Nodo destino; 
    private Nodo valor;    

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
    public void chequea() {
        if (destino != null) destino.chequea();
        if (valor != null) valor.chequea();
        if (destino != null && valor != null) {
            Tipo tDestino = destino.getTipo();
            Tipo tValor = valor.getTipo();
            if (tDestino != null && tValor != null) {
                if (!tDestino.equals(tValor)) {
                    Main.gestor.errorSemantico(this.fila(), this.col(), "No se puede asignar " + tValor.tipoKind() + " a " + tDestino.tipoKind());
                }
            }
        }
    }

    @Override
    public void codeI(StringBuilder sb) {
        if (destino != null && valor != null) {
            destino.codeD(sb);
            ((Expresion)valor).codeE(sb);
            if (destino.getTipo().tipoKind() == TipoKind.FLOAT) {
                sb.append("    f32.store\n");
            } else {
                sb.append("    i32.store\n");
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
