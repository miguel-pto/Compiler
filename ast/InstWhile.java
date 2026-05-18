package ast;
import java.util.List;

import asint.Main;

public class InstWhile extends Instruccion {
    private Nodo condicion;
    private List<Nodo> cuerpo;

    public InstWhile(Nodo cond, List<Nodo> cuerpo, int f, int c) {
        super(f, c);
        this.condicion = cond;
        this.cuerpo = cuerpo;
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

    @Override
    public void chequea() {
        if (condicion != null) {
            condicion.chequea();
            if (condicion.getTipo() != null && condicion.getTipo().tipoKind() != TipoKind.BOOL) {
                Main.gestor.errorSemantico(this.fila(), this.col(), "La condición del WHILE debe ser de tipo BOOL.");
            }
        }

        if (cuerpo != null) {
            for (Nodo instr : cuerpo) {
                instr.chequea();
            }
        }
    }

    @Override
    public int calcularMemoria(int despActual, int profundidad) {
        int despLocal = despActual;
        if (cuerpo != null) {
            for (Nodo instr : cuerpo) {
                despLocal = instr.calcularMemoria(despLocal, profundidad);
            }
        }
        return despLocal;
    }

    @Override
    public void codeI(StringBuilder sb) {
        sb.append("  block\n");
        sb.append("    loop\n");
        if (condicion != null) {
            ((Expresion)condicion).codeE(sb);
            sb.append("      i32.eqz\n");
            sb.append("      br_if 1\n");  
        }
        if (cuerpo != null) {
            for (Nodo instr : cuerpo) {
                instr.codeI(sb);
            }
        }
        sb.append("      br 0\n");
        sb.append("    end\n"); 
        sb.append("  end\n");  
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
