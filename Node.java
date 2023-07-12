package practica3;

//Clase que actúa como contructor de un Node con contenido String.
public class Node {
	private String content;
	private Node next;
	
	public Node (String content, Node next) {
		this.content = content;
		this.next = next;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Node [content=" + content + ", next=" + next + "]";
	}
	
}