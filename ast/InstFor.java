package ast;
import java.util.List;

import asint.Main;

public class InstFor extends Instruccion {
    private Nodo ini; 
    private Nodo cond;  
    private Nodo iteracion; 
    private List<Nodo> cuerpo;

    public InstFor(Nodo i, Nodo condicion, Nodo it, List<Nodo> body, int f, int c) {
        super(f, c);
        this.ini = i;
        this.cond = condicion;
        this.iteracion = it;
        this.cuerpo = body;
    }

    public List<Nodo> getCuerpo(){
        return this.cuerpo;
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

    @Override
    public int calcularMemoria(int despActual, int profundidad) {
        int desplLocal = despActual;
        if (ini != null) desplLocal = ini.calcularMemoria(desplLocal, profundidad);
        if (cond != null) desplLocal = cond.calcularMemoria(desplLocal, profundidad);
        if (iteracion != null) desplLocal = iteracion.calcularMemoria(desplLocal, profundidad);
        if (cuerpo != null) {
            for (Nodo n : cuerpo) {
                desplLocal = n.calcularMemoria(desplLocal, profundidad);
            }
        }
        return desplLocal;
    }

    @Override
    public void codeI(StringBuilder sb) {
        if (ini != null) {
            ini.codeI(sb);
        }
        sb.append("  block\n");
        sb.append("    loop\n");
        if (cond != null) {
            ((Expresion)cond).codeE(sb);
            sb.append("      i32.eqz\n");
            sb.append("      br_if 1\n");
        }
        if (cuerpo != null) {
            for (Nodo n : cuerpo) {
                n.codeI(sb);
            }
        }
        if (iteracion != null) {
            iteracion.codeI(sb);
        }
        sb.append("      br 0\n");
        sb.append("    end\n");
        sb.append("  end\n");
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
