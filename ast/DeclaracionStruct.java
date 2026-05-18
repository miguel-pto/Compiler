package ast;
import java.util.List;

public class DeclaracionStruct extends Instruccion {
    private String nombre;
    private List<Nodo> campos;
    private int tamanoTotal;

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

    @Override
    public void simplifica() {
        if (campos != null) {
            for (Nodo campo : campos) {
                campo.simplifica();
            }
        }
    }

    @Override
    public void chequea() {
        if (campos != null) {
            for (Nodo campo : campos) {
                campo.chequea();
            }
        }
    }

    @Override
    public int calcularMemoria(int despActual, int profundidad) {
        int despInterno = 0;
        
        if (campos != null) {
            for (Nodo campo : campos) {
                despInterno = campo.calcularMemoria(despInterno, profundidad);
            }
        }
        this.tamanoTotal = despInterno;
        return despActual;
    }

    public int getTam() {
        return tamanoTotal;
    }

    public DeclaracionVariable buscaCampo(String idCampo) {
        for (Nodo n : campos) {
            if (n instanceof DeclaracionVariable) {
                DeclaracionVariable dv = (DeclaracionVariable) n;
                if (dv.getId().equals(idCampo)) {
                    return dv;
                }
            }
        }
        return null;
    }

    public void imprimir(String indent) {
        System.out.println(indent + "└── DeclaracionStruct: " + nombre);
        System.out.println(indent + "| └── Campos:");
        for (Nodo campo : campos) {
            campo.imprimir(indent + "| | ");
        }
    }
}
