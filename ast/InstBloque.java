package ast;
import java.util.List;

public class InstBloque extends Instruccion {
    private List<Nodo> instrucciones;

    public InstBloque(List<Nodo> lista, int f, int c) {
        super(f, c);
        this.instrucciones = lista;
    }
    @Override
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION;
    }

    @Override
    public void vincular() {
        vinculador.abreBloque();
        if (instrucciones != null) {
            for (Nodo instr : instrucciones) {
                instr.vincular();
            }
        }
        vinculador.cierraBloque();
    }

    @Override
    public void simplifica() {
        if (instrucciones != null) {
            for (Nodo instr : instrucciones) {
                instr.simplifica();
            }
        }
    }

    @Override
    public void chequea() {
        if (instrucciones != null) {
            for (Nodo instr : instrucciones) {
                instr.chequea();
            }
        }
    }

    @Override
    public int calcularMemoria(int despActual, int profundidad) {
        int c_inicial = despActual;
        int c = despActual;
        if (instrucciones != null) {
            for (Nodo i : instrucciones) {
                if (i != null) {
                    c = i.calcularMemoria(c, profundidad);
                }
            }
        }
        return c_inicial; 
    }

    @Override
    public void codeI(StringBuilder sb) {
        if (instrucciones != null) {
            for (Nodo i : instrucciones) {
                if (i != null) {
                    i.codeI(sb);
                }
            }
        }
    }


    public void imprimir(String indent) {
        System.out.println(indent + "└── InstBloque:");
        for (Nodo instr : instrucciones) {
            instr.imprimir(indent + "| ");
        }
    }
}
