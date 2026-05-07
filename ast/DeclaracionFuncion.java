package ast;
import java.util.List;

public class DeclaracionFuncion extends Instruccion {
    public String nombre;
    public List<Parametro> parametros;
    public Tipo tipo;
    public List<Nodo> cuerpo; 

    public DeclaracionFuncion(String id, List<Parametro> ps, Tipo t, List<Nodo> body, int f, int c) {
        super(f, c);
        this.nombre = id;
        this.parametros = ps;
        this.tipo = t;
        this.cuerpo = body;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.FUNCION;
    }

    @Override
    public void vincular() {
        vinculador.insertaId(nombre, this);
        vinculador.abreBloque();
        if (tipo != null) tipo.vincular();
        for (Parametro p : parametros) {
            p.vincular();
        }
        for (Nodo instr : cuerpo) {
            instr.vincular();
        }
        vinculador.cierraBloque();
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── DeclaracionFuncion: " + nombre);
        System.out.println(indent + "| └── Tipo: ");
        tipo.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Parametros:");
        for (Parametro p : parametros) {
            p.imprimir(indent + "| | ");
        }
        System.out.println(indent + "| └── Cuerpo:");
        for (Nodo instr : cuerpo) {
            instr.imprimir(indent + "| | ");
        }
    }
}
