import java.io.*;

public class SortFile {
    private int stdId;
    private String stdName;

    private static SortFile arr[]; //Creating an Object array
    private static SortFile bsArr[]; //To create sorted array for bubble sort
    private static SortFile ssArr[]; //To create sorted array for selection sort
    private static SortFile isArr[]; //To create sorted array for insertion sort

    public SortFile(int pStdId, String pStdName) {
        this.stdId = pStdId;
        this.stdName = pStdName;
    }

    //getters
    public int getStdId() {
        return stdId;
    }

    public String getStdName() {
        return stdName;
    }

    public static void main(String[] args) {

        String line;
        int lineNo = 0;

        String inputFile = "RandomNames7000.csv";
        FileInputStream inputStream = null;
        InputStreamReader isReader;
        BufferedReader bfReader;

        String bsOutput = "BubbleSort.csv";
        String ssOutput = "SelectionSort.csv";
        String isOutput = "InsertionSort.csv";

        FileOutputStream outputStream1 = null;
        FileOutputStream outputStream2 = null;
        FileOutputStream outputStream3 = null;
        PrintWriter pw1;
        PrintWriter pw2;
        PrintWriter pw3;

        try {
            int objCount = 7000;
            int i = 0;
            arr = new SortFile[objCount];
            bsArr = new SortFile[objCount];
            ssArr = new SortFile[objCount];
            isArr = new SortFile[objCount];

            ////read File
            inputStream = new FileInputStream(inputFile);
            isReader = new InputStreamReader(inputStream);
            bfReader = new BufferedReader(isReader);
            line = bfReader.readLine();

            ////write File
            outputStream1 = new FileOutputStream(bsOutput);
            outputStream2 = new FileOutputStream(ssOutput);
            outputStream3 = new FileOutputStream(isOutput);

            pw1 = new PrintWriter(outputStream1);
            pw2 = new PrintWriter(outputStream2);
            pw3 = new PrintWriter(outputStream3);

            while (line != null) {

                String[] splitArray = line.split(",");
                arr[i] = new SortFile(Integer.parseInt(splitArray[0]), splitArray[1]);
                line = bfReader.readLine();
                //System.out.println(arr[i].getStID());
                //System.out.println(arr[i].getStName());
                bsArr[lineNo] = arr[i];
                ssArr[lineNo] = arr[i];
                isArr[lineNo] = arr[i];
                lineNo++;

            }


            bubbleSort();
            selectionSort();
            insertionSort();


            //Creating new sorted csv
            for (int ii = 0; ii < bsArr.length; ii++) {
                //System.out.println(nArr[ii].getStdId() + " " + nArr[ii].getStdName());
                pw1.println(bsArr[ii].getStdId() + "," + bsArr[ii].getStdName());
            }
            pw1.close();

            for (int ii = 0; ii < ssArr.length; ii++) {
                //System.out.println(nArr[ii].getStdId() + " " + nArr[ii].getStdName());
                pw2.println(ssArr[ii].getStdId() + "," + ssArr[ii].getStdName());
            }
            pw2.close();

            for (int ii = 0; ii < isArr.length; ii++) {
                //System.out.println(nArr[ii].getStdId() + " " + nArr[ii].getStdName());
                pw3.println(isArr[ii].getStdId() + "," + isArr[ii].getStdName());
            }
            pw3.close();

            inputStream.close();


            System.out.println("Sorted successfully ");

        } catch (IOException e) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex2) {
                }
            }
            System.out.println("File Not Processed: " + e.getMessage());
        }


    }


    //Sorting algorithms

    public static void bubbleSort() {
        for (int pass = 0; pass < bsArr.length - 1; pass++) {
            for (int ii = 0; ii < (bsArr.length - pass) - 1; ii++) {
                if (bsArr[ii].getStdId() > bsArr[ii + 1].getStdId()) {
                    SortFile temp = bsArr[ii];
                    bsArr[ii] = bsArr[ii + 1];
                    bsArr[ii + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort() {
        for (int nn = 0; nn <= ssArr.length - 1; nn++) {
            int minIdx = nn;
            for (int jj = nn + 1; jj <= ssArr.length - 1; jj++) {
                if (ssArr[jj].getStdId() < ssArr[minIdx].getStdId()) {
                    minIdx = jj;
                }
            }
            SortFile temp = ssArr[minIdx];
            ssArr[minIdx] = ssArr[nn];
            ssArr[nn] = temp;
        }
    }

    public static void insertionSort() {
        for (int nn = 1; nn <= isArr.length - 1; nn++) {
            int ii = nn;
            while ((ii > 0) && (isArr[ii - 1].getStdId()) > (isArr[ii].getStdId())) {
                SortFile temp = isArr[ii];
                isArr[ii] = isArr[ii - 1];
                isArr[ii - 1] = temp;
                ii = ii - 1;
            }
        }
    }
}

