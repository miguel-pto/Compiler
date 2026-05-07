package ast;
import java.util.List;

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
                System.err.println("[" + fila() + ":" + col() + "] Error: '" + idFunc + "' no es una función.");
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
        System.out.println(indent + "└── InstLlamadaFuncion: " + idFunc);
        System.out.println(indent + "| └── Argumentos:");
        for (Nodo arg : argumentos) {
            arg.imprimir(indent + "| | ");
        }
    }
}
