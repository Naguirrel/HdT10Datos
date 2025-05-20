package ProgramaJava;
import java.io.*;
import java.util.*;

/**
 * Grafo dirigido usando matriz de adyacencia cargado desde CSV.
 */
public class Grafo {
    private List<Nodo> nodos;
    private double[][] matrizAdyacencia;
    private final static double INF = Double.POSITIVE_INFINITY;

    public Grafo() {
        nodos = new ArrayList<>();
        matrizAdyacencia = new double[0][0];
    }

    /**
     * Carga conexiones desde archivo CSV según el tipo de clima.
     * @param archivo nombre del archivo CSV
     * @param clima tipo de clima: Normal, Lluvia, Nieve o Tormenta
     * @throws IOException si no se puede leer
     */
    public void cargarDesdeCSV(String archivo, String clima) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String header = br.readLine(); // leer encabezado
        String[] columnas = header.split(",");
        int indiceClima = -1;

        for (int i = 2; i < columnas.length; i++) {
            if (columnas[i].equalsIgnoreCase(clima)) {
                indiceClima = i;
                break;
            }
        }

        if (indiceClima == -1) {
            br.close();
            throw new IllegalArgumentException("Clima no válido: " + clima);
        }

        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length > indiceClima) {
                String origen = partes[0];
                String destino = partes[1];
                double peso = Double.parseDouble(partes[indiceClima]);
                agregarConexion(origen, destino, peso);
            }
        }
        br.close();
    }

    public void agregarConexion(String origen, String destino, double peso) {
        Nodo nodoOrigen = new Nodo(origen);
        Nodo nodoDestino = new Nodo(destino);

        if (!nodos.contains(nodoOrigen)) agregarNodo(nodoOrigen);
        if (!nodos.contains(nodoDestino)) agregarNodo(nodoDestino);

        int i = nodos.indexOf(nodoOrigen);
        int j = nodos.indexOf(nodoDestino);
        matrizAdyacencia[i][j] = peso;
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

        for (int i = 0; i < n; i++)
            Arrays.fill(nuevaMatriz[i], INF);

        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - 1; j++)
                nuevaMatriz[i][j] = matrizAdyacencia[i][j];

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
