package ast;
import java.util.List;

import asint.Main;

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

        @Override
    public void simplifica() {
        if (condicion != null) condicion.simplifica();
        if (cuerpo != null) {
            for (Nodo instr : cuerpo) instr.simplifica();
        }
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
