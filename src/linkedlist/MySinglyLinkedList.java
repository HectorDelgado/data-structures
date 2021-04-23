package linkedlist;

import java.util.HashSet;

/**
 * A custom implementation of a singly linked list.
 */
public class MySinglyLinkedList<T extends Comparable<T>> {
    protected class SLLNode {
        T data;
        SLLNode next;

        public SLLNode() {
            data = null;
            next = null;
        }

        public SLLNode(T data) {
            this.data = data;
            next = null;
        }
    }

    private SLLNode head;

    /**
     * Constructor to create an empty Linked List.
     */
    public MySinglyLinkedList() {
        head = null;
    }

    /**
     * Constructor to create a Linked List with the data as the head.
     * @param data The data to store in the head node.
     */
    public MySinglyLinkedList(T data) {
        head = new SLLNode(data);
    }

    /**
     * Constructor to create a Linked List from another Linked List.
     * @param list The other Linked List.
     */
    public MySinglyLinkedList(MySinglyLinkedList<T> list) {
        // TODO perform deep copy
    }

    /**
     * Checks if the specified data exists in the list.
     * @param data The data to search for.
     * @return True if the data exists in the list.
     */
    public boolean contains(T data) {
        SLLNode current = head;

        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    /**
     * Counts the number of times a specific data is found in the list.
     * @param data The data to count.
     * @return The number of occurrences in the list.
     */
    public int count(T data) {
        SLLNode current = head;
        int count = 0;

        while (current != null) {
            if (current.data == data) {
                count++;
            }
            current = current.next;
        }

        return count;
    }

    /**
     * Adds a Node at the front of the list.
     * @param data The data that will be added.
     */
    public void push(T data) {
        SLLNode newNode = new SLLNode(data);
        newNode.next = head;
        head = newNode;
    }

    /**
     * Adds a new Node after a given node.
     * @param previousNode The node before the one to be deleted.
     * @param data The data that will be added to the list.
     */
    public void insertAfter(SLLNode previousNode, T data) {
        // Allocate the Node and put in data
        SLLNode newNode = new SLLNode(data);

        // Make next of new Node as next of previous Node
        newNode.next = previousNode.next;

        // Make next of previous node as new Node
        previousNode.next = newNode;
    }

    /**
     * Adds a new SLLNode after a node with the given data.
     * @param previousData The data of the previous node.
     * @param newData The data that will be added to the list.
     */
    public void insertAfter(T previousData, T newData) {
        SLLNode previousNode = head;
        SLLNode newNode = new SLLNode(newData);

        // Traverse list while previousNode is not null and data is not found
        while (previousNode != null && previousNode.data != previousData) {
            previousNode = previousNode.next;
        }

        // If previous node is not null, update pointers
        if (previousNode != null) {
            newNode.next = previousNode.next;
            previousNode.next = newNode;
        }
    }

    /**
     * Adds a new SLLNode before a node with the given data.
     * @param previousData The data of the previous node.
     * @param newData The data that will be added to the list.
     */
    public void insertBefore(T previousData, T newData) {
        SLLNode targetNode = head;
        SLLNode previousNode = null;
        SLLNode newNode = new SLLNode(newData);

        // Base case, new data is inserted before head
        if (head.data.equals(newData)) {
            newNode.next = head;
            head = newNode;
        } else {
            // Traverse list while targetNode is not null and data is not found
            while (targetNode != null && targetNode.data != previousData) {
                previousNode = targetNode;
                targetNode = targetNode.next;
            }

            // If previous node is not null, update pointers
            if (targetNode != null) {
                newNode.next = targetNode;
                previousNode.next = newNode;
            }
        }
    }

    /**
     * Adds a Node at the end of the list.
     * @param data The data that will be added.
     */
    public void append(T data) {
        // Allocate the Node and put in data
        SLLNode newNode = new SLLNode(data);

        // If linked list is empty, make new node as head
        if (head == null) {
            head = newNode;
        } else {
            // Else traverse LinkedList until last node
            SLLNode lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }

            // Change the next of last node
            lastNode.next = newNode;
        }
    }

    /**
     * Removes a node with the given data from the list.
     * @param data The data to be removed.
     */
    public void remove(T data) {
        // Store current node and previous node
        SLLNode currentNode = head;
        SLLNode previousNode = null;

        // If head node holds key, point head to next Node
        if (currentNode != null && currentNode.data.equals(data)) {
            head = currentNode.next;
        } else {
            // Search for the key to be deleted, keep track of
            // previous node as we need to change temp.next
            while (currentNode != null && !currentNode.data.equals(data)) {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }

            // If our current node is not null (pointing to next of last node),
            // set node before node to be deleted to next node
            if (currentNode != null) {
                previousNode.next = currentNode.next;
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
                head = head.next;
            } else {
                // Iterate through list until we reach the previous node
                for (int i = 0; i < position - 1; i++) {
                    previousNode = previousNode.next;
                }

                // Store node ahead of node to be deleted,
                // set node before node to be deleted to next node.
                SLLNode nextNode = previousNode.next.next;
                previousNode.next = nextNode;
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
            currentNode = currentNode.next;
        }

        return count;
    }

    /**
     * Removes duplicate values from the list.
     */
    public void removeDuplicates() {
        HashSet<T> set = new HashSet<>();
        SLLNode currentNode = head;
        SLLNode previousNode = null;

        while (currentNode != null) {
            // If value is found in HashSet,
            // point previousNode's next to duplicate's next
            if (set.contains(currentNode.data)) {
                previousNode.next = currentNode.next;
            } else {
                // Add unique data to HashSet, update previousNode
                set.add(currentNode.data);
                previousNode = currentNode;
            }
            currentNode = currentNode.next;
        }
    }

    /**
     * Retrieves a Node at the given position if found.
     * @param position The position of the Node to retrieve.
     * @return The item at the given position.
     * @throws ArrayIndexOutOfBoundsException if position is greater than size of list.
     */
    public T getAt(int position) {
        if (position >= size()) {
            String msg = String.format("Can't access element at position %d from list of size %d", position, size());
            throw new ArrayIndexOutOfBoundsException(msg);
        }

        SLLNode currentNode = head;
        T targetNode = null;

        for (int i = 0; i <= position; i++) {
            if (i == position) {
                targetNode = currentNode.data;
                break;
            } else {
                currentNode = currentNode.next;
            }
        }

        return targetNode;
    }

    /**
     * Helper method for printing the values in the list.
     */
    public void printList() {
        SLLNode currentNode = head;

        System.out.print("SingleLinkedList: Head -> ");

        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }

        System.out.println("<- Tail");
    }

}
