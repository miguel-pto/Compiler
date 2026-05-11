package ast;

import java.util.LinkedList;

public class Programa extends Nodo {
    private LinkedList<Nodo> instrucciones;
    private int tamanoGlobales; 

    public Programa(LinkedList<Nodo> instrucciones, int fila, int col) {
        super(fila, col);
        this.instrucciones = instrucciones;
        vinculador = new Vinculacion();
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.PROGRAMA;
    }

    @Override
    public void vincular() {
        vinculador.abreBloque();
        for (Nodo i : instrucciones) {
            if (i != null) {
                i.vincular(); 
            }
        }
        vinculador.cierraBloque();
    }

        @Override
    public void simplifica() {
        for (Nodo i : instrucciones) {
            i.simplifica();
        }
    }

    @Override
    public void chequea() {
        for (Nodo i : instrucciones) {
            i.chequea();
        }
    }

    @Override
    public int calcularMemoria(int despActual, int profundidad) {
        int c = despActual;
        
        for (Nodo i : instrucciones) {
            if (i != null) {
                c = i.calcularMemoria(c, profundidad);
            }
        }
        this.tamanoGlobales = c;    
        return c; 
    }

    @Override
    public void codeI(StringBuilder sb) {
        sb.append("(module\n");
        sb.append("  (import \"runtime\" \"print\" (func $print (param i32)))\n");
        sb.append("  (import \"runtime\" \"read\" (func $read (result i32)))\n");
        sb.append("  (memory 2000)\n");
        sb.append("  (export \"memory\" (memory 0))\n");
        sb.append("  (global $SP (mut i32) (i32.const 0))\n");
        sb.append("  (global $MP (mut i32) (i32.const 0))\n");
        sb.append("  (global $NP (mut i32) (i32.const 131072000))\n\n");
        sb.append("  (func $reserveStack (param $size i32)\n");
        sb.append("    global.get $SP\n");
        sb.append("    local.get $size\n");
        sb.append("    i32.add\n");
        sb.append("    global.set $SP\n");
        sb.append("  )\n\n");
        sb.append("  (func $reserveHeap (param $size i32) (result i32)\n");
        sb.append("    global.get $NP\n");
        sb.append("    local.get $size\n");
        sb.append("    i32.sub\n");
        sb.append("    global.set $NP\n");
        sb.append("    global.get $NP\n");
        sb.append("  )\n\n");

        for (Nodo i : instrucciones) {
            if (i instanceof DeclaracionFuncion) {
                i.codeI(sb);
            }
        }
        sb.append("  (func $init_global\n");
        sb.append("    i32.const ").append(this.tamanoGlobales).append("\n");
        sb.append("    global.set $SP\n");
        sb.append("    global.get $SP\n");
        sb.append("    global.set $MP\n\n");
        for (Nodo i : instrucciones) {
            if (i instanceof DeclaracionVariable) {
                i.codeI(sb);
            }
        }

        sb.append("    call $main\n"); 
        sb.append("  )\n");
        sb.append("  (export \"main\" (func $init_global))\n");
        sb.append(")\n");
    }



    @Override
    public void imprimir(String indent) {
        System.out.println(indent + "Programa:");
        for (Nodo i : instrucciones) {
            i.imprimir(indent + "  ");
        }
    }
}
