import java.io.*;
import java.util.*;

public class DSAHashTable {

    HashMap<String, Object> hashTable = new HashMap<String, Object>();

    // METHOD: RETURNING VALUES FROM HASH TABLE
    public Object getValue(String inKey) throws Exception {
        if (hashTable.containsKey(inKey)) {
            return hashTable.get(inKey);
        } else
            throw new Exception("Key could not be found");

    }

    // METHOD: ADDING VALUES TO HASH TABLE
    public void putValue(String inKey, Object inValue) throws Exception {

        hashTable.put(inKey, inValue);

    }

    // METHOD: REMOVING VALUES FROM HASH TABLE
    public void removeValue(String inKey) throws Exception {

        if (hashTable.containsKey(inKey)) {
            hashTable.remove(inKey);
        } else
            throw new Exception("Not removed! Key could not be found");

    }

    // METHOD: DISPLAYING HASH VLUES (& LOAD FACTOR, FINALE HASH TABLE SIZE)
    public void display() {
        Iterator<String> iterator1 = hashTable.keySet().iterator();
        Iterator<Object> iterator2 = hashTable.values().iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next() + " " + iterator2.next());
        }
        System.out.println();

    }

    // METHOD: LOADING DATA FROM A FILE TO HASH TABLE
    public void loadFile(String pFile) {
        String line;
        FileInputStream stream = null;
        InputStreamReader reader;
        BufferedReader bfrReader;

        try {
            stream = new FileInputStream(pFile);
            reader = new InputStreamReader(stream);
            bfrReader = new BufferedReader(reader);

            while ((line = bfrReader.readLine()) != null) {
                String[] splitArray = line.split(",");
                try {
                    putValue(splitArray[0], splitArray[1]);
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

    // METHOD: SAVING HASH TABLE TO A CSV FILE
    public void saveFile(String output) {
        FileOutputStream outputStream = null;
        PrintWriter pw;

        try {
            outputStream = new FileOutputStream(output);
            pw = new PrintWriter(outputStream);
            Iterator<String> iterator1 = hashTable.keySet().iterator();
            Iterator<Object> iterator2 = hashTable.values().iterator();
            while (iterator1.hasNext()) {
                pw.println(iterator1.next() + " " + iterator2.next());
            }
            pw.close();
            System.out.println("File saved.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
