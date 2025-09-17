class Node{
	Integer value;
	Node next;

	Node(Integer value) {
		this.value = value;
		this.next = null;
	}
}

class LinkedList {
	private Node head;

	public void insertAtHead(Integer value ) {
	 	Node newNode = new Node(value);
	 	newNode.next = head;
	 	head = newNode;
	}
	
	public void printList() {
		Node current = head;
		while(current != null){

			System.out.print(current.value + "->");
			current = current.next;	
		}
		System.out.print("/");
	}
}

public class LinkedListDemo {
	public static void main(String[] arg){
		LinkedList list = new LinkedList();
		list.insertAtHead(50);
		list.insertAtHead(40);
		list.insertAtHead(30);
		list.insertAtHead(10);
		list.insertAtHead(80);
		list.insertAtHead(70);
		list.insertAtHead(90);
		list.insertAtHead(20);

		list.printList();
	}

}