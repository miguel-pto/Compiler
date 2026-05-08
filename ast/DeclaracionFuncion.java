package ast;
import java.util.List;

import asint.Main;

public class DeclaracionFuncion extends Instruccion {
    public String nombre;
    public List<Parametro> parametros;
    public Tipo tipoRetorno;
    public List<Nodo> cuerpo; 

    public DeclaracionFuncion(String id, List<Parametro> ps, Tipo t, List<Nodo> body, int f, int c) {
        super(f, c);
        this.nombre = id;
        this.parametros = ps;
        this.tipoRetorno = t;
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
        if (tipoRetorno != null) tipoRetorno.vincular();
        for (Parametro p : parametros) {
            p.vincular();
        }
        for (Nodo instr : cuerpo) {
            instr.vincular();
        }
        vinculador.cierraBloque();
    }
    @Override
    public void simplifica() {
        if (tipoRetorno != null) tipoRetorno.simplifica();
        for (Parametro p : parametros) p.simplifica();
        for (Nodo instr : cuerpo) instr.simplifica();
    }

    @Override
    public void chequea() {
        for (Parametro p : parametros) p.chequea();
        if (tipoRetorno != null) tipoRetorno.chequea();
        for (Nodo instr : cuerpo) {
            instr.chequea();
        }
        for (Nodo instr : cuerpo) {
            validarRetornosRecursivo(instr, this.tipoRetorno);
        }
    }

    private void validarRetornosRecursivo(Nodo n, Tipo esperado) {
        if (n == null) return;

        if (n instanceof InstReturn) {
            InstReturn ir = (InstReturn) n;
            Tipo devuelto = ir.getTipo();
            if (devuelto != null && esperado != null) {
                if (!esperado.equals(devuelto)) {
                    Main.gestor.errorSemantico(this.fila(), this.col(), "La función '" + nombre + "' debería retornar " + 
                        esperado.tipoKind() + " pero retorna " + devuelto.tipoKind() + ".");
                }
            }
        } 
        else if (n instanceof InstIf) {
            InstIf nIf = (InstIf) n;
            for (Nodo hijo : nIf.bloqueIf) validarRetornosRecursivo(hijo, esperado);
            if (nIf.bloqueElse != null) {
                for (Nodo hijo : nIf.bloqueElse) validarRetornosRecursivo(hijo, esperado);
            }
        } 
        else if (n instanceof InstWhile) {
            InstWhile nWhile = (InstWhile) n;
            for (Nodo hijo : nWhile.cuerpo) validarRetornosRecursivo(hijo, esperado);
        }
        else if (n instanceof InstFor) {
            InstFor nFor = (InstFor) n;
            for (Nodo hijo : nFor.cuerpo) validarRetornosRecursivo(hijo, esperado);
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── DeclaracionFuncion: " + nombre);
        System.out.println(indent + "| └── Tipo: ");
        tipoRetorno.imprimir(indent + "| | ");
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
