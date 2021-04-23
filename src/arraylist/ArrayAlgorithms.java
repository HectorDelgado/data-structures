package arraylist;

public class ArrayAlgorithms<T extends Comparable<T>> {
    /**
     * Recursive method to perform a binary search on an array.
     * @param arr The array to search through.
     * @param start The starting index of the array.
     * @param end The last index of the array.
     * @param element The element to search for.
     * @return The index that the element was found at, or -1.
     */
    public int binarySearch(T[] arr, int start, int end, T element) {
        if (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid].equals(element)) {
                return mid;
            } else if (element.compareTo(arr[mid]) < 0) {
                return binarySearch(arr, start, mid - 1, element);
            } else {
                return binarySearch(arr, mid + 1, end, element);
            }
        }

        return -1;
    }

    /**
     * Iterative method to perform a binary search on an array.
     * @param arr The array to search through.
     * @param element The element to search for.
     * @return The index that the element was found at, or -1.
     */
    public int binarySearch(T[] arr, T element) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid].equals(element)) {
                return mid;
            } else if (element.compareTo(arr[mid]) < 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    /**
     * Performs an iterative jump search on a given array.
     * @param arr The array to search through.
     * @param element The element to search for.
     * @return The index that the element was found at, or -1 if it was not found.
     */
    public int jumpSearch(T[] arr, T element) {
        int blockSize = (int)Math.floor(Math.sqrt(arr.length));
        int currentLastIndex = blockSize - 1;

        // Jump to next block as long as [element > arr[currentLastIndex]]
        // and the array end has not been reached
        while (currentLastIndex < arr.length && element.compareTo(arr[currentLastIndex]) > 0) {
            currentLastIndex += blockSize;
        }

        for (int i = currentLastIndex - blockSize + 1; i < currentLastIndex && i < arr.length; i++) {
            if (element.equals(arr[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Performs an interpolation search on a given array.
     * @param arr The array to search through.
     * @param element The element to search for.
     * @return The index that the element was found at, or -1 if it was not found.
     */
    public int interpolationSearch(T[] arr, T element) {
        int highEnd = arr.length - 1;
        int lowEnd = 0;

        while (element.compareTo(arr[lowEnd]) >= 0 && element.compareTo(arr[highEnd]) <= 0 && lowEnd <= highEnd) {
            int probe = lowEnd + ((highEnd - lowEnd) * (element.compareTo(arr[lowEnd])) / (element.compareTo(arr[lowEnd])));

            if (highEnd == lowEnd) {
                if (arr[lowEnd] == element) {
                    return lowEnd;
                } else {
                    return -1;
                }
            }

            if (arr[probe] == element) {
                return probe;
            }

            if (element.compareTo(arr[probe]) < 0) {
                highEnd = probe - 1;
            } else {
                lowEnd = probe + 1;
            }
        }

        return -1;
    }

    /**
     * Performs an exponential search on a given array.
     * @param arr The array to search through.
     * @param element The element to search for.
     * @return The index that the element was found at, or -1 if it was not found.
     */
    public int exponentialSearch(T[] arr, T element) {
        int bound = 1;

        while (bound < arr.length && element.compareTo(arr[bound]) > 0) {
            bound *= 2;
        }

        int left = bound / 2;
        int right = Math.min(bound, arr.length - 1);

        return binarySearch(arr, left, right, element);
    }

    /**
     * Performs a ternary search on a given array.
     * @param arr The array to search through.
     * @param start The starting index of the array to begin searching at.
     * @param end The ending index of the array to end searching at.
     * @param data The element to search for.
     * @return The index that the element was found at, or -1 if it was not found.
     */
    public int ternarySearch(T[] arr, int start, int end, T data) {
        if (start <= end) {
            // first boundary (add 1/3 of length to start)
            int mid1 = start + (end - start) / 3;
            int mid2 = start + 2 * (end - start) / 3;

            if (data.equals(arr[mid1])) {
                return mid1;
            } else if (data.equals(arr[mid2])) {
                return mid2;
            } else if (data.compareTo(arr[mid1]) < 0) {
                return ternarySearch(arr, start, mid1 - 1, data);
            } else if (data.compareTo(arr[mid2]) > 0) {
                return ternarySearch(arr, mid2 + 1, end, data);
            } else {
                return ternarySearch(arr, mid1, mid2, data);
            }
        } else {
            return -1;
        }
    }

    /**
     * Performs a selection sort on the given array.
     * @param arr The array to sort.
     */
    public void selectionSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // Find minimum element in unsorted array
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // Swap found minimum element with first element
            T temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Performs a bubble sort on the given array.
     * @param arr The array to sort.
     */
    public void bubbleSort(T[] arr) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i + 1].compareTo(arr[i]) < 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    sorted = false;
                }
            }
        }
    }
}
