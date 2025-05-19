import java.util.*;

public class Grafo {
    private List<Nodo> nodos;
    private double[][] matrizAdyacencia;
    private final static double INF = Double.POSITIVE_INFINITY;

    public Grafo() {
        nodos = new ArrayList<>();
        matrizAdyacencia = new double[0][0];
    }

    public void agregarConexion(String origen, String destino, double tiempoNormal) {
        Nodo nodoOrigen = new Nodo(origen);
        Nodo nodoDestino = new Nodo(destino);

        if (!nodos.contains(nodoOrigen)) agregarNodo(nodoOrigen);
        if (!nodos.contains(nodoDestino)) agregarNodo(nodoDestino);

        int i = nodos.indexOf(nodoOrigen);
        int j = nodos.indexOf(nodoDestino);

        matrizAdyacencia[i][j] = tiempoNormal;
    }

    public void eliminarConexion(String origen, String destino) {
        int i = nodos.indexOf(new Nodo(origen));
        int j = nodos.indexOf(new Nodo(destino));
        matrizAdyacencia[i][j] = INF;
    }

    private void agregarNodo(Nodo nuevoNodo) {
        nodos.add(nuevoNodo);
        int n = nodos.size();
        double[][] nuevaMatriz = new double[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(nuevaMatriz[i], INF);
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                nuevaMatriz[i][j] = matrizAdyacencia[i][j];
            }
        }

        matrizAdyacencia = nuevaMatriz;
    }

    public double[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public static double getINF() {
        return INF;
    }
}
