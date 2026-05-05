package ast;

public class ExpNew extends Nodo {
    public Tipo tipoReservado;

    public ExpNew(Tipo t, int f, int c) {
        super(f, c);
        this.tipoReservado = t;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpNew:");
        System.out.println(indent + "| └── Tipo Reservado:");
        tipoReservado.imprimir(indent + "| | ");
    }
}
