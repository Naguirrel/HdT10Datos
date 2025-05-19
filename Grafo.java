import java.util.*;

/**
 * Representa un grafo dirigido utilizando matriz de adyacencia.
 */
public class Grafo {
    private List<Nodo> nodos;
    private double[][] matrizAdyacencia;
    private final static double INF = Double.POSITIVE_INFINITY;

    /**
     * Constructor que inicializa el grafo vacío.
     */
    public Grafo() {
        nodos = new ArrayList<>();
        matrizAdyacencia = new double[0][0];
    }

    /**
     * Agrega una conexión dirigida entre dos nodos.
     * @param origen nombre de la ciudad origen.
     * @param destino nombre de la ciudad destino.
     * @param tiempoNormal peso de la conexión.
     */
    public void agregarConexion(String origen, String destino, double tiempoNormal) {
        Nodo nodoOrigen = new Nodo(origen);
        Nodo nodoDestino = new Nodo(destino);

        if (!nodos.contains(nodoOrigen)) agregarNodo(nodoOrigen);
        if (!nodos.contains(nodoDestino)) agregarNodo(nodoDestino);

        int i = nodos.indexOf(nodoOrigen);
        int j = nodos.indexOf(nodoDestino);
        matrizAdyacencia[i][j] = tiempoNormal;
    }

    /**
     * Elimina una conexión entre dos ciudades.
     * @param origen ciudad origen.
     * @param destino ciudad destino.
     */
    public void eliminarConexion(String origen, String destino) {
        int i = nodos.indexOf(new Nodo(origen));
        int j = nodos.indexOf(new Nodo(destino));
        matrizAdyacencia[i][j] = INF;
    }

    private void agregarNodo(Nodo nuevoNodo) {
        nodos.add(nuevoNodo);
        int n = nodos.size();
        double[][] nuevaMatriz = new double[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(nuevaMatriz[i], INF);
        for (int i = 0; i < n - 1; i++)
            System.arraycopy(matrizAdyacencia[i], 0, nuevaMatriz[i], 0, n - 1);
        matrizAdyacencia = nuevaMatriz;
    }

    /**
     * Obtiene la matriz de adyacencia.
     * @return matriz de adyacencia.
     */
    public double[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    /**
     * Obtiene la lista de nodos del grafo.
     * @return lista de nodos.
     */
    public List<Nodo> getNodos() {
        return nodos;
    }

    /**
     * Obtiene el valor infinito usado para representar ausencia de conexión.
     * @return infinito.
     */
    public static double getINF() {
        return INF;
    }
}
