class Node {
	Integer value;
	Node next;

	Node(Integer value) {
		this.value = value;
		this.next = null;
	}
}


class LinkedList {

	private Node head;

	public void insertAtHead(Integer value) {
		Node newNode = new Node(value);
		newNode.next = head;
		head = newNode;
	}

	public void LinkedListInsertAfter(Node previous, Node newNode) {
		newNode.next = previous.next;
		previous.next = newNode;
	}

	public Node LinkedListLookUp(int elementNumber) {

		Node current = head;
		int count = 0;

		while(count < elementNumber && current != null) {

			current = current.next;
			count = count + 1;

		}

		return current;
	}


	public void printList() {
		Node current = head;
		while(current != null) {
			System.out.print(current.value + "->");
			current = current.next;
		}

		System.out.print("/");
	}

}

public class LinkedListDemo {

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.insertAtHead(50);
		list.insertAtHead(40);
		list.insertAtHead(30);
		list.insertAtHead(20);
		list.insertAtHead(10);

		

		// 10->20->30->71->40->50->/
		// head: 10


		list.printList();

		Node previous = list.LinkedListLookUp(2);
		Node newNode = new Node(71);


		list.LinkedListInsertAfter(previous, newNode);
		list.printList();



	}
} 