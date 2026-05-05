package ast;

public class TipoStruct extends Tipo {
    public String nombre;

    public TipoStruct(String id, int f, int c) {
        super(f, c);
        this.nombre = id;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoStruct: " + nombre);
    }
}
