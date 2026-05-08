package ast;
import java.util.List;

import asint.Main;

public class InstIf extends Instruccion {
    public Nodo condicion;
    public List<Nodo> bloqueIf;
    public List<Nodo> bloqueElse; // null si no hay else

    public InstIf(Nodo cond, List<Nodo> i, List<Nodo> e, int f, int c) {
        super(f, c);
        this.condicion = cond;
        this.bloqueIf = i;
        this.bloqueElse = e;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION;
    }

    @Override
    public void vincular() {
        // Vinculamos la condición en el ámbito donde está el IF
        if (condicion != null) {
            condicion.vincular();
        }

        // Bloque IF: Creamos un nuevo ámbito 
        vinculador.abreBloque();
        for (Nodo instr : bloqueIf) {
            instr.vincular();
        }
        vinculador.cierraBloque(); 

        // Bloque ELSE: Si existe, creamos otro ámbito independiente
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
