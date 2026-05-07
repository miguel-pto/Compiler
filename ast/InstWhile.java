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

    @Override
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION;
    }

    @Override
    public void vincular() {
        if (condicion != null) {
            condicion.vincular();
        }

        vinculador.abreBloque();
        if (cuerpo != null) {
            for (Nodo instr : cuerpo) {
                instr.vincular();
            }
        }
        vinculador.cierraBloque();
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
