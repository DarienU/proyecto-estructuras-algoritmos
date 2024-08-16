package codigo.modelo;

public class NodoInventario {
    public int numero;
    public NodoInventario siguiente;

    public NodoInventario(int numero) {
        this.numero = numero;
        this.siguiente = null;
    }

    // Método para obtener el número del nodo
    public int obtenerNumero() {
        return numero;
    }
}
