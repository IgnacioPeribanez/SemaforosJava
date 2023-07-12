package practica3;

public class Main {
	/**
	 * Pre: ---
	 * Post: Método que ejecuta el programa, este genera unos hilos 
	 * 		 que se encargan de gestionarlos dentro de una cola.
	 */
	public static void main(String[] args) {
		Heap heap = new Heap();
		Writer writers[] = new Writer[4];
		Reader readers[] = new Reader[5];
		// Creamos los hilos
		for (int i = 0; i < writers.length; i++) {
			writers[i] = new Writer(i + 1, heap);
		}
		for (int i = 0; i < readers.length; i++) {
			readers[i] = new Reader(heap);
		}
		// Inicializamos los hilos
		for (int i = 0; i < writers.length; i++) {
			writers[i].start();
		}
		for (int i = 0; i < readers.length; i++) {
			readers[i].start();
		}
		// Esperamos que terminen los hilos
		for (int i = 0; i < writers.length; i++) {
			try {
				writers[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < readers.length; i++) {
			try {
				readers[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Mostramos los nodos que a leído cada Reader
		for (int i = 0; i < readers.length; i++) {
			System.out.println("------------------------------------------");
			System.out.println("Reader " + (i + 1) + " has read: \n");
			for (int j = 0; j < readers[i].getContenido().length; j++) {
				System.out.println(readers[i].getContenido()[j].getContent() + "\n");
			}
		}
		// Mostramos la longitud de la cola en su estado final para verificar el resultado
		System.out.println("Final tail length: " + heap.getSize());
	}
}
