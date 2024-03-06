import java.io.*;

public class DSAHashTable {
    private DSAHashEntry[] hashArray;
    private int count;
    private static final double LF_HIGHEST = 0.7; // HIGHEST VALUE OF LOAD FACTOR
    private static final double LF_LOWEST = 0.4; // LOWEST VALUE OF LOAD FACTOR

    // ALTERNATIVE CONSTRUCTOR//
    public DSAHashTable(int tableSize) {
        int actualSize = nextPrime(tableSize); // GET THE NEXT PRIME NUMBER AS TABLE SIZE

        hashArray = new DSAHashEntry[actualSize];
        count = 0;

        for (int i = 0; i < actualSize; i++) { // INITIALIZING DSAHashEntry OBJECTS
            hashArray[i] = new DSAHashEntry();
        }
    }

    // METHOD: RETURNING VALUES FROM HASH TABLE
    public Object get(String inKey) throws Exception {
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp = false;

        while (!found && !giveUp) {
            if (hashArray[hashIdx].getState() == 0)
                giveUp = true;
            else if (hashArray[hashIdx].getKey().equals(inKey))
                found = true;
            else {
                hashIdx = (hashIdx + stepHash(origIdx) % hashArray.length);
                if (hashIdx == origIdx)
                    giveUp = true;
            }
        }
        if (!found) {
            throw new Exception("Key " + inKey + " not found");
        }
        return hashArray[hashIdx].getValue();
    }

    // METHOD: ADDING VALUES TO HASH TABLE
    public void put(String inKey, Object inValue) throws Exception {
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp = false;

        while (!found && !giveUp) {
            if (hashArray[hashIdx].getState() == 0 || hashArray[hashIdx].getState() == -1) {
                found = true;
            } else if (hashArray[hashIdx].getState() == 1) {
                if (hashArray[hashIdx].getKey().equals(inKey)) {
                    throw new IllegalArgumentException("This key is already there!");
                }
                hashIdx = (hashIdx + stepHash(origIdx)) % hashArray.length;
                if (hashIdx == origIdx) {
                    giveUp = true;
                }
            }
        }

        if (found) {
            hashArray[hashIdx] = new DSAHashEntry(inKey, inValue);
            count++;
        } else
            throw new Exception("Key " + inKey + " not found");

        if (getLoadFactor() > LF_HIGHEST) {
            scaleUp(hashArray.length);
        }

    }

    // METHOD: REMOVING VALUES FROM HASH TABLE
    public void remove(String inKey) throws Exception {
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp = false;

        while (!found && !giveUp) {
            if (hashArray[hashIdx].getState() == 0)
                giveUp = true;
            else if (hashArray[hashIdx].getKey().equals(inKey))
                found = true;
            else {
                hashIdx = (hashIdx + stepHash(origIdx) % hashArray.length);
                if (hashIdx == origIdx)
                    giveUp = true;
            }
        }
        if (found) {
            hashArray[hashIdx].setValue(null);
            hashArray[hashIdx].setKey("");
            hashArray[hashIdx].setState(-1);
            count--;
        } else
            throw new Exception("Not removed! Key could not be found");

        if (getLoadFactor() < LF_LOWEST) {
            scaleDown(hashArray.length);
        }

    }

    // METHOD: HASH
    private int hash(String key) {
        int hashIdx = 0;
        for (int ii = 0; ii < key.length(); ii++) {
            hashIdx = (31 * hashIdx) + key.charAt(ii);
        }
        return Math.abs(hashIdx % hashArray.length);
    }

    // METHOD: SECOND HASH
    private int stepHash(int hashKey) {
        return (5 - (hashKey % 5));
    }

    // METHOD: RETURNING THE NEXT PRIME NUMBER
    private int nextPrime(int startVal) {
        int primeVal;
        int ii;
        double rootVal;
        boolean isPrime;
        if (startVal % 2 == 0) {
            primeVal = startVal - 1;
        } else {
            primeVal = startVal;
        }

        isPrime = false;
        do {
            primeVal = primeVal + 2;
            ii = 3;
            isPrime = true;
            rootVal = Math.sqrt(primeVal);
            do {
                if (primeVal % ii == 0) {
                    isPrime = false;
                } else
                    ii = ii + 2;

            } while (ii <= rootVal && isPrime);
        } while (!isPrime);

        return primeVal;
    }

    // METHOD: RETURNING THE PREVIOUS PRIME NUMBER
    private int prevPrime(int startVal) {
        int primeVal;
        int ii;
        double rootVal;
        boolean isPrime;
        if (startVal % 2 == 0) {
            primeVal = startVal - 1;
        } else {
            primeVal = startVal;
        }

        isPrime = false;
        do {
            primeVal = primeVal - 2;
            ii = 3;
            isPrime = true;
            rootVal = Math.sqrt(primeVal);
            do {
                if (primeVal % ii == 0) {
                    isPrime = false;
                } else
                    ii = ii - 2;

            } while (ii >= rootVal && isPrime);
        } while (!isPrime);

        return primeVal;
    }

    // METHOD: CLACULATE THE LOAD FACTOR
    private double getLoadFactor() {
        double lf = ((double) count / (double) hashArray.length);
        return lf;
    }

    // METHOD: RESIZING THE HASH TABLE (SCALE UP)
    private void scaleUp(int size) {
        int newSize = nextPrime(size);
        int newCount = 0;

        DSAHashEntry[] oldHashArray = new DSAHashEntry[hashArray.length];
        for (int i = 0; i < hashArray.length; i++) {
            oldHashArray[i] = hashArray[i];
        }
        hashArray = new DSAHashEntry[newSize];
        for (int i = 0; i < newSize; i++) {
            hashArray[i] = new DSAHashEntry();
        }
        for (int i = 0; i < oldHashArray.length; i++) {
            if (oldHashArray[i].getState() == 1) {
                try {
                    put(oldHashArray[i].getKey(), oldHashArray[i].getValue());
                    newCount++;
                } catch (Exception e) {
                    System.out.println("Error while array resizing");
                }
            }
        }
        count = newCount;

    }

    // METHOD: RESIZING THE HASH TABLE (SCALE DOWN)
    private void scaleDown(int size) {
        int newSize = prevPrime(size);
        int newCount = 0;

        DSAHashEntry[] oldHashArray = new DSAHashEntry[hashArray.length];
        for (int i = 0; i < hashArray.length; i++) {
            oldHashArray[i] = hashArray[i];
        }
        hashArray = new DSAHashEntry[newSize];
        for (int i = 0; i < newSize; i++) {
            hashArray[i] = new DSAHashEntry();
        }
        for (int i = 0; i < oldHashArray.length; i++) {
            if (oldHashArray[i].getState() == 1) {
                try {
                    put(oldHashArray[i].getKey(), oldHashArray[i].getValue());
                newCount++;
                } catch (Exception e) {
                    System.out.println("Error while array resizing");
                }               
            }
        }
        count = newCount;

    }

    // METHOD: DISPLAYING HASH VLUES (& LOAD FACTOR, FINALE HASH TABLE SIZE)
    public void display() {
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i].getState() == 1) {
                System.out.println(hashArray[i].getKey() + "," + hashArray[i].getValue());
            }
            // System.out.println(hashArray[i].getState());
        }
        System.out.print("\n");
        System.out.println("Load Factor = " + getLoadFactor());
        System.out.println("Final table size = " + hashArray.length);
    }

    // METHOD: LOADING DATA FROM A FILE TO HASH TABLE
    public void loadFile(String pFile) {
        String line;
        FileInputStream stream = null;
        InputStreamReader reader;
        BufferedReader bfrReader;
        int duplicates = 0;

        try {
            stream = new FileInputStream(pFile);
            reader = new InputStreamReader(stream);
            bfrReader = new BufferedReader(reader);

            while ((line = bfrReader.readLine()) != null) {
                String[] splitArray = line.split(",");
                try {
                    put(splitArray[0], splitArray[1]);
                } catch (IllegalArgumentException e) {
                    duplicates++;
                } catch (Exception e){
                    System.out.println("Error while file passing");
                }

            }
            stream.close();
            System.out.println("File Loaded.");
        } catch (IOException e) {
            System.out.println("ERROR While File loading: " + e.getMessage());
        }

        System.out.println(duplicates + " duplicate keys found!");
    }

    // METHOD: SAVING HASH TABLE TO A CSV FILE
    public void saveFile(String output) {
        FileOutputStream outputStream = null;
        PrintWriter pw;

        try {
            outputStream = new FileOutputStream(output);
            pw = new PrintWriter(outputStream);

            for (int i = 0; i < hashArray.length; i++) {
                if (hashArray[i].getState() == 1) {
                    pw.println(hashArray[i].getKey() + "," + hashArray[i].getValue());
                }
            }
            pw.close();
            System.out.println("File saved.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
