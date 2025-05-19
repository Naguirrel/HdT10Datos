package ProgramaJava;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Pruebas unitarias del sistema de grafo y algoritmo de Floyd.
 */
public class GrafoTest {

    @Test
    public void testAgregarYEliminarConexion() {
        Grafo grafo = new Grafo();
        grafo.agregarConexion("A", "B", 5.0);
        assertEquals(5.0, grafo.getMatrizAdyacencia()[0][1]);
        grafo.eliminarConexion("A", "B");
        assertEquals(Grafo.getINF(), grafo.getMatrizAdyacencia()[0][1]);
    }

    @Test
    public void testRutaMasCortaFloyd() {
        Grafo grafo = new Grafo();
        grafo.agregarConexion("A", "B", 2.0);
        grafo.agregarConexion("B", "C", 3.0);
        grafo.agregarConexion("A", "C", 10.0);
        FloydWarshall floyd = new FloydWarshall(grafo);

        List<Integer> ruta = floyd.getRuta(0, 2);
        assertEquals(List.of(0, 1, 2), ruta);
        assertEquals(5.0, floyd.getDistancias()[0][2]);
    }

    @Test
    public void testCentroDelGrafo() {
        Grafo grafo = new Grafo();
        grafo.agregarConexion("A", "B", 2);
        grafo.agregarConexion("B", "C", 4);
        grafo.agregarConexion("C", "A", 6);
        FloydWarshall floyd = new FloydWarshall(grafo);
        int centro = CentroGrafo.calcularCentro(floyd.getDistancias());

        assertTrue(centro >= 0 && centro < grafo.getNodos().size());
    }

    @Test
    public void testNodosIguales() {
        Nodo n1 = new Nodo("X");
        Nodo n2 = new Nodo("X");
        Nodo n3 = new Nodo("Y");

        assertEquals(n1, n2);
        assertNotEquals(n1, n3);
        assertEquals(n1.hashCode(), n2.hashCode());
    }
}
