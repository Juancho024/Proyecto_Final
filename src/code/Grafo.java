package code;

import java.util.*;

public class Grafo {
    private HashMap<String, LinkedList<Aristas>> rutas;
    private HashMap<String, Nodo> lugar;

    public Grafo(){
        this.rutas = new HashMap<>();
        this.lugar = new HashMap<>();
    }

    public void agregarNodo(String nombre, int posicionx, int posiciony){ //Agregar parada
        Nodo aux = new Nodo(nombre, posicionx, posiciony);
        lugar.put(nombre, aux);
        rutas.putIfAbsent(nombre, new LinkedList<>());
    }

    public void agregarArista(String origen, String destino, int peso){ //Agregar ruta
        Nodo nodoOrigen = lugar.get(origen);
        Nodo nodoDestino = lugar.get(destino);

        if(nodoOrigen != null && nodoDestino != null){
            Aristas arista = new Aristas(nodoOrigen, nodoDestino, peso);
            rutas.computeIfAbsent(origen, k -> new LinkedList<>()).add(arista);
        } else {
            System.out.println("Uno de los nodos no existe.");
        }
    }

    public void mostrarGrafo(){
        for (String aux : rutas.keySet()) {
            System.out.print("Nodo " + aux + " -> ");
            for (Aristas arista : rutas.get(aux)) {
                System.out.print(arista.getDestino().getNombre() + "(peso: " + arista.getPeso() + ") ");
            }
            System.out.println();
        }
    }

    public static void main (String[] args) {
        Grafo grafo = new Grafo();
        grafo.agregarNodo("A",10,15);
        grafo.agregarNodo("B",10,15);
        grafo.agregarNodo("C",10,15);

        grafo.agregarArista("A", "B", 5);
        grafo.agregarArista("B", "C", 10);
        grafo.agregarArista("A", "C", 15);

        grafo.mostrarGrafo();
        grafo.dijkstra("E", "C");

    }



    private void dijkstra(String inicio, String fin) {
        if(lugar.containsKey(inicio) && lugar.containsKey(fin)){
            HashMap<String, Integer> distancia = new HashMap<>();
            HashMap<String, String> previo = new HashMap<>();
            PriorityQueue<ColaPrioritaria> cola = new PriorityQueue<>(Comparator.comparingInt(ColaPrioritaria::getPeso));

            for(String nodo : lugar.keySet()){
                distancia.put(nodo, Integer.MAX_VALUE);
                previo.put(nodo, null);
            }

            distancia.put(inicio, 0);
            cola.add(new ColaPrioritaria(inicio, 0));
            while(!cola.isEmpty()){
                ColaPrioritaria actual = cola.poll();
                String nombreActual = actual.getNombre();

                for(Aristas arista : rutas.get(nombreActual)){
                    String vecino = arista.getDestino().getNombre();
                    int peso = arista.getPeso();
                    int nuevaDistancia = distancia.get(nombreActual) + peso;

                    if(nuevaDistancia < distancia.get(vecino)){
                        distancia.put(vecino, nuevaDistancia);
                        previo.put(vecino, nombreActual);
                        cola.add(new ColaPrioritaria(vecino, nuevaDistancia));
                    }
                }
            }
            System.out.println("Distancia mínima de " + inicio + " a " + fin + ": " + distancia.get(fin));
        } else {
            System.out.println("Uno de los nodos no existe.");
        }

    }
    private void floydWarshall() {
        // Implementación del algoritmo de Floyd-Warshall
    }

    private void bellmanFord(String inicio) {
        // Implementación del algoritmo de Bellman-Ford
    }
    private void ArbolExpansionMinima() {
        // Implementación del algoritmo de Árbol de Expansión Mínima
    }



}
