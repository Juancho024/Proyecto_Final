package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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

    }



    private void dijkstra(String inicio, String fin) {
        // Implementación del algoritmo de Dijkstra
    }



}
