package code;

public class Aristas {
    private Nodo origen;
    private Nodo destino;
    private int peso;
    private int tiempoRecorrido;
    private float costo;
    private float numTransbordos;

    public Aristas(Nodo origen, Nodo destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public Aristas(Nodo origen, Nodo destino, int peso, int tiempoRecorrido, float costo, float numTransbordos) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.tiempoRecorrido = tiempoRecorrido;
        this.costo = costo;
        this.numTransbordos = numTransbordos;
    }

    public Nodo getOrigen() {
        return origen;
    }

    public void setOrigen(Nodo origen) {
        this.origen = origen;
    }

    public Nodo getDestino() {
        return destino;
    }

    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getTiempoRecorrido() {
        return tiempoRecorrido;
    }

    public void setTiempoRecorrido(int tiempoRecorrido) {
        this.tiempoRecorrido = tiempoRecorrido;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getNumTransbordos() {
        return numTransbordos;
    }

    public void setNumTransbordos(float numTransbordos) {
        this.numTransbordos = numTransbordos;
    }
}
