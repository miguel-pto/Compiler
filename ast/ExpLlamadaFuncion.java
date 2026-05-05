package ast;
import java.util.List;

public class ExpLlamadaFuncion extends Nodo {
    public String idFunc;
    public List<Nodo> argumentos;

    public ExpLlamadaFuncion(String id, List<Nodo> args, int f, int c) {
        super(f, c);
        this.idFunc = id;
        this.argumentos = args;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpLlamadaFuncion: " + idFunc);
        System.out.println(indent + "| └── Argumentos:");
        for (Nodo arg : argumentos) {
            arg.imprimir(indent + "| | ");
        }
    }
}
