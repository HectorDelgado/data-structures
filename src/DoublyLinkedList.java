public class DoublyLinkedList {
    private DLLNode head;

    public DoublyLinkedList() {
        head = null;
    }

    public DoublyLinkedList(int data) {
        head = new DLLNode(data);
    }

    /**
     * Adds a node at the front of the list.
     * @param data The ata that will be added.
     */
    public void push(int data) {
        // Allocate the node and put in data
        DLLNode newNode = new DLLNode(data);

        // Make next of new node as head, prev as null
        newNode.setNext(head);
        newNode.setPrev(null);

        // Update prev of old head to newNode
        if (head != null) {
            head.setPrev(newNode);
        }

        // Move head to point tp newNode
        head = newNode;
    }

    public void insertAfter(DLLNode previousNode, int data) {
        if (previousNode == null) {
            throw new IllegalArgumentException("Previous node cannot be null");
        }

        // Allocate the node and put in data
        DLLNode newNode = new DLLNode(data);

        // Make next of newNode as next of previousNode
        newNode.setNext(previousNode.getNext());

        // Make next of previousNode as newNode
        previousNode.setNext(newNode);

        // Make prev of newNode as previousNode
        newNode.setPrev(previousNode);

        // Update prev of newNodes next node
        if (newNode.getNext() != null) {
            newNode.getNext().setPrev(newNode);
        }
    }

    public void insertBefore(DLLNode nextNode, int data) {

    }

    public void append(int data) {
        // Allocate the node and put in data
        DLLNode newNode = new DLLNode(data);

        // If linked list if empty, make newNode as head
        if (head == null) {
            head = newNode;
        } else {
            // Else traverse list until last node
            DLLNode lastNode = head;
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }

            // Change the next of last node
            lastNode.setNext(newNode);

            // Make prev of newNode as lastNode
            newNode.setPrev(lastNode);
        }
    }

    /**
     * Removes a node from the list.
     * @param deletedNode The Node to be removed
     */
    public void remove(DLLNode deletedNode) {
        // Make sure list is not empty
        if (head != null && deletedNode != null) {
            // If node to be deleted is head node, move head to next
            if (head == deletedNode) {
                head = head.getNext();
            }

            // Change the prev of deletedNode next node if its not last node
            if (deletedNode.getNext() != null) {
                deletedNode.getNext().setPrev(deletedNode.getPrev());
            }

            // Change the next of deletedNode previous node if its not first node
            if (deletedNode.getPrev() != null) {
                deletedNode.getPrev().setNext(deletedNode.getNext());
            }
        }
    }

    /**
     * Removes a node with the given data from the list.
     * @param data The data to be removed.
     */
    public void remove(int data) {
        // If head node holds key, point head to next Node
        if (head != null && head.getData() == data) {
            head = head.getNext();
        } else {
            DLLNode currentNode = head;

            while (currentNode != null && currentNode.getData() != data) {
                currentNode = currentNode.getNext();
            }

            if (currentNode != null) {
                currentNode.getNext().setPrev(currentNode.getPrev());
                currentNode.getPrev().setNext(currentNode.getNext());
            }
        }
    }

    public void reverse() {
        DLLNode tempNode = null;
        DLLNode currentNode = head;

        while (currentNode != null) {
            tempNode = currentNode.getPrev();
            currentNode.setPrev(currentNode.getNext());
            currentNode.setNext(tempNode);
            currentNode = currentNode.getPrev();
        }

        if (tempNode != null) {
            head = tempNode.getPrev();
        }
    }

    public void printList() {
        DLLNode currentNode = head;

        System.out.print("DoublyLinkedList: Head -> ");

        while (currentNode != null) {
            System.out.print(currentNode.getData() + " ");
            currentNode = currentNode.getNext();
        }
        System.out.println("<- Tail");
    }
}
