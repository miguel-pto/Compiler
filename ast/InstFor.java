package ast;
import java.util.List;

public class InstFor extends Instruccion {
    public Nodo ini; 
    public Nodo cond;  
    public Nodo iteracion; 
    public List<Nodo> cuerpo;

    public InstFor(Nodo i, Nodo condicion, Nodo it, List<Nodo> body, int f, int c) {
        super(f, c);
        this.ini = i;
        this.cond = condicion;
        this.iteracion = it;
        this.cuerpo = body;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── InstFor:");
        System.out.println(indent + "| └── Inicializacion:");
        ini.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Condicion:");
        cond.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Iteracion:");
        iteracion.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Cuerpo:");
        for (Nodo instr : cuerpo) {
            instr.imprimir(indent + "| | ");
        }
    }
}
