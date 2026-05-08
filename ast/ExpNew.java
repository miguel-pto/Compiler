package ast;

public class ExpNew extends Nodo {
    public Tipo tipoReservado;

    public ExpNew(Tipo t, int f, int c) {
        super(f, c);
        this.tipoReservado = t;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.EXPRESION;
    }

    @Override
    public void vincular() {
        if (tipoReservado != null) {
            tipoReservado.vincular();
        }
    }


    @Override
    public void simplifica() {
        if (tipoReservado != null) {
            tipoReservado.simplifica();
        }
    }

    @Override
    public void chequea() {
        if (tipoReservado != null) {
            tipoReservado.chequea();
            this.setTipo(new TipoPuntero(tipoReservado, fila(), col()));
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── ExpNew:");
        System.out.println(indent + "| └── Tipo Reservado:");
        tipoReservado.imprimir(indent + "| | ");
    }
}
