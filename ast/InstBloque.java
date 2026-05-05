package ast;
import java.util.List;

public class InstBloque extends Instruccion {
    public List<Nodo> instrucciones;

    public InstBloque(List<Nodo> lista, int f, int c) {
        super(f, c);
        this.instrucciones = lista;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── InstBloque:");
        for (Nodo instr : instrucciones) {
            instr.imprimir(indent + "| ");
        }
    }
}
