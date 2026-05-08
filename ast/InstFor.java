package ast;
import java.util.List;

import asint.Main;

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

    @Override
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION;
    }

    @Override
    public void vincular() {
        vinculador.abreBloque();
        if (ini != null) {
            ini.vincular();
        }
        if (cond != null) {
            cond.vincular();
        }
        if (iteracion != null) {
            iteracion.vincular();
        }
        if (cuerpo != null) {
            for (Nodo instr : cuerpo) {
                instr.vincular();
            }
        }
        vinculador.cierraBloque();
    }

        @Override
    public void simplifica() {
        if (ini != null) ini.simplifica();
        if (cond != null) cond.simplifica();
        if (iteracion != null) iteracion.simplifica();
        if (cuerpo != null) {
            for (Nodo n : cuerpo) n.simplifica();
        }
    }

    @Override
    public void chequea() {
        if (ini != null) ini.chequea();

        if (cond != null) {
            cond.chequea();
            if (cond.getTipo() != null && cond.getTipo().tipoKind() != TipoKind.BOOL) {
                Main.gestor.errorSemantico(this.fila(), this.col(), "La condición del bucle FOR debe ser booleana.");
            }
        }
        if (iteracion != null) iteracion.chequea();
        if (cuerpo != null) {
            for (Nodo n : cuerpo) n.chequea();
        }
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
