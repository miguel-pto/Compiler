package ast;
import java.util.List;

import asint.Main;

public class InstLlamadaFuncion extends Instruccion {
    private String idFunc;
    private List<Nodo> argumentos;
    private DeclaracionFuncion definicion;

    public InstLlamadaFuncion(String id, List<Nodo> args, int f, int c) {
        super(f, c);
        this.idFunc = id;
        this.argumentos = args;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION; 
    }

    @Override
    public void vincular() {
        Nodo def = vinculador.buscaId(idFunc);
        
        if (def != null) {
            if (def.nodeKind() == NodeKind.FUNCION) {
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
    public void chequea() {
        if (argumentos != null) {
            for (Nodo arg : argumentos) arg.chequea();
        }

        if (definicion != null) {
            if (argumentos.size() != definicion.getParametros().size()) {
                Main.gestor.errorSemantico(this.fila(), this.col(), "Número de argumentos incorrecto para '" + idFunc + "'.");
                return;
            }
            for (int i = 0; i < argumentos.size(); i++) {
                Nodo arg = argumentos.get(i);
                Parametro param = definicion.getParametros().get(i);
                
                if (arg.getTipo() != null && param.tipo != null) {
                    if (!param.tipo.equals(arg.getTipo())) {
                        Main.gestor.errorSemantico(this.fila(), this.col(), "El argumento " + (i+1) + " de '" + idFunc + "' debe ser " + param.tipo.tipoKind() + ".");
                    }
                }
            }
        }
    }

    @Override
    public int calcularMemoria(int despActual, int profundidad) {
        int tamArgs = 4; 
        if (argumentos != null && definicion != null) {
            for (int i = 0; i < argumentos.size(); i++) {
                Parametro pDef = definicion.getParametros().get(i);
                if (pDef.getPorReferencia()) {
                    tamArgs += 4;
                } else {
                    tamArgs += pDef.getTipo().getTam();
                }
            }
        }
        if (tamArgs > despActual) {
            return tamArgs;
        }
        return despActual;
    }


    @Override
    public void codeI(StringBuilder sb) {
        int desplazamiento = 4;
        if (argumentos != null && definicion != null) {
            for (int i = 0; i < argumentos.size(); i++) {
                Nodo arg = argumentos.get(i);
                Parametro pDef = definicion.getParametros().get(i);
                sb.append("    global.get $SP\n");
                sb.append("    i32.const " + desplazamiento + "\n");
                sb.append("    i32.add\n");
                if (pDef.getPorReferencia()) {
                    ((Designador)arg).codeD(sb);
                } else {
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
        if (definicion != null && definicion.getTipoRetorno().tipoKind() != TipoKind.VOID) {
            sb.append("    drop\n");
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── InstLlamadaFuncion: " + idFunc);
        System.out.println(indent + "| └── Argumentos:");
        for (Nodo arg : argumentos) {
            arg.imprimir(indent + "| | ");
        }
    }
}
