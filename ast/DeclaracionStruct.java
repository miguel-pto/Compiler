package ast;
import java.util.List;

public class DeclaracionStruct extends Instruccion {
    public String nombre;
    public List<Nodo> campos;

    public DeclaracionStruct(String id, List<Nodo> lista, int f, int c) {
        super(f, c);
        this.nombre = id;
        this.campos = lista;
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.DECLARACION;
    }

    @Override
    public void vincular() {
        vinculador.insertaId(nombre, this);
        vinculador.abreBloque();

        if (campos != null) {
            for (Nodo campo : campos) {
                campo.vincular();
            }
        }
        vinculador.cierraBloque();
    }
    public void imprimir(String indent) {
        System.out.println(indent + "└── DeclaracionStruct: " + nombre);
        System.out.println(indent + "| └── Campos:");
        for (Nodo campo : campos) {
            campo.imprimir(indent + "| | ");
        }
    }
}
