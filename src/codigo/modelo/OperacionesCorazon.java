package codigo.modelo;

public class OperacionesCorazon implements Interfaz{
    
    private NodoCorazon lista;

    public OperacionesCorazon() {
        this.lista = null;
    }

    @Override
    public void insertarCorazones(int cantidad) {
        
        NodoCorazon nuevoNodo = new NodoCorazon(cantidad);
        
        if (this.lista == null) {
            this.lista = nuevoNodo;
        } else {
            NodoCorazon aux = this.lista;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevoNodo;
        }
    } 
    
    @Override
    public void imprimirLista() {
        System.out.println("Contenido de la lista de corazones:");
        NodoCorazon aux = this.lista;
        while (aux != null) {
            System.out.println(aux.cantidad); // Imprime la cantidad de corazones en el nodo actual
            aux = aux.siguiente;
        }
    }
    
    public NodoCorazon getLista() {
        return this.lista;
    }
}
