/**
 ** Software Technology 152
 ** Class to hold various static sort methods.
 */
class Sorts {
    // bubble sort
    public static void bubbleSort(int[] A) {
        for (int pass = 0; pass < A.length - 1; pass++) {
            for (int ii = 0; ii < (A.length - pass) - 1; ii++) {
                if (A[ii] > A[ii + 1]) {
                    int temp = A[ii];
                    A[ii] = A[ii + 1];
                    A[ii + 1] = temp;
                }
            }
        }
    }// bubbleSort()

    // selection sort
    public static void selectionSort(int[] A) {
        for (int nn = 0; nn <= A.length - 1; nn++) {
            int minIdx = nn;
            for (int jj = nn + 1; jj <= A.length - 1; jj++) {
                if (A[jj] < A[minIdx]) {
                    minIdx = jj;
                }
            }
            int temp = A[minIdx];
            A[minIdx] = A[nn];
            A[nn] = temp;
        }
    }// selectionSort()

    // insertion sort
    public static void insertionSort(int[] A) {
        for (int nn = 1; nn <= A.length - 1; nn++) {
            int ii = nn;
            while ((ii > 0) && (A[ii - 1]) > (A[ii])) {
                int temp = A[ii];
                A[ii] = A[ii - 1];
                A[ii - 1] = temp;
                ii = ii - 1;
            }
        }
    }// insertionSort()

    // mergeSort - front-end for kick-starting the recursive algorithm
    public static void mergeSort(int[] A) {
        mergeSortRecurse(A, 0, (A.length - 1));
    }// mergeSort()

    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx) {
        int midIdx;
        if (leftIdx < rightIdx) {
            midIdx = (leftIdx + rightIdx) / 2;
            mergeSortRecurse(A, leftIdx, midIdx);
            mergeSortRecurse(A, midIdx + 1, rightIdx);
            merge(A, leftIdx, midIdx, rightIdx);
        }
    }// mergeSortRecurse()

    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx) {
        int[] tempArr = new int[rightIdx - leftIdx + 1];
        int ii = leftIdx;
        int jj = midIdx + 1;
        int kk = 0;

        while ((ii <= midIdx) && (jj <= rightIdx)) {
            if (A[ii] <= A[jj]) {
                tempArr[kk] = A[ii];
                ii = ii + 1;
            } else {
                tempArr[kk] = A[jj];
                jj = jj + 1;
            }
            kk = kk + 1;
        }

        for (int i = ii; ii <= midIdx; ii++) {
            tempArr[kk] = A[ii];
            kk = kk + 1;
        }
        for (int j = jj; jj <= rightIdx; jj++) {
            tempArr[kk] = A[jj];
            kk = kk + 1;
        }
        for (int k = leftIdx; k <= rightIdx; k++) {
            A[k] = tempArr[k - leftIdx];
        }

    }// merge()
     // quickSort - front-end for kick-starting the recursive algorithm

    public static void quickSort(int[] A) {
        quickSortRecurse(A, 0, (A.length - 1));
    }// quickSort()

    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx) {
        int pivotIdx;
        int newPivotIdx;
        if (rightIdx > leftIdx) {
            // pivotIdx = (leftIdx + rightIdx) / 2;
            pivotIdx = leftIdx;

            newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            quickSortRecurse(A, leftIdx, newPivotIdx);
            quickSortRecurse(A, (newPivotIdx + 1), rightIdx);
        }
    }// quickSortRecurse()

    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx) {

        int currIdx;

        int pivotVal = A[pivotIdx];
        A[pivotIdx] = A[rightIdx];
        A[rightIdx] = pivotVal;

        currIdx = leftIdx;
        for (int ii = leftIdx; ii <= (rightIdx - 1); ii++) {
            if (A[ii] < pivotVal) {
                int temp = A[ii];
                A[ii] = A[currIdx];
                A[currIdx] = temp;
                currIdx = currIdx + 1;
            }
        }
        int newPivIdx = currIdx;
        A[rightIdx] = A[newPivIdx];
        A[newPivIdx] = pivotVal;
        return newPivIdx; // TEMP - Replace this when you implement QuickSort
    }// doPartitioning

    public static void quickSortMedian3(int[] A) {
        quickSortM3Recurse(A, 0, (A.length - 1));
    }// quickSortMedian3()

    private static void quickSortM3Recurse(int[] A, int leftIdx, int rightIdx) {
        int pivotIdx;
        int newPivotIdx;
        if (rightIdx > leftIdx) {
            pivotIdx = getMedian(A, leftIdx, (leftIdx + rightIdx) / 2, rightIdx);
            newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            quickSortRecurse(A, leftIdx, newPivotIdx);
            quickSortRecurse(A, (newPivotIdx + 1), rightIdx);
        }
    }// quickSortM3Recurse()

    private static int getMedian(int[] A, int x, int y, int z) {
        int median;
        if (((A[x] < A[y]) && (A[x] > A[z])) || ((A[x] < A[z]) && (A[x] > A[y])))
            median = x;
        else if (((A[y] < A[x]) && (A[y] > A[z])) || ((A[y] < A[z]) && (A[y] > A[x])))
            median = y;
        else
            median = z;

        return median;
    }// getMedian()

    public static void quickSortRandom(int[] A) {
        quickSortRanRecurse(A, 0, (A.length - 1));
    }// quickSortRandom()

    private static void quickSortRanRecurse(int[] A, int leftIdx, int rightIdx) {
        int pivotIdx;
        int newPivotIdx;
        if (rightIdx > leftIdx) {
            pivotIdx = getRandomIdx(A);
            newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            quickSortRecurse(A, leftIdx, newPivotIdx);
            quickSortRecurse(A, (newPivotIdx + 1), rightIdx);
        }
    }// quickSortRanRecurse()

    private static int getRandomIdx(int[] A) {
        int randomIdx = (int) (Math.random() * A.length);
        return randomIdx;
    }// getRandomIdx()

    // shell sort 
    // Reference: https://www.geeksforgeeks.org/shellsort/
    public static void shellSort(int[] A) {
        for (int gap = A.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < A.length; i += 1) {
                int temp = A[i];
                int j;
                for (j = i; j >= gap && A[j - gap] > temp; j -= gap) {
                    A[j] = A[j - gap];
                }
                A[j] = temp;
            }
        }
    }// shellSort()

    // counting sort
    // Reference: https://www.programiz.com/dsa/counting-sort
    public static void countingSort(int[] A) {

        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }

        int[] result = new int[A.length + 1];
        int[] count = new int[max + 1];

        for (int i = 0; i < max; i++) {
            count[i] = 0;
        }

        for (int i = 0; i < A.length; i++) {
            count[A[i]]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        for (int i = A.length - 1; i >= 0; i--) {
            result[count[A[i]] - 1] = A[i];
            count[A[i]]--;
        }

        for (int i = 0; i < A.length; i++) {
            A[i] = result[i];
        }

    }// countingSort()

    // radix lsd sort
    // Reference: https://www.programiz.com/dsa/radix-sort
    public static void radixLSDSort(int[] A) {
        int max = getMax(A);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingRadix(A, exp);
        }
    }// radixLSDSort()

    // counting sort for radix sort
    public static void countingRadix(int[] A, int exp) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }

        int[] result = new int[A.length + 1];
        int[] count = new int[max + 1];

        for (int i = 0; i < max; i++) {
            count[i] = 0;
        }

        for (int i = 0; i < A.length; i++) {
            count[(A[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = A.length - 1; i >= 0; i--) {
            result[count[(A[i] / exp) % 10] - 1] = A[i];
            count[(A[i] / exp) % 10]--;
        }

        for (int i = 0; i < A.length; i++) {
            A[i] = result[i];
        }
    }
    // countingRadix()

    // get the largest element
    public static int getMax(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }
        return max;
    }// getMax()

}// end Sorts calss
