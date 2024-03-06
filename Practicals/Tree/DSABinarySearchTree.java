import java.io.*;
import java.util.*;

public class DSABinarySearchTree implements Serializable {
    private class DSATreeNode implements Serializable {
        private String m_key;
        private Object m_value;
        private DSATreeNode m_leftChild;
        private DSATreeNode m_rightChild;

        public DSATreeNode(String inKey, Object inVal) {
            if (inKey == null)
                throw new IllegalArgumentException("Key cannot be null");
            m_key = inKey;
            m_value = inVal;
            m_rightChild = null;
            m_leftChild = null;
        }

        public String getKey() {
            return m_key;
        }

        public Object getValue() {
            return m_value;
        }

        public DSATreeNode getLeft() {
            return m_leftChild;
        }

        public void setLeft(DSATreeNode newLeft) {
            m_leftChild = newLeft;
        }

        public DSATreeNode getRight() {
            return m_rightChild;
        }

        public void setRight(DSATreeNode newRight) {
            m_rightChild = newRight;
        }

    }

    private DSATreeNode m_root;
    private int leftHt;
    private int rightHt;
    private DSALinkedList inOderList;
    private DSALinkedList preOderList;
    private DSALinkedList postOderList;

    public DSABinarySearchTree() {
        m_root = null;
    }

    public Object find(String key) throws Exception {
        return findRec(key, m_root);
    }

    public void insert(String key, Object value) throws Exception {
        m_root = insertRec(key, value, m_root);
    }

    public void delete(String key) throws Exception {
        deleteRec(key, m_root);
    }

    public void display(char usrInput) {
        inOderList = new DSALinkedList();
        preOderList = new DSALinkedList();
        postOderList = new DSALinkedList();
        inOrderTraverse(m_root);
        preOrderTraverse(m_root);
        postOrderTraverse(m_root);

        switch (usrInput) {
            case 'i':
                System.out.println("Inorder:");
                inOderList.iterateOverList(inOderList);
                System.out.println("\n");
                break;
            case 'p':
                System.out.println("Preorder:");
                preOderList.iterateOverList(postOderList);
                System.out.println("\n");
                break;
            case 'o':
                System.out.println("Postorder:");
                postOderList.iterateOverList(postOderList);
                System.out.println("\n");
                break;
        }

    }

    public int height() {
        return heightRec(m_root);
    }

    public String minKey() {
        return minRec(m_root);
    }

    public String maxKey() {
        return maxRec(m_root);
    }

    public double balance() {
        return calcBalPercentage(m_root);
    }

    public void save(DSABinarySearchTree pObject) throws Exception {
        saveFile(pObject, "Saved_Tree");
    }

    public DSABinarySearchTree load() throws Exception {
        return loadFile("Saved_Tree");
    }

    public void writeCSV() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("i-> Inorder\n" +
                "p-> Preorder\n" +
                "o-> Postorder\n");
        char usr = sc.next().charAt(0);
        switch (usr) {
            case 'i':
                inorderCSV();
                break;
            case 'p':
                preorderCSV();
                break;
            case 'o':
                postorderCSV();
                break;
        }
        sc.close();
    }

    public void readCSV(String pFile) throws Exception {
        readFrmCSV(pFile);
    }

    private Object findRec(String key, DSATreeNode currNode) throws Exception {
        Object value = null;
        if (currNode == null) {
            throw new Exception("Key " + key + " not found. ");
        } else if (key.equals(currNode.getKey())) {
            value = currNode.getValue();
        } else if (key.compareTo(currNode.getKey()) < 0) {
            value = findRec(key, currNode.getLeft());
        } else
            value = findRec(key, currNode.getRight());
        return value;
    }

    private DSATreeNode insertRec(String key, Object data, DSATreeNode currNode) throws Exception {
        DSATreeNode updateNode = currNode;
        if (currNode == null) {
            DSATreeNode newNode = new DSATreeNode(key, data);
            updateNode = newNode;
        } else if (key.equals(currNode.getKey())) {
            throw new Exception("This value is already in the tree ");
        } else if (key.compareTo(currNode.getKey()) < 0) {
            currNode.setLeft(insertRec(key, data, currNode.getLeft()));
        } else
            currNode.setRight(insertRec(key, data, currNode.getRight()));

        return updateNode;
    }

    private DSATreeNode deleteRec(String key, DSATreeNode currNode) throws Exception {
        DSATreeNode updateNode = currNode;
        if (currNode == null) {
            throw new Exception("Couldn't find that key");
        } else if (key.equals(currNode.getKey())) {
            updateNode = deleteNode(key, currNode);
        } else if (key.compareTo(currNode.getKey()) < 0) {
            currNode.setLeft(deleteRec(key, currNode.getLeft()));
        } else
            currNode.setRight(deleteRec(key, currNode.getRight()));

        return updateNode;
    }

    private DSATreeNode deleteNode(String key, DSATreeNode delNode) {
        DSATreeNode updateNode = null;
        if ((delNode.getLeft() == null) && (delNode.getRight() == null))
            updateNode = null;
        else if ((delNode.getLeft() != null) && (delNode.getRight() == null))
            updateNode = delNode.getLeft();
        else if ((delNode.getLeft() == null) && (delNode.getRight() != null))
            updateNode = delNode.getRight();
        else {
            updateNode = promoteSuccessor(delNode.getRight());
            if (updateNode != delNode.getRight()) {
                updateNode.setRight(delNode.getRight());
            } else
                updateNode.setLeft(delNode.getLeft());
        }
        return updateNode;
    }

    private DSATreeNode promoteSuccessor(DSATreeNode currNode) {
        DSATreeNode successor = currNode;
        if (currNode.getLeft() == null) {
            successor = currNode;
        } else {
            if (currNode.getLeft() != null) {
                successor = promoteSuccessor(currNode.getLeft());
                if (successor == currNode.getLeft())
                    currNode.setLeft(successor.getRight());
            }
        }
        return successor;
    }

    private int heightRec(DSATreeNode currNode) {
        int htSoFar;
        int iLeftHt = 0;
        int iRightHt = 0;
        if (currNode == null) {
            htSoFar = -1;
        } else {
            iLeftHt = heightRec(currNode.getLeft());
            iRightHt = heightRec(currNode.getRight());
            if (iLeftHt > iRightHt)
                htSoFar = iLeftHt + 1;
            else
                htSoFar = iRightHt + 1;
        }
        leftHt = iLeftHt + 1;
        rightHt = iRightHt + 1;
        return htSoFar;

    }

    private String minRec(DSATreeNode currNode) {
        String minKey;
        if (currNode.getLeft() != null) {
            minKey = minRec(currNode.getLeft());
        } else
            minKey = currNode.getKey();
        return minKey;
    }

    private String maxRec(DSATreeNode currNode) {
        String maxKey;
        if (currNode.getRight() != null) {
            maxKey = maxRec(currNode.getRight());
        } else
            maxKey = currNode.getKey();
        return maxKey;
    }

    private double calcBalPercentage(DSATreeNode node) {
        height();
        double min = Math.min(leftHt, rightHt);
        double max = Math.max(leftHt, rightHt);
        // System.out.println("Max " + max + " Min " + min);
        double bPercentage;
        // System.out.println("LH " + leftHt);
        // System.out.println("RH " + rightHt);

        if ((leftHt == rightHt) || ((max - min) == 1)) {
            bPercentage = 100;
        } else {
            bPercentage = (100 - ((max - min) / (max + min)) * 100);
        }
        return bPercentage;
    }

    private void inOrderTraverse(DSATreeNode node) {

        if (node != null) {
            inOrderTraverse(node.m_leftChild);
            // System.out.print(node.getKey() + " - " + node.getValue()+ " | ");
            inOderList.insertLast(node.getKey() + "," + node.getValue());
            inOrderTraverse(node.m_rightChild);
        }
    }

    private void preOrderTraverse(DSATreeNode node) {

        if (node != null) {
            // System.out.print(node.getKey() + " - " + node.getValue() + " | ");
            preOderList.insertLast(node.getKey() + "," + node.getValue());
            preOrderTraverse(node.m_leftChild);
            preOrderTraverse(node.m_rightChild);
        }
    }

    private void postOrderTraverse(DSATreeNode node) {

        if (node != null) {
            postOrderTraverse(node.m_leftChild);
            postOrderTraverse(node.m_rightChild);
            // System.out.print(node.getKey() + " - " + node.getValue() + " | ");
            postOderList.insertLast(node.getKey() + "," + node.getValue());
        }
    }

    // SERIALIZING
    private void saveFile(DSABinarySearchTree pObject, String pFile) {
        FileOutputStream fileStrm;
        ObjectOutputStream objStrm;
        try {
            fileStrm = new FileOutputStream(pFile);
            objStrm = new ObjectOutputStream(fileStrm);
            objStrm.writeObject(pObject);
            objStrm.close();
            System.out.println("File created.");
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to save object to file");
        }
    }

    // DESERIALIZING
    private DSABinarySearchTree loadFile(String pFile) throws IllegalArgumentException {
        FileInputStream fileStrm;
        ObjectInputStream objStrm;
        DSABinarySearchTree inObj;
        try {
            fileStrm = new FileInputStream(pFile);
            objStrm = new ObjectInputStream(fileStrm);
            inObj = (DSABinarySearchTree) objStrm.readObject();
            objStrm.close();
            fileStrm.close();
            System.out.println("File Loaded.");
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to load object from file");
        }

        return inObj;
    }

    private void inorderCSV() {
        FileOutputStream fos = null;
        PrintWriter pw;
        inOderList = new DSALinkedList();

        inOrderTraverse(m_root);

        Iterator newIterator;

        try {
            fos = new FileOutputStream("inorder.csv");
            pw = new PrintWriter(fos);
            newIterator = inOderList.iterator();
            while (newIterator.hasNext()) {
                pw.println(newIterator.next());
            }

            pw.close();

        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to create CSV File");
        }
        System.out.println("inorder.csv File created.");

    }

    private void preorderCSV() {
        FileOutputStream fos = null;
        PrintWriter pw;

        preOderList = new DSALinkedList();

        preOrderTraverse(m_root);

        Iterator newIterator;

        try {
            fos = new FileOutputStream("preorder.csv");
            pw = new PrintWriter(fos);
            newIterator = preOderList.iterator();
            while (newIterator.hasNext()) {
                pw.println(newIterator.next());
            }

            pw.close();

        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to create CSV File");
        }
        System.out.println("preorder.csv File created.");

    }

    private void postorderCSV() {
        FileOutputStream fos = null;
        PrintWriter pw;

        postOderList = new DSALinkedList();

        postOrderTraverse(m_root);
        Iterator newIterator;

        try {
            fos = new FileOutputStream("postorder.csv");
            pw = new PrintWriter(fos);
            newIterator = postOderList.iterator();
            while (newIterator.hasNext()) {
                pw.println(newIterator.next());
            }

            pw.close();

        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to create CSV File");
        }
        System.out.println("postorder.csv File created.");
    }

    private void readFrmCSV(String pFile) throws Exception {
        String line;
        FileInputStream stream = null;
        InputStreamReader reader;
        BufferedReader bfrReader;

        try {
            stream = new FileInputStream(pFile);
            reader = new InputStreamReader(stream);
            bfrReader = new BufferedReader(reader);

            int i = 0;

            while ((line = bfrReader.readLine()) != null) {
                String[] splitArray = line.split(",");
                insert(splitArray[0], splitArray[1]);
                i++;
            }
            stream.close();
        } catch (IOException e) {
            System.out.println("ERROR While CSV File loading: " + e.getMessage());
        }
    }
}
