package ast;
import java.util.List;

import asint.Main;

public class InstIf extends Instruccion {
    private Nodo condicion;
    private List<Nodo> bloqueIf;
    private List<Nodo> bloqueElse; // null si no hay else

    public InstIf(Nodo cond, List<Nodo> i, List<Nodo> e, int f, int c) {
        super(f, c);
        this.condicion = cond;
        this.bloqueIf = i;
        this.bloqueElse = e;
    }

    public List<Nodo> getBloqueIf(){
        return this.bloqueIf;
    }

    public List<Nodo> getBloqueElse(){
        return this.bloqueElse;
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
        for (Nodo instr : bloqueIf) {
            instr.vincular();
        }
        vinculador.cierraBloque(); 
        if (bloqueElse != null) {
            vinculador.abreBloque();
            for (Nodo instr : bloqueElse) {
                instr.vincular();
            }
            vinculador.cierraBloque();
        }
    }

    @Override
    public void simplifica() {
        if (condicion != null) condicion.simplifica();
        if (bloqueIf != null) {
            for (Nodo n : bloqueIf) n.simplifica();
        }
        if (bloqueElse != null) {
            for (Nodo n : bloqueElse) n.simplifica();
        }
    }

    @Override
    public void chequea() {
        if (condicion != null) {
            condicion.chequea();
            if (condicion.getTipo() != null && condicion.getTipo().tipoKind() != TipoKind.BOOL) {
                Main.gestor.errorSemantico(this.fila(), this.col(), "La condición del IF debe ser booleana.");
            }
        }
        if (bloqueIf != null) {
            for (Nodo n : bloqueIf) {
                n.chequea();
            }
        }
        if (bloqueElse != null) {
            for (Nodo n : bloqueElse) {
                n.chequea();
            }
        }
    }

    @Override
    public int calcularMemoria(int despActual, int profundidad) {
        int inicioIf = despActual;
        int despFinalIf = inicioIf;
        if (bloqueIf != null) {
            for (Nodo n : bloqueIf) {
                despFinalIf = n.calcularMemoria(despFinalIf, profundidad);
            }
        }
        int despFinalElse = inicioIf;
        if (bloqueElse != null) {
            for (Nodo n : bloqueElse) {
                despFinalElse = n.calcularMemoria(despFinalElse, profundidad);
            }
        }
        if (despFinalIf > despFinalElse) {
            return despFinalIf;
        } else {
            return despFinalElse;
        }
    }

    @Override
    public void codeI(StringBuilder sb) {
        if (condicion != null) {
            ((Expresion)condicion).codeE(sb);
        }
        sb.append("    if\n");
        if (bloqueIf != null) {
            for (Nodo n : bloqueIf) {
                n.codeI(sb);
            }
        }
        if (bloqueElse != null) {
            sb.append("    else\n");
            for (Nodo n : bloqueElse) {
                n.codeI(sb);
            }
        }
        sb.append("    end\n");
    }

    
    public void imprimir(String indent) {
        System.out.println(indent + "└── InstIf:");
        System.out.println(indent + "| └── Condicion:");
        condicion.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Bloque If:");
        for (Nodo instr : bloqueIf) {
            instr.imprimir(indent + "| | ");
        }
        if (bloqueElse != null) {
            System.out.println(indent + "| └── Bloque Else:");
            for (Nodo instr : bloqueElse) {
                instr.imprimir(indent + "| | ");
            }
        }
    }
}
