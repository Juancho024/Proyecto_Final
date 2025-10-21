package code;

import java.util.*;

public class Grafo {
    private HashMap<String, LinkedList<Aristas>> rutas;
    private HashMap<String, Nodo> lugar;

    public Grafo() {
        this.rutas = new HashMap<>();
        this.lugar = new HashMap<>();
    }

    public void agregarNodo(String nombre, int posicionx, int posiciony) { //Agregar parada
        Nodo aux = new Nodo(nombre, posicionx, posiciony);
        lugar.put(nombre, aux);
        rutas.putIfAbsent(nombre, new LinkedList<>());
    }

    public void agregarArista(String origen, String destino, int peso) { //Agregar ruta
        Nodo nodoOrigen = lugar.get(origen);
        Nodo nodoDestino = lugar.get(destino);

        if (nodoOrigen != null && nodoDestino != null) {
            Aristas arista = new Aristas(nodoOrigen, nodoDestino, peso);
            rutas.computeIfAbsent(origen, k -> new LinkedList<>()).add(arista);
        } else {
            System.out.println("Uno de los lugares no existe.");
        }
    }

    public void mostrarGrafo() {
        System.out.print("Grafo: \n");
        for (String aux : rutas.keySet()) {
            LinkedList<Aristas> listRutas = rutas.get(aux);
            System.out.print("Lugar " + aux + " tiene rutas al ");
            for (int i = 0; i < listRutas.size(); i++) {
                Aristas arista = listRutas.get(i);
                System.out.print(arista.getDestino().getNombre() + " hay " + arista.getPeso() + "km");
                if (i != listRutas.size() - 1) {
                    System.out.print(", al ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.agregarNodo("A", 10, 15);
        grafo.agregarNodo("B", 10, 15);
        grafo.agregarNodo("C", 10, 15);

        grafo.agregarArista("A", "B", 5);
        grafo.agregarArista("B", "C", 10);
        grafo.agregarArista("A", "C", 12);

        grafo.mostrarGrafo();
        grafo.dijkstra("A", "C");
        grafo.floydWarshall();
    }


    private void dijkstra(String inicio, String fin) {
        if (lugar.containsKey(inicio) && lugar.containsKey(fin)) {
            HashMap<String, Integer> distancia = new HashMap<>();
            HashMap<String, Integer> tiempoRecorrido = new HashMap<>();
            HashMap<String, Float> costo = new HashMap<>();
            HashMap<String, Integer> numTransbordo = new HashMap<>();
            HashMap<String, String> previo = new HashMap<>();
            PriorityQueue<ColaPrioritaria> cola = new PriorityQueue<>(Comparator.comparingInt(ColaPrioritaria::getPeso));

            for (String nodo : lugar.keySet()) {
                distancia.put(nodo, Integer.MAX_VALUE);
                tiempoRecorrido.put(nodo, Integer.MAX_VALUE);
                costo.put(nodo, Float.MAX_VALUE);
                numTransbordo.put(nodo, Integer.MAX_VALUE);
                previo.put(nodo, null);

            }

            distancia.put(inicio, 0);
            tiempoRecorrido.put(inicio, 0);
            costo.put(inicio, 0f);
            numTransbordo.put(inicio, 0);
            cola.add(new ColaPrioritaria(inicio, 0));
            while (!cola.isEmpty()) {
                ColaPrioritaria actual = cola.poll();
                String nombreActual = actual.getNombre();

                for (Aristas arista : rutas.get(nombreActual)) {
                    String vecino = arista.getDestino().getNombre();
                    int peso = arista.getPeso();
                    int nuevaDistancia = distancia.get(nombreActual) + peso;
                    float nuevoCosto = costo.get(nombreActual) + arista.getCosto();
                    int nuevoTiempo = tiempoRecorrido.getOrDefault(nombreActual, 0) + arista.getTiempoRecorrido();
                    int nuevoNumTransbordo = numTransbordo.getOrDefault(nombreActual, 0) + (arista.getNumTransbordos() > 0 ? 1 : 0);


                    if (nuevaDistancia < distancia.get(vecino) && nuevoCosto < costo.get(vecino) && nuevoTiempo < tiempoRecorrido.getOrDefault(vecino, Integer.MAX_VALUE)
                            && nuevoNumTransbordo < numTransbordo.getOrDefault(vecino, Integer.MAX_VALUE)) {
                        distancia.put(vecino, nuevaDistancia);
                        costo.put(vecino, (Float) nuevoCosto);
                        tiempoRecorrido.put(vecino, nuevoTiempo);
                        numTransbordo.put(vecino, nuevoNumTransbordo);
                        previo.put(vecino, nombreActual);
                        cola.add(new ColaPrioritaria(vecino, nuevaDistancia));
                    }
                }
            }
            System.out.println("Distancia mínima de " + inicio + " a " + fin + ": " + distancia.get(fin));
        } else {
            System.out.println("Uno de los lugares no existe.");
        }

    }

    private void floydWarshall() {
        // Implementación del algoritmo de Floyd-Warshall
        LinkedList<String> nombres = new LinkedList<>(lugar.keySet());
        HashMap<String, Integer> indice = new HashMap<>();

        int n = lugar.size();
        for (int i = 0; i < n; i++) {
            indice.put(nombres.get(i), i);
        }
        int distancia[][] = new int[n][n];
        final int numMax = Integer.MAX_VALUE / 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    distancia[i][j] = 0;
                else
                    distancia[i][j] = numMax;
            }
        }
        for (String lugar : lugar.keySet()) {
            for (Aristas ruta : rutas.get(lugar)) {
                int i = indice.get(lugar);
                int j = indice.get(ruta.getDestino().getNombre());
                distancia[i][j] = ruta.getPeso();
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancia[i][j] > distancia[i][k] + distancia[k][j]) {
                        distancia[i][j] = distancia[i][k] + distancia[k][j];
                    }
                }
            }
        }
        System.out.println("\nMatriz de distancias mínimas:");
        System.out.print("     ");
        for (String nombre : nombres) {
            System.out.printf("%5s", nombre);
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.printf("%5s", nombres.get(i));
            for (int j = 0; j < n; j++) {
                if (distancia[i][j] == numMax)
                    System.out.printf("%5s", "∞");
                else
                    System.out.printf("%5d", distancia[i][j]);
            }
            System.out.println();
        }

    }

    private void generarEventoRandow() {
        // Implementación para generar eventos aleatorios en el grafo
        Random random = new Random();
        int numEventos = random.nextInt(2) + 1;
        switch (numEventos) {
            case 1:
            {

            }
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }

    }

    private void bellmanFord(String inicio) {
        // Implementación del algoritmo de Bellman-Ford
    }

    private void ArbolExpansionMinima() {
        // Implementación del algoritmo de Árbol de Expansión Mínima
    }


}
