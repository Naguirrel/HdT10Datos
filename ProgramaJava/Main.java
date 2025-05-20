package ProgramaJava;
import java.io.*;
import java.util.*;

/**
 * Clase principal del programa.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Grafo grafo = new Grafo();

        System.out.println("Seleccione el tipo de clima para calcular rutas:");
        System.out.println("1. Normal");
        System.out.println("2. Lluvia");
        System.out.println("3. Nieve");
        System.out.println("4. Tormenta");
        System.out.print("Opción: ");
        int opcionClima = sc.nextInt();
        sc.nextLine();  // limpiar buffer

        String clima = switch (opcionClima) {
            case 1 -> "Normal";
            case 2 -> "Lluvia";
            case 3 -> "Nieve";
            case 4 -> "Tormenta";
            default -> {
                System.out.println("Opción no válida. Usando 'Normal'.");
                yield "Normal";
            }
        };

        try {
            grafo.cargarDesdeCSV("rutas.csv", clima);
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            return;
        }

        FloydWarshall floyd = new FloydWarshall(grafo);

        while (true) {
            System.out.println("\n1. Ruta más corta entre ciudades");
            System.out.println("2. Centro del grafo");
            System.out.println("3. Interrumpir conexión");
            System.out.println("4. Agregar conexión");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            if (op == 1) {
                System.out.print("Ciudad origen: ");
                String origen = sc.nextLine();
                System.out.print("Ciudad destino: ");
                String destino = sc.nextLine();

                int i = grafo.getNodos().indexOf(new Nodo(origen));
                int j = grafo.getNodos().indexOf(new Nodo(destino));

                if (i == -1 || j == -1) {
                    System.out.println("Ciudad no encontrada");
                    continue;
                }

                List<Integer> ruta = floyd.getRuta(i, j);
                if (ruta == null) {
                    System.out.println("No hay camino disponible");
                } else {
                    System.out.println("Ruta más corta: " + floyd.getDistancias()[i][j]);
                    for (int idx = 0; idx < ruta.size(); idx++) {
                        System.out.print(grafo.getNodos().get(ruta.get(idx)).getNombre());
                        if (idx < ruta.size() - 1) System.out.print(" -> ");
                    }
                    System.out.println();
                }

            } else if (op == 2) {
                int centro = CentroGrafo.calcularCentro(floyd.getDistancias());
                System.out.println("Centro del grafo: " + grafo.getNodos().get(centro).getNombre());

            } else if (op == 3) {
                System.out.print("Ciudad origen: ");
                String origen = sc.nextLine();
                System.out.print("Ciudad destino: ");
                String destino = sc.nextLine();
                grafo.eliminarConexion(origen, destino);
                floyd = new FloydWarshall(grafo);

            } else if (op == 4) {
                System.out.print("Ciudad origen: ");
                String origen = sc.nextLine();
                System.out.print("Ciudad destino: ");
                String destino = sc.nextLine();
                System.out.print("Tiempo con clima " + clima + ": ");
                double tiempo = sc.nextDouble();
                sc.nextLine();
                grafo.agregarConexion(origen, destino, tiempo);
                floyd = new FloydWarshall(grafo);

            } else if (op == 5) {
                break;
            }
        }

        sc.close();
    }
}
