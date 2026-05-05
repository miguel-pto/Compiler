package ast;
import java.util.List;

public class InstLlamadaFuncion extends Instruccion {
    public String idFunc;
    public List<Nodo> argumentos; // Lista de expresiones (valores pasados)

    public InstLlamadaFuncion(String id, List<Nodo> args, int f, int c) {
        super(f, c);
        this.idFunc = id;
        this.argumentos = args;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── InstLlamadaFuncion: " + idFunc);
        System.out.println(indent + "| └── Argumentos:");
        for (Nodo arg : argumentos) {
            arg.imprimir(indent + "| | ");
        }
    }
}
