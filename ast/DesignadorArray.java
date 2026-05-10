package ast;

import asint.Main;

public class DesignadorArray extends Designador {
    private Designador designador; 
    private Expresion indice;
    
    public DesignadorArray(Nodo d, Nodo ind, int f, int c) {
        super(f, c); 
        this.designador = (Designador) d; 
        this.indice = (Expresion) ind;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.DESIGNADOR;
    }

    @Override
    public void vincular() {
        if (designador != null) {
            designador.vincular();
        }
        if (indice != null) {
            indice.vincular();
        }
    }

        @Override
    public void simplifica() {
        if (designador != null) designador.simplifica();
        if (indice != null) indice.simplifica();
    }

    @Override
    public void chequea() {
        designador.chequea();
        indice.chequea();

        if (designador.getTipo() == null || indice.getTipo() == null) {
            setTipo(null);
            return;
        }
        if (designador.getTipo().tipoKind() != TipoKind.ARRAY) {
            Main.gestor.errorSemantico(this.fila(), this.col(), "El designador no es de tipo array.");
            setTipo(null);
        } 
        else if (indice.getTipo().tipoKind() != TipoKind.INT) {
            Main.gestor.errorSemantico(this.fila(), this.col(), "El índice debe ser de tipo entero.");
            setTipo(null);
        } 
        else {
            TipoArray ta = (TipoArray) designador.getTipo();
            setTipo(ta.getTipoElementos());
        }
    }

    @Override
    public void codeD(StringBuilder sb) {
        sb.append("    ;; --- Acceso a Array (codeD) ---\n");
        // 1. Calculamos la dirección base del array (recursivo por si es matriz)
        designador.codeD(sb); 

        // 2. Calculamos el valor del índice
        indice.codeE(sb);

        // 3. Multiplicamos el índice por el tamaño del tipo de los elementos
        // Obtenemos el tipo del array para saber el tamaño de sus celdas
        TipoArray ta = (TipoArray) designador.getTipo();
        int tamElemento = ta.getTipoElementos().getTam();
        sb.append("    i32.const ").append(tamElemento).append("\n");
        sb.append("    i32.mul\n");

        // 4. Sumamos el desplazamiento a la dirección base
        sb.append("    i32.add\n");
    }

    @Override
    public void codeE(StringBuilder sb) {
        this.codeD(sb); // Calculamos la dirección del elemento
        
        TipoKind k = this.getTipo().tipoKind();
        
        // Solo hacemos load si es un valor escalar que cabe en la pila
        if (k == TipoKind.INT || k == TipoKind.BOOL || k == TipoKind.PUNTERO) {
            sb.append("    i32.load\n");
        } else if (k == TipoKind.FLOAT) {
            sb.append("    f32.load\n");
        }
        // Si es STRUCT o ARRAY, NO hacemos load. 
        // La dirección se queda en la pila como referencia.
    }



    public void imprimir(String indent) {
        System.out.println(indent + "└── DesignadorArray:");
        System.out.println(indent + "| └── Designador:");
        designador.imprimir(indent + "| | ");
        System.out.println(indent + "| └── Indice:");
        indice.imprimir(indent + "| | ");
    }
}
