public class Runner {
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList(0);
        dll.push(3);
        dll.push(5);
        dll.append(15);

        dll.printList();
        dll.reverse();
        dll.printList();


    }
}
