public class Runner {
    public static void main(String[] args) {
        SinglyLinkedList linkedList = new SinglyLinkedList();
        linkedList.printList();

        linkedList.append(3);
        linkedList.append(4);
        linkedList.append(1);
        linkedList.push(3);
        linkedList.push(0);
        linkedList.printList();

        linkedList.removeDuplicates();


    }
}
