package ast;
import java.util.List;

public class InstWhile extends Instruccion {
    public Nodo condicion;
    public List<Nodo> cuerpo;

    public InstWhile(Nodo cond, List<Nodo> cuerpo, int f, int c) {
        super(f, c);
        this.condicion = cond;
        this.cuerpo = cuerpo;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── InstWhile:");
        System.out.println(indent + "| └── Condicion:");
        condicion.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Cuerpo:");
        for (Nodo instr : cuerpo) {
            instr.imprimir(indent + "| | ");
        }
    }
}
