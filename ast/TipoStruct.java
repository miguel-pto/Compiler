package ast;

public class TipoStruct extends Tipo {
    public String nombre;
    private Nodo definicion;

    public TipoStruct(String id, int f, int c) {
        super(f, c);
        this.nombre = id;
    }

    @Override
    public TipoKind tipoKind() {
        return TipoKind.STRUCT;
    }

    @Override
    public void vincular() {
        Nodo def = vinculador.buscaId(nombre);
        if (def != null) {
            // Verificamos que el identificador sea realmente un Struct
            if (def instanceof DeclaracionStruct) {
                this.definicion = def;
            } else {
                System.err.println("[" + fila() + ":" + col() + "] Error: '" + nombre + "' no es un Struct definido.");
                Vinculacion.hayErrorSemantico = true;
            }
        }
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── TipoStruct: " + nombre);
    }
}
