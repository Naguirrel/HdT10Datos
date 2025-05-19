# Librerías
import networkx as nx

class GrafoLogistico:
    def __init__(self):
        self.G = nx.DiGraph()

    def agregar_conexion(self, origen, destino, tiempo):
        self.G.add_edge(origen, destino, weight=tiempo)

    def eliminar_conexion(self, origen, destino):
        if self.G.has_edge(origen, destino):
            self.G.remove_edge(origen, destino)

    def nodos(self):
        return list(self.G.nodes)

    def floyd_warshall(self):
        return dict(nx.floyd_warshall(self.G, weight='weight'))

    def ruta_mas_corta(self, origen, destino):
        try:
            ruta = nx.reconstruct_path(origen, destino, nx.floyd_warshall_predecessor_and_distance(self.G, weight='weight')[0])
            distancia = nx.shortest_path_length(self.G, origen, destino, weight='weight')
            return ruta, distancia
        except (nx.NetworkXNoPath, nx.NodeNotFound):
            return None, float('inf')

    def centro(self):
        distancias = self.floyd_warshall()
        max_dist = {}
        for nodo in distancias:
            max_dist[nodo] = max(distancias[nodo].values(), default=float('inf'))
        centro = min(max_dist, key=max_dist.get)
        return centro


def main():
    grafo = GrafoLogistico()
    grafo.agregar_conexion("BuenosAires", "SaoPaulo", 10)
    grafo.agregar_conexion("BuenosAires", "Lima", 15)
    grafo.agregar_conexion("Lima", "Quito", 10)

    while True:
        print("\n1. Ruta más corta entre ciudades")
        print("2. Centro del grafo")
        print("3. Interrumpir conexión")
        print("4. Agregar conexión")
        print("5. Salir")
        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            origen = input("Ciudad origen: ")
            destino = input("Ciudad destino: ")
            ruta, distancia = grafo.ruta_mas_corta(origen, destino)
            if ruta:
                print(f"Ruta más corta: {distancia}")
                print(" -> ".join(ruta))
            else:
                print("No hay camino disponible")

        elif opcion == "2":
            print("Centro del grafo:", grafo.centro())

        elif opcion == "3":
            origen = input("Ciudad origen: ")
            destino = input("Ciudad destino: ")
            grafo.eliminar_conexion(origen, destino)

        elif opcion == "4":
            origen = input("Ciudad origen: ")
            destino = input("Ciudad destino: ")
            tiempo = float(input("Tiempo con clima normal: "))
            grafo.agregar_conexion(origen, destino, tiempo)

        elif opcion == "5":
            break