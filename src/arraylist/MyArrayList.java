package arraylist;

import java.util.Arrays;

/**
 * A simple implementation of an ArrayList.
 * This list uses primitive ints for simplicity.
 */
public class MyArrayList {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private int[] elements;

    /**
     * Default constructor to instantiate an array of DEFAULT_CAPACITY elements.
     */
    public MyArrayList() {
        this.elements = new int[DEFAULT_CAPACITY];
    }

    /**
     * Constructor to instantiate an array of a specific size.
     * @param initialCapacity The initial capacity of the arraylist.
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new int[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elements = new int[] {};
        } else {
            throw new IllegalArgumentException("Initial capacity must be non-negative!");
        }
    }

    /**
     * Constructor to instantiate an array from a specified array.
     * @param arr The array to copy data from.
     */
    public MyArrayList(int[] arr, int size) {
        this.elements = Arrays.copyOf(arr, arr.length);
        this.size = size;
    }

    /**
     * Copy Constructor to instantiate an array from from another MyArrayList.
     * @param myArrayList The list to copy data from.
     */
    public MyArrayList(MyArrayList myArrayList) {
        this.elements = Arrays.copyOf(myArrayList.elements, myArrayList.size);
        this.size = myArrayList.size;
    }

    /**
     * Adds the specified element to the internal array.
     * The array may grow in size if the internal array is at capacity.
     * @param data The element to add.
     */
    public void add(int data) {
        ensureCapacity();
        elements[size++] = data;
    }

    /**
     * Adds the specified element to the internal array at the specified location.
     * The array may grow in size if the internal array is at capcity.
     * @param index The index to insert data at.
     * @param data The element to add.
     */
    public void addAt(int index, int data) {
        if (index >= 0 && index < elements.length) {
            ensureCapacity();
            elements[index] = data;
            size++;
        } else {
            throw new IndexOutOfBoundsException(
                    String.format("Can't add element at position %d to array of size %d", index, size));
        }
    }

    /**
     * Removes the element at the specified index from the list.
     * @param index The index to remove data at.
     */
    public void removeAt(int index) {
        if (index >= 0 && index < size) {
            elements[index] = 0;
            size--;
        } else {
            throw new IndexOutOfBoundsException(
                    String.format("Can't remove element at position %d to array of size %d", index, size));
        }
    }

    /**
     * Removes all elements from the list by setting them to their default value.
     */
    public void removeAll() {
        for (int i = 0; i < size; i++) {
            elements[i] = 0;
        }
    }

    /**
     * Returns the element found at the specified index.
     * @param index The index to retrieve data from.
     * @return The element at the specified index.
     */
    public int get(int index) {
        if (index >= 0 && index < size) {
            return elements[index];
        } else {
            throw new IndexOutOfBoundsException(
                    String.format("Can't get element at position %d from array of size %d", index, size));
        }
    }

    /**
     * Updates the element at the specified index.
     * @param index The index to update data at.
     * @param data The new data that will replace the previous data.
     */
    public void set(int index, int data) {
        if (index >= 0 && index < size) {
            elements[index] = data;
        } else {
            throw new IndexOutOfBoundsException(
                    String.format("Can't update element at position %d from array of size %d", index, size));
        }
    }

    /**
     * Checks if the specified element exists in the list.
     * @param data The element to search for.
     * @return Whether or not the element exists in the list.
     */
    public boolean contains(int data) {
        for (int element : elements) {
            if (element == data) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the first index that the specified data
     * is found at or -1 if it is not found.
     * @param data The element to search for.
     * @return The first index that the element was found at.
     */
    public int indexOf(int data) {
        int location = -1;

        for (int i = 0; i < size; i++) {
            if (elements[i] == data) {
                location = i;
                break;
            }
        }

        return location;
    }

    /**
     * The number of elements in the list.
     * @return The number of elements in the list.
     */
    public int size() {
        return this.size;
    }

    /**
     * The number of elements the list can currently store without growing.
     * @return The number of elements the list can currently store without growing.
     */
    public int capacity() { return elements.length; }

    /**
     * Whether the list is empty or not.
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks to see if we need to grow the internal array
     * If so, a new duplicated larger array is assigned to the internal array.
     */
    public void ensureCapacity() {
        if (size == elements.length) {
            elements = grow();
        }
    }

    /**
     * Trims the capacity of the internal array to the current number of elements.
     */
    public void trimToSize() {
        int newCapacity = size;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    /**
     * Creates a new array containing the same elements but double in capacity.
     * @return A new array double the size of the original.
     */
    private int[] grow() {
        int newCapacity = elements.length * 2;
        return Arrays.copyOf(elements, newCapacity);
    }

    /**
     * Creates and returns a new MyArrayList with all elements in reverse order.
     * @return A new reversed list.
     */
    public MyArrayList reverse() {
        int last = size - 1;
        int[] reversed = new int[elements.length];

        for (int i = 0; i < size; i++) {
            reversed[i] = elements[last--];
        }

        return new MyArrayList(reversed, size);
    }

    /**
     * Reverse the current list in place without additional space.
     */
    public void reverseInPlace() {
        int start = 0;
        int end = size - 1;
        int temp;

        while (start < end) {
            temp = elements[end];
            elements[end] = elements[start];
            elements[start] = temp;
            start++;
            end--;
        }
    }

    /**
     * Convenience method to print the contents of the list.
     */
    public void printList() {
        System.out.print("MyArrayList: [");

        for (int i = 0; i < size; i++) {
            System.out.print(elements[i] + " ");
        }

        System.out.println("]");
    }
}
