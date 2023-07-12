package practica3;

// Clase que actúa como contructor de un Thread llamado Writer.
public class Reader extends Thread {
	private Heap heap;
	private Node[] contenido;

	public Reader(Heap heap) {
		this.heap = heap;
		this.contenido = new Node[6];
	}

	public Heap getHeap() {
		return heap;
	}

	public void setHeap(Heap heap) {
		this.heap = heap;
	}

	public Node[] getContenido() {
		return contenido;
	}

	public void setContenido(Node[] contenido) {
		this.contenido = contenido;
	}

	/**
	 * Pre: --- 
	 * Post: Métoto que se encarga de llamar al pop() de la cola 6 veces, y almacenar
	 * 		 los nodos en una tabla.
	 */
	public void run() {
		for (int i = 0; i < 6; i++) {
			contenido[i] = heap.pop();
		}
	}
}