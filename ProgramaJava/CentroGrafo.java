package ProgramaJava;
/**
 * Provee el método para encontrar el centro del grafo.
 */
public class CentroGrafo {

    /**
     * Calcula el índice del nodo más cercano a todos los demás (centro).
     * @param dist matriz de distancias mínimas.
     * @return índice del nodo que es centro.
     */
    public static int calcularCentro(double[][] dist) {
        int n = dist.length;
        double[] maximos = new double[n];

        for (int i = 0; i < n; i++) {
            double max = 0;
            for (int j = 0; j < n; j++)
                if (dist[i][j] != Double.POSITIVE_INFINITY && dist[i][j] > max)
                    max = dist[i][j];
            maximos[i] = max;
        }

        int centro = 0;
        double minMax = maximos[0];
        for (int i = 1; i < n; i++)
            if (maximos[i] < minMax) {
                minMax = maximos[i];
                centro = i;
            }
        return centro;
    }
}
