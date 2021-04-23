package arraylist;

public class IntArrayAlgorithms {
    /**
     * Recursive method to perform a binary search on a given array.
     * @param arr The array to search through.
     * @param left The left edge of the array.
     * @param right The right edge of the array.
     * @param element The element to search for.
     * @return The index that the element was found at, or -1 if it was not found.
     */
    public Integer binarySearch(int[] arr, int left, int right, int element) {
        if (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == element) {
                return mid;
            } else if (element < arr[mid]) {
                return binarySearch(arr, left, mid - 1, element);
            } else {
                return binarySearch(arr, mid + 1, right, element);
            }
        }

        return -1;
    }

    /**
     * Iterative method to perform a binary search on a given array.
     * @param arr The array to search through.
     * @param element The element to search for.
     * @return The index that the element was found at, or -1 if it was not found.
     */
    public int binarySearch(int[] arr, int element) {
        int left = 0;
        int right= arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == element) {
                return mid;
            } else if (element > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
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
    public int jumpSearch(int[] arr, int element) {
        int blockSize = (int)Math.floor(Math.sqrt(arr.length));
        int currentLastIndex = blockSize - 1;

        // Jump to next block as long as [element > currentLastIndex]
        // and the array end has not been reached
        while (currentLastIndex < arr.length && element > arr[currentLastIndex]) {
            currentLastIndex += blockSize;
        }

        // Find accurate position of target element using linear search
        for (int i = currentLastIndex - blockSize + 1; i <= currentLastIndex && i < arr.length; i++) {
            if (element == arr[i]) {
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
    public int interpolationSearch(int[] arr, int element) {
        int highEnd = arr.length - 1;
        int lowEnd = 0;

        while (element >= arr[lowEnd] && element <= arr[highEnd] && lowEnd <= highEnd) {
            int probe = lowEnd + ((highEnd - lowEnd) * (element - arr[lowEnd])) / (arr[highEnd] - arr[lowEnd]);

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

            if (arr[probe] < element) {
                lowEnd = probe + 1;
            } else {
                highEnd = probe - 1;
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
    public int exponentialSearch(int[] arr, int element) {
        int bound = 1;

        while (bound < arr.length && arr[bound] < element) {
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
    public int ternarySearch(int[] arr, int start, int end, int data) {
        if (start <= end) {
            // first boundary (add 1/3 of length to start)
            int mid1 = start + (end - start) / 3;
            int mid2 = start + 2 * (end - start) / 3;

            if (arr[mid1] == data) {
                return mid1;
            } else if (arr[mid2] == data) {
                return mid2;
            } else if (data < arr[mid1]) {
                return ternarySearch(arr, start, mid1 - 1, data);
            } else if (data > arr[mid2]) {
                return ternarySearch(arr, mid2 + 1, end, data);
            } else {
                return ternarySearch(arr, mid1, mid2, data);
            }
        } else {
            return -1;
        }
    }
}
