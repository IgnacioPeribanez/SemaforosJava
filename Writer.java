package practica3;

// Clase que actúa como contructor de un Thread llamado Writer.
public class Writer extends Thread {
	private int id;
	private Heap heap;

	public Writer(int id, Heap heap) {
		this.heap = heap;
		this.id = id;
	}

	/**
	 * Pre: --- 
	 * Post: Métoto que se encarga de llamar al push() de la cola 8 veces.
	 */
	public void run() {
		for (int i = 0; i < 8; i++) {
			heap.push(new Node("Writer " + id + ", process " + (i + 1), null));
		}
	}
}