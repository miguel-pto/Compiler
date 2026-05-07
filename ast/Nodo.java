package ast;

public abstract class Nodo {
    private int fila, col;
    public static Vinculacion vinculador;

    public Nodo(int f, int c) { this.fila = f; this.col = c; }
    public int fila() { return fila; }
    public int col() { return col; }
    public abstract NodeKind nodeKind();
    public abstract void vincular();
    public abstract void imprimir(String indent);
}
