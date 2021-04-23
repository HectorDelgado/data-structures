package linkedlist;

public class MyDoubleLinkedList<T extends Comparable<T>> extends MySinglyLinkedList<T> {
    protected class DLLNode {
        T data;
        DLLNode prev;
        DLLNode next;

        public DLLNode() {
            this.data = null;
            this.prev = null;
            this.next = null;
        }

        public DLLNode(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }
    private DLLNode head;

    public MyDoubleLinkedList() {
        head = null;
    }

    public MyDoubleLinkedList(T data) {
        head = new DLLNode(data);
    }

    public MyDoubleLinkedList(MyDoubleLinkedList<T> list) {
        // TODO copy constructor
    }

    /**
     * Adds a node at the front of the list.
     * @param data The ata that will be added.
     */
    @Override
    public void push(T data) {
        // Allocate the node and put in data
        DLLNode newNode = new DLLNode(data);

        // Make next of new node as head, prev as null
        newNode.next = head;
        newNode.prev = null;

        // Update prev of old head to newNode
        if (head != null) {
            head.prev = newNode;
        }

        // Move head to point tp newNode
        head = newNode;
    }

    public void insertAfter(DLLNode previousNode, T data) {
        if (previousNode == null) {
            throw new IllegalArgumentException("Previous node cannot be null");
        }

        // Allocate the node and put in data
        DLLNode newNode = new DLLNode(data);

        // Make next of newNode as next of previousNode
        newNode.next = previousNode.next;

        // Make next of previousNode as newNode
        previousNode.next = newNode;

        // Make prev of newNode as previousNode
        newNode.prev = previousNode;

        // Update prev of newNodes next node
        if (newNode.next != null) {
            newNode.next.prev = newNode;
        }
    }

    public void insertAfter(T previousData, T newData) {
        // TODO finish code
    }

    public void insertBefore(DLLNode nextNode, int data) {
        // TODO finish code
    }

    public void insertBefore(T previousData, T newData) {
        // TODO finish code
    }

    public void append(T data) {
        // Allocate the node and put in data
        DLLNode newNode = new DLLNode(data);

        // If linked list if empty, make newNode as head
        if (head == null) {
            head = newNode;
        } else {
            // Else traverse list until last node
            DLLNode lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }

            // Change the next of last node
            lastNode.next = newNode;

            // Make prev of newNode as lastNode
            newNode.prev = lastNode;
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
                head = head.next;
            }

            // Change the prev of deletedNode next node if its not last node
            if (deletedNode.next != null) {
                deletedNode.next.prev = deletedNode.prev;
            }

            // Change the next of deletedNode previous node if its not first node
            if (deletedNode.prev != null) {
                deletedNode.prev.next = deletedNode.next;
            }
        }
    }

    /**
     * Removes a node with the given data from the list.
     * @param data The data to be removed.
     */
    public void remove(T data) {
        // If head node holds key, point head to next Node
        if (head != null && head.data == data) {
            head = head.next;
        } else {
            DLLNode currentNode = head;

            while (currentNode != null && currentNode.data != data) {
                currentNode = currentNode.next;
            }

            if (currentNode != null) {
                currentNode.next.prev = currentNode.prev;
                currentNode.prev.next = currentNode.next;
            }
        }
    }

    public void reverse() {
        DLLNode tempNode = null;
        DLLNode currentNode = head;

        while (currentNode != null) {
            tempNode = currentNode.prev;
            currentNode.prev = currentNode.next;
            currentNode.next = tempNode;
            currentNode = currentNode.prev;
        }

        if (tempNode != null) {
            head = tempNode.prev;
        }
    }

    public void printList() {
        DLLNode currentNode = head;

        System.out.print("DoublyLinkedList: Head -> ");

        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println("<- Tail");
    }
}
