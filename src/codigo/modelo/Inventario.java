package codigo.modelo;

public class Inventario {

    private NodoInventario lista;

    public Inventario() {
        this.lista = null;
    }

    public void insertarInicio(int numero) {
        NodoInventario nuevoNodo = new NodoInventario(numero);
        nuevoNodo.siguiente = this.lista;
        this.lista = nuevoNodo;
    }

    // Método para imprimir la lista
    public void imprimir() {
        NodoInventario aux = this.lista;
        while (aux != null) {
            System.out.print(aux.numero + " ");
            aux = aux.siguiente;
        }
        System.out.println();
    }

    // Método para obtener el nodo inicial de la lista
    public NodoInventario obtenerLista() {
        return this.lista;
    }

    public void ordenarLista() {
        if (this.lista == null || this.lista.siguiente == null) {
            return;
        }

        NodoInventario aux = this.lista;

        while (aux != null) {
            NodoInventario nodoMinimo = encontrarNodoMinimo(aux);

            intercambiarNodos(aux, nodoMinimo);

            aux = aux.siguiente;
        }
    }

    private NodoInventario encontrarNodoMinimo(NodoInventario inicio) {
        NodoInventario minimo = inicio;
        NodoInventario aux = inicio.siguiente;

        while (aux != null) {
            if (aux.numero < minimo.numero) {
                minimo = aux;
            }
            aux = aux.siguiente;
        }

        return minimo;
    }

    private void intercambiarNodos(NodoInventario nodo1, NodoInventario nodo2) {
        int temp = nodo1.numero;
        nodo1.numero = nodo2.numero;
        nodo2.numero = temp;
    }

    public boolean buscarNumero(int numero) {
        NodoInventario aux = this.lista;

        while (aux != null) {
            if (aux.numero == numero) {
                return true;
            }
            aux = aux.siguiente;
        }

        return false;
    }
    
    
    public void eliminarInicio(int numero) {
        if (this.lista == null) {
            return; // No hay elementos en la lista
        }

        // Verificar si el primer nodo contiene el número especificado
        if (this.lista.numero == numero) {
            this.lista = this.lista.siguiente; // Eliminar el primer nodo
        }
    }

}
