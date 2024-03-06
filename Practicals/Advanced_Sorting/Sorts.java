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

}// end Sorts calss
