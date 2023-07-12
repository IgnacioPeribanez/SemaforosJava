package practica3;

import java.util.concurrent.*;

public class Heap {
	private Node first;
	private Node last;
	private int size;
	
	public Heap () {
		this.first = null;
		this.last = null;
		this.size = 0;
	}
	
	/*
	 * Este semáforo gestiona la posibilidad de realizar un push() en la cola.
	 * Se inicializa a 10 para limitar la longitud de la cola.
	 */
	static Semaphore escribir = new Semaphore(10);
	
	/*
	 * Este semáforo gestiona la posibilidad de realizar un pop() en la cola.
	 * Se inicializa a 0 para asi impedir que se realice un pop() de una cola vacia
	 */
	static Semaphore leer = new Semaphore(0);
	
	/*
	 * Este semáforo gestiona la posibilidad de realizar una acción en la cola, 
	 * ya sea un pop() o un push()
	 * Se inicializa a 1 para así limitar la posibilidad de modificar la cola a un solo
	 * proceso simultaneamente
	 */
	static Semaphore mutex = new Semaphore(1);
	
	public Heap (Node first, Node last, int size) {
		this.first = first;
		this.last = last;
		this.size = 1;
	}
	
	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "Heap [first=" + first + ", last=" + last + ", size=" + size + "]";
	}

	public boolean push(Node node) {
		try {
			System.out.println(node.getContent() + " is waiting for a permit.\n");
			// Coge el permiso para realizar una acción.
			mutex.acquire();
			// Coge el permiso para realizar el push().
			escribir.acquire();
			System.out.println(node.getContent() + " gets a permit.\n");
			if(isEmpty()) {
				first = node;
				last = node;
				size++;
				System.out.println(node.getContent() + " it has been written\n");
				// Libera un permiso para realizar un pop()
				leer.release();
				// Libera un permiso para realizar un push()
				escribir.release();
				// Libera un permiso para realizar una acción.
				mutex.release();
				return true;
			} else {
				last.setNext(node);
				last = node;
				size++; 
				System.out.println(node.getContent() + " escrito\n");
				// Libera un permiso para realizar un pop()
				leer.release();
				// Libera un permiso para realizar un push()
				escribir.release();
				// Libera un permiso para realizar una acción.
				mutex.release();
				return true;
			}
		} catch(Exception e) { System.out.println(e); }
		return false;
	}
	
	public boolean isEmpty() {
		if(size == 0) return true;
		return false;
	}
	
	public Node pop() {
		try {
			System.out.println("Pop is waiting for a permit.\n");
			// Coge el permiso para realizar un pop().
			leer.acquire();
			// Coge el permiso para realizar una acción.
			mutex.acquire();
			if(!isEmpty()) { 
				System.out.println("Pop gets a permit.\n");
				Node p = first;
				System.out.println(first.getContent() + " go out\n");
				first = first.getNext();
				size--;
				// Libera un permiso para realizar una acción.
				mutex.release();
				return p;
			}
		} catch(Exception e) { System.out.println(e); } 
		return null;
	}
	
	public void show() {
		Node p = first;
		for (int i = 0; i < size; i++) {
			System.out.println("[" + i + "] -> " + p.getContent());
			p = p.getNext();
		}
	}
	
}
