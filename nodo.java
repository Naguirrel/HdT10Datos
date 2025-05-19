/**
 * Representa un nodo del grafo, correspondiente a una ciudad.
 */
public class Nodo {
    private String nombre;

    /**
     * Crea un nodo con el nombre especificado.
     * @param nombre nombre de la ciudad.
     */
    public Nodo(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del nodo (ciudad).
     * @return nombre de la ciudad.
     */
    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo nodo = (Nodo) o;
        return nombre.equals(nodo.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}
