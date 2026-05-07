package ast;
import java.util.List;

public class ExpLlamadaFuncion extends Nodo {
    public String idFunc;
    public List<Nodo> argumentos;
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
                System.err.println("[" + fila() + ":" + col() + "] Error Semántico: '" + idFunc + "' no es una función.");
                Vinculacion.hayErrorSemantico = true;
            }
        }

        if (argumentos != null) {
            for (Nodo arg : argumentos) {
                arg.vincular();
            }
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpLlamadaFuncion: " + idFunc);
        System.out.println(indent + "| └── Argumentos:");
        for (Nodo arg : argumentos) {
            arg.imprimir(indent + "| | ");
        }
    }
}
