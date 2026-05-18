package ast;
import java.util.List;

import asint.Main;

public class DeclaracionFuncion extends Instruccion {
    private String nombre;
    private List<Parametro> parametros;
    private Tipo tipoRetorno;
    private List<Nodo> cuerpo; 
    private int tamanoMarco;

    public DeclaracionFuncion(String id, List<Parametro> ps, Tipo t, List<Nodo> body, int f, int c) {
        super(f, c);
        this.nombre = id;
        this.parametros = ps;
        this.tipoRetorno = t;
        this.cuerpo = body;
    }

    public List<Parametro> getParametros(){
        return this.parametros;
    }

    public Tipo getTipoRetorno(){
        return this.tipoRetorno;
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
                    Main.gestor.errorSemantico(this.fila(), this.col(), "La funcion '" + nombre + "' deberia devolver " + 
                        esperado.tipoKind() + " pero devuelve " + devuelto.tipoKind() + ".");
                }
            }
        } 
        else if (n instanceof InstIf) {
            InstIf nIf = (InstIf) n;
            for (Nodo hijo : nIf.getBloqueIf()) validarRetornosRecursivo(hijo, esperado);
            if (nIf.getBloqueElse() != null) {
                for (Nodo hijo : nIf.getBloqueElse()) validarRetornosRecursivo(hijo, esperado);
            }
        } 
        else if (n instanceof InstWhile) {
            InstWhile nWhile = (InstWhile) n;
            for (Nodo hijo : nWhile.getCuerpo()) validarRetornosRecursivo(hijo, esperado);
        }
        else if (n instanceof InstFor) {
            InstFor nFor = (InstFor) n;
            for (Nodo hijo : nFor.getCuerpo()) validarRetornosRecursivo(hijo, esperado);
        }
    }

    @Override
    public int calcularMemoria(int despActual, int profundidad) {
        int despParametros = 4;
        int profLocal = 1;
        for (Parametro p : parametros) {
            despParametros = p.calcularMemoria(despParametros, profLocal);
        }
        int maxAlcanzado = despParametros; 
        for (Nodo instr : cuerpo) {
            if (instr != null) maxAlcanzado = instr.calcularMemoria(maxAlcanzado, profLocal);
        }
        this.tamanoMarco = maxAlcanzado;
        return despActual;
    }


    @Override
    public void codeI(StringBuilder sb) {
        sb.append("  (func $").append(nombre);
        if (tipoRetorno.tipoKind() != TipoKind.VOID) {
            if (tipoRetorno.tipoKind() == TipoKind.FLOAT) {
                sb.append(" (result f32)");
            } else {
                sb.append(" (result i32)");
            }
        }
        sb.append("\n");

        sb.append("    ;; Guardar enlace dinamico: Memoria[SP] = MP viejo\n");
        sb.append("    global.get $SP\n");
        sb.append("    global.get $MP\n");
        sb.append("    i32.store\n");
        sb.append("    ;; Establecer nuevo MP\n");
        sb.append("    global.get $SP\n");
        sb.append("    global.set $MP\n");
        sb.append("    ;; Reservar espacio en el Stack: SP = SP + tamanoMarco\n");
        sb.append("    global.get $SP\n");
        sb.append("    i32.const ").append(tamanoMarco).append("\n");
        sb.append("    i32.add\n");
        sb.append("    global.set $SP\n");

        for (Nodo instr : cuerpo) {
            instr.codeI(sb);
        }

        sb.append("    ;; Epílogo\n");
        sb.append("    global.get $MP\n");
        sb.append("    global.set $SP\n");
        sb.append("    global.get $MP\n");
        sb.append("    i32.load\n");
        sb.append("    global.set $MP\n");
        sb.append("  )\n");
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
