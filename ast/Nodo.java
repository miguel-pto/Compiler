package ast;

public abstract class Nodo {
    private int fila, col;
    public static Vinculacion vinculador;

    protected Tipo tipo;
    protected int desplazamiento;
    protected int pa;

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo t) { this.tipo = t; }

    public Nodo(int f, int c) { this.fila = f; this.col = c; }
    public int fila() { return this.fila; }
    public int col() { return this.col; }
    public int getDesplazamiento(){
        return this.desplazamiento;
    }
    public int getPa(){
        return this.pa;
    }
    public abstract NodeKind nodeKind();
    public abstract void vincular();
    public abstract void simplifica();
    public abstract void chequea();
    public int calcularMemoria(int despActual, int profundidad) {
        return despActual;
    }
    public void codeE(StringBuilder sb) {}
    public void codeD(StringBuilder sb) {}
    public void codeI(StringBuilder sb) {}
    public abstract void imprimir(String indent);
}
