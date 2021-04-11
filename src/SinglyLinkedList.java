import java.util.HashSet;

/**
 * A custom implementation of a singly linked list.
 */
public class SinglyLinkedList {
    // Head node of list.
    private SLLNode head;

    /**
     * Constructor to create an empty Linked List.
     */
    public SinglyLinkedList() {
        head = null;
    }

    /**
     * Constructor to create a Linked List with the data as the head.
     * @param data The data to store in the head node.
     */
    public SinglyLinkedList(int data) {
        head = new SLLNode(data);
    }

    /**
     * Constructor to create a Linked List from another Linked List.
     * @param list The other Linked List.
     */
    public SinglyLinkedList(SinglyLinkedList list) {
        head = list.head;
    }

    public SLLNode getHead() {
        return head;
    }

    /**
     * Checks if the specified data exists in the list.
     * @param data The data to search for.
     * @return If the data exists in the list.
     */
    public boolean contains(int data) {
        SLLNode currentNode = head;

        while (currentNode != null) {
            if (currentNode.getData() == data) {
                return true;
            }
            currentNode = currentNode.getNext();
        }

        return false;
    }

    /**
     * Counts the number of times a specific data is found in the list.
     * @param data The data to count.
     * @return The number of occurrences in the list.
     */
    public int count(int data) {
        SLLNode currentNode = head;
        int count = 0;

        while (currentNode != null) {
            if (currentNode.getData() == data) {
                count++;
            }

            currentNode = currentNode.getNext();
        }

        return count;
    }

    /**
     * Adds a Node at the front of the list.
     * @param data The data that will be added.
     */
    public void push(int data) {
        // Allocate the Node and put in data
        SLLNode newNode = new SLLNode(data);

        // Make next of new node point to head
        newNode.setNext(head);

        // Move head to point to new node
        head = newNode;
    }

    /**
     * Adds a new Node after a given node.
     * @param previousNode The node before the one to be deleted.
     * @param data The data that will be added to the list.
     */
    public void insertAfter(SLLNode previousNode, int data) {
        // Allocate the Node and put in data
        SLLNode newNode = new SLLNode(data);

        // Make next of new Node as next of previous Node
        newNode.setNext(previousNode.getNext());

        // Make next of previous node as new Node
        previousNode.setNext(newNode);
    }

    /**
     * Adds a new SLLNode after a node with the given data.
     * @param previousData The data of the previous node.
     * @param newData The data that will be added to the list.
     */
    public void insertAfter(int previousData, int newData) {
        SLLNode previousNode = head;
        SLLNode newNode = new SLLNode(newData);

        // Traverse list while previousNode is not null and data is not found
        while (previousNode != null && previousNode.getData() != previousData) {
            previousNode = previousNode.getNext();
        }

        // If previous node is not null, update pointers
        if (previousNode != null) {
            newNode.setNext(previousNode.getNext());
            previousNode.setNext(newNode);
        }
    }

    /**
     * Adds a new SLLNode before a node with the given data.
     * @param previousData The data of the previous node.
     * @param newData The data that will be added to the list.
     */
    public void insertBefore(int previousData, int newData) {
        SLLNode targetNode = head;
        SLLNode previousNode = null;
        SLLNode newNode = new SLLNode(newData);

        // Base case, new data is inserted before head
        if (head.getData() == previousData) {
            newNode.setNext(head);
            head = newNode;
        } else {
            // Traverse list while targetNode is not null and data is not found
            while (targetNode != null && targetNode.getData() != previousData) {
                previousNode = targetNode;
                targetNode = targetNode.getNext();
            }

            // If previous node is not null, update pointers
            if (targetNode != null) {
                newNode.setNext(targetNode);
                previousNode.setNext(newNode);
            }
        }
    }

    /**
     * Adds a Node at the end of the list.
     * @param data The data that will be added.
     */
    public void append(int data) {
        // Allocate the Node and put in data
        SLLNode newNode = new SLLNode(data);

        // If linked list is empty, make new node as head
        if (head == null) {
            head = newNode;
        } else {
            // Else traverse LinkedList until last node
            SLLNode lastNode = head;
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }

            // Change the next of last node
            lastNode.setNext(newNode);
        }
    }

    /**
     * Removes a node with the given data from the list.
     * @param data The data to be removed.
     */
    public void remove(int data) {
        // Store current node and previous node
        SLLNode currentNode = head;
        SLLNode previousNode = null;

        // If head node holds key, point head to next Node
        if (currentNode != null && currentNode.getData() == data) {
            head = currentNode.getNext();
        } else {
            // Search for the key to be deleted, keep track of
            // previous node as we need to change temp.next
            while (currentNode != null && currentNode.getData() != data) {
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }

            // If our current node is not null (pointing to next of last node),
            // set node before node to be deleted to next node
            if (currentNode != null) {
                previousNode.setNext(currentNode.getNext());
            }
        }
    }

    /**
     * Removes a node at a given position
     * @param position Position of element to be deleted (0 based).
     * @throws ArrayIndexOutOfBoundsException if position is greater than size of list.
     */
    public void removeAt(int position) {
        // Check if position is valid in list
        if (position >= size()) {
            String msg = String.format("Can't remove element at position %d from list of size %d", position, size());
            throw new ArrayIndexOutOfBoundsException(msg);
        } else {
            // Store pointer to previous node that will be removed
            SLLNode previousNode = head;

            // If node to be deleted is first, move head to next node
            if (position == 0) {
                head = head.getNext();
            } else {
                // Iterate through list until we reach the previous node
                for (int i = 0; i < position - 1; i++) {
                    previousNode = previousNode.getNext();
                }

                // Store node ahead of node to be deleted,
                // set node before node to be deleted to next node.
                SLLNode nextNode = previousNode.getNext().getNext();
                previousNode.setNext(nextNode);
            }
        }
    }

    /**
     * Removes all elements from the list.
     * Algorithm is optimized to O(1) time complexity
     * due to Javas automatic garbage collection.
     */
    public void removeAll() {
        head = null;
    }

    /**
     * Removes duplicate values from the list.
     */
    public void removeDuplicates() {
        HashSet<Integer> set = new HashSet<>();
        SLLNode currentNode = head;
        SLLNode previousNode = null;

        while (currentNode != null) {
            // If value is found in HashSet,
            // point previousNode's next to duplicate's next
            if (set.contains(currentNode.getData())) {
                previousNode.setNext(currentNode.getNext());
                System.out.println("Found duplicate values of " + currentNode.getData());
            } else {
                // Add unique data to HashSet, update previousNode
                set.add(currentNode.getData());
                previousNode = currentNode;
            }
            currentNode = currentNode.getNext();
        }
    }

    /**
     * Retrieves a Node at the given position if found.
     * @param position The position of the Node to retrieve.
     * @return The Node at the given position.
     * @throws ArrayIndexOutOfBoundsException if position is greater than size of list.
     */
    public SLLNode getNodeAt(int position) {
        if (position >= size()) {
            String msg = String.format("Can't access element at position %d from list of size %d", position, size());
            throw new ArrayIndexOutOfBoundsException(msg);
        }

        SLLNode currentNode = head;
        SLLNode targetNode = null;

        for (int i = 0; i <= position; i++) {
            if (i == position) {
                targetNode = currentNode;
                break;
            } else {
                currentNode = currentNode.getNext();
            }
        }

        return targetNode;
    }

    /**
     * Calculates and returns the number of elements in the list.
     * @return The number of elements in the list.
     */
    public int size() {
        // Counter for nodes
        int count = 0;
        SLLNode currentNode = head;

        // Increment count while we have valid nodes
        while (currentNode != null) {
            count++;
            currentNode = currentNode.getNext();
        }

        return count;
    }

    /**
     * Helper method for printing the values in the list.
     */
    public void printList() {
        SLLNode currentNode = head;

        System.out.print("SingleLinkedList: Head -> ");

        while (currentNode != null) {
            System.out.print(currentNode.getData() + " ");
            currentNode = currentNode.getNext();
        }

        System.out.println("<- Tail");
    }
}
