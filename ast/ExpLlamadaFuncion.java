package ast;
import java.util.List;

import asint.Main;

public class ExpLlamadaFuncion extends Expresion {
    private String idFunc;
    private List<Nodo> argumentos;
    private DeclaracionFuncion definicion;

    public ExpLlamadaFuncion(String id, List<Nodo> args, int f, int c) {
        super(f, c);
        this.idFunc = id;
        this.argumentos = args;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.EXPRESION;
    }

    @Override
    public void vincular() {
        Nodo def = vinculador.buscaId(idFunc);
        
        if (def != null) {
            if (def instanceof DeclaracionFuncion) {
                this.definicion = (DeclaracionFuncion) def;
            } else {
                Main.gestor.errorSemantico(this.fila(), this.col(), "'" + idFunc + "' no es una función.");
                Vinculacion.hayErrorSemantico = true;
            }
        }

        if (argumentos != null) {
            for (Nodo arg : argumentos) {
                arg.vincular();
            }
        }
    }

    @Override
    public void simplifica() {
        if (argumentos != null) {
            for (Nodo arg : argumentos) arg.simplifica();
        }
    }

    @Override
    public void chequea() {
        if (argumentos != null) {
            for (Nodo arg : argumentos) arg.chequea();
        }

        if (definicion != null) {
            if (argumentos.size() != definicion.getParametros().size()) {
                Main.gestor.errorSemantico(this.fila(), this.col(), "Número de parámetros incorrecto.");
            } else {
                for (int i = 0; i < argumentos.size(); i++) {
                    Nodo arg = argumentos.get(i);
                    Parametro p = definicion.getParametros().get(i);
                    if (arg.getTipo() != null && p.tipo != null) {
                        if (!p.tipo.equals(arg.getTipo())) {
                            Main.gestor.errorSemantico(this.fila(), this.col(), "Tipo de argumento incompatible.");
                        }
                    }
                }
            }
            this.setTipo(definicion.getTipoRetorno());
        }
    }

    @Override
    public void codeE(StringBuilder sb) {
        int desplazamiento = 4;
        if (argumentos != null && definicion != null) {
            for (int i = 0; i < argumentos.size(); i++) {
                Nodo arg = argumentos.get(i);
                Parametro pDef = definicion.getParametros().get(i);
                sb.append("    global.get $SP\n");
                sb.append("    i32.const " + desplazamiento + "\n");
                sb.append("    i32.add\n");
                if (pDef.getPorReferencia()) {
                    // Si es REF, metemos en la pila la dirección del argumento
                    ((Designador)arg).codeD(sb);
                } else {
                    // Si es VALOR, metemos en la pila el valor resultante
                    ((Expresion)arg).codeE(sb);
                }
                sb.append("    i32.store\n");
                if (pDef.getPorReferencia()) {
                    desplazamiento += 4;
                } else {
                    desplazamiento += pDef.getTipo().getTam();
                }
            }
        }
        sb.append("    call $" + idFunc + "\n");
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpLlamadaFuncion: " + idFunc);
        System.out.println(indent + "| └── Argumentos:");
        for (Nodo arg : argumentos) {
            arg.imprimir(indent + "| | ");
        }
    }
}
