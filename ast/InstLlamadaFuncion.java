package ast;
import java.util.List;

import asint.Main;

public class InstLlamadaFuncion extends Instruccion {
    public String idFunc;
    public List<Nodo> argumentos; // Lista de expresiones (valores pasados)
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
            if (argumentos.size() != definicion.parametros.size()) {
                Main.gestor.errorSemantico(this.fila(), this.col(), "Número de argumentos incorrecto para '" + idFunc + "'.");
                return;
            }
            for (int i = 0; i < argumentos.size(); i++) {
                Nodo arg = argumentos.get(i);
                Parametro param = definicion.parametros.get(i);
                
                if (arg.getTipo() != null && param.tipo != null) {
                    if (!param.tipo.equals(arg.getTipo())) {
                        Main.gestor.errorSemantico(this.fila(), this.col(), "El argumento " + (i+1) + " de '" + idFunc + "' debe ser " + param.tipo.tipoKind() + ".");
                    }
                }
            }
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
