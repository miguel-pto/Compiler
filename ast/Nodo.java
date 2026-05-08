package ast;

public abstract class Nodo {
    private int fila, col;
    public static Vinculacion vinculador;

    protected Tipo tipo;

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo t) { this.tipo = t; }

    public Nodo(int f, int c) { this.fila = f; this.col = c; }
    public int fila() { return this.fila; }
    public int col() { return this.col; }
    public abstract NodeKind nodeKind();
    public abstract void vincular();
    public abstract void simplifica();
    public abstract void chequea();
    public abstract void imprimir(String indent);
}
