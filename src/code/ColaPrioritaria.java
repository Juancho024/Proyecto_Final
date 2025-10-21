package code;

public class ColaPrioritaria {
    private String nombre;
    private int peso;
    private int tiempo;
    private float costo;
    private float numTransbordos;

    public ColaPrioritaria(String nombre, int peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
