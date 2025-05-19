import java.util.*;

public class FloydWarshall {
    private double[][] dist;
    private int[][] next;

    public FloydWarshall(Grafo grafo) {
        int n = grafo.getNodos().size();
        dist = new double[n][n];
        next = new int[n][n];

        double[][] matriz = grafo.getMatrizAdyacencia();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = matriz[i][j];
                if (matriz[i][j] != Grafo.getINF()) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    public List<Integer> getRuta(int u, int v) {
        if (next[u][v] == -1) return null;
        List<Integer> ruta = new ArrayList<>();
        ruta.add(u);
        while (u != v) {
            u = next[u][v];
            ruta.add(u);
        }
        return ruta;
    }

    public double[][] getDistancias() {
        return dist;
    }

    public int[][] getNext() {
        return next;
    }
}
