import java.io.*;

public class DSAHeap {
    // PRIVATE CLASS DSAHeapEntry
    private class DSAHeapEntry {
        // DSAHeapEntry CLASS FIELDS
        private int priority;
        private Object value;

        // ALTERNATIVE CONSTRUCTOR
        public DSAHeapEntry(int inPriority, Object inValue) {
            priority = inPriority;
            value = inValue;
        }

        // GETTERS & SETTERS
        public int getPriority() {
            return priority;
        }

        public void setPriority(int inPriority) {
            priority = inPriority;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object inValue) {
            value = inValue;
        }
    }

    // DSAHeap CLASS FIELDS
    private DSAHeapEntry[] heap;
    private int count;
    private Object[] arrToSort;

    // ALTERNATIVE CONSTRUCTOR
    public DSAHeap(int size) {
        heap = new DSAHeapEntry[size];
        count = 0;
    }

    // METHOD: ADDING VALUES TO HEAP
    public void add(int inPriority, Object inValue) {
        heap[count] = new DSAHeapEntry(inPriority, inValue);
        trickleUp(count);
        count++;
    }

    // METHOD: REMOVING ROOT VALUE FROM HEAP
    public void remove() {
        count--;
        heap[0] = heap[count];
        heap[count] = null;
        trickleDown(0);
    }

    // METHOD: DISPLAYING THE HEAP
    public void display() {
        for (DSAHeapEntry entry : heap) {
            if (entry != null) {
                System.out.println(entry.getPriority() + " " + entry.getValue());
            }
        }
    }

    // METHOD: REARRANGING THE HEAP - UP
    private void trickleUp(int curIdx) {
        int parentIdx = (curIdx - 1) / 2;
        DSAHeapEntry temp;
        if (curIdx > 0) {
            if (heap[curIdx].getPriority() > heap[parentIdx].getPriority()) {
                temp = heap[parentIdx];
                heap[parentIdx] = heap[curIdx];
                heap[curIdx] = temp;
                trickleUp(parentIdx);
            }
        }
    }

    // METHOD: REARRANGING THE HEAP - DOWN
    private void trickleDown(int curIdx) {
        int lChildIdx = (curIdx * 2) + 1;
        int rChildIdx = (curIdx * 2) + 2;
        int largeIdx;
        DSAHeapEntry temp;

        if (lChildIdx < count) {
            largeIdx = lChildIdx;
            if (rChildIdx < count) {
                if (heap[lChildIdx].getPriority() < heap[rChildIdx].getPriority()) {
                    largeIdx = rChildIdx;
                }
            }
            if (heap[largeIdx].getPriority() > heap[curIdx].getPriority()) {
                temp = heap[largeIdx];
                heap[largeIdx] = heap[curIdx];
                heap[curIdx] = temp;
                trickleDown(largeIdx);
            }
        }
    }

    // METHOD: CONVERT ARRAY OBJECTS INTO HeapEntry OBJECTS
    private DSAHeapEntry[] heapyfy(Object[] inArr) {
        count = inArr.length;
        heap = new DSAHeapEntry[count];
        int i = 0;

        for (Object object : inArr) {
            String[] splitArray = object.toString().split(",");
            heap[i] = new DSAHeapEntry(Integer.parseInt(splitArray[0]), splitArray[1]);
            i++;
        }

        for (int ii = (count / 2) - 1; ii >= 0; ii--) {
            trickleDown(ii);
        }

        System.out.println("File Passed.");
        return heap;
    }

    // METHOD: SORTING THE HEAP (HEAP SORT)
    public DSAHeapEntry[] heapSort() {
        DSAHeapEntry temp;
        heapyfy(arrToSort);

        for (int i = count - 1; i >= 1; i--) {
            temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;

            count = i;
            trickleDown(0);
        }

        count = arrToSort.length;
        System.out.println("File Sorted.");
        return heap;
    }

    // METHOD: READ A CSV FILE AND PUT THEIR DATA INTO AN ARRAY
    public void readCSV(String pFile) {
        String line;
        FileInputStream stream = null;
        InputStreamReader reader;
        BufferedReader bfrReader;
        arrToSort = new Object[7000];
        int i = 0;

        try {
            stream = new FileInputStream(pFile);
            reader = new InputStreamReader(stream);
            bfrReader = new BufferedReader(reader);

            while ((line = bfrReader.readLine()) != null) {
                // String[] splitArray = line.split(",");
                try {
                    // add(Integer.parseInt(splitArray[0]), splitArray[1]);
                    arrToSort[i] = line;
                    i++;
                } catch (Exception e) {
                    System.out.println("Error while file passing");
                }

            }
            stream.close();
            System.out.println("File Loaded.");
        } catch (IOException e) {
            System.out.println("ERROR While File loading: " + e.getMessage());
        }
    }

}
