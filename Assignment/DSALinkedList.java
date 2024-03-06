import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

public class DSALinkedList implements Iterable, Serializable {

    public Iterator iterator() {
        return new DSALinkedListIterator(this);
    }

    // PRIVATE CLASS: DSAListNode
    private class DSAListNode implements Serializable {

        // CLASS FIELDS
        public Object value;
        public DSAListNode next;
        public DSAListNode prev;

        // ALTERNATIVE CONSTRUCTOR
        public DSAListNode(Object inValue) {
            value = inValue;
            next = null;
        }

        public Object getValue() {
            return value;
        }

        public DSAListNode getNext() {
            return next;
        }

        public DSAListNode getPrev() {
            return prev;
        }

        public void setValue(Object inValue) {
            value = inValue;
        }

        public void setNext(DSAListNode newNext) {
            next = newNext;
        }

        public void setPrev(DSAListNode newPrev) {
            prev = newPrev;
        }

    }

    // PRIVATE CLASS: ITERATOR
    private class DSALinkedListIterator implements Iterator, Serializable {
        private DSAListNode iterNext;

        public DSALinkedListIterator(DSALinkedList theList) {
            iterNext = theList.head;
        }

        public boolean hasNext() {
            return (iterNext != null);
        }

        public Object next() {
            Object value;
            if (iterNext == null)
                value = null;
            else {
                value = iterNext.getValue();
                iterNext = iterNext.getNext();
            }
            return value;
        }

        public void remove() {
            throw new UnsupportedOperationException("Not Supported ");
        }
    }

    // CLASS FIELDS
    private DSAListNode head;
    private DSAListNode tail;

    // DEFAULT CONSTRUCTOR
    public DSALinkedList() {
        head = null;
        tail = null;
    }

    // INSERT FIRST
    public void insertFirst(Object newValue) {

        DSAListNode newNode = new DSAListNode(newValue);

        if (isEmpty()) {
            tail = newNode;
        } else {
            head.setPrev(newNode);
        }
        newNode.setNext(head);
        head = newNode;
        // System.out.println(newValue + " added to list.");

    }

    // INSERT LAST
    public void insertLast(Object newValue) {

        DSAListNode newNode = new DSAListNode(newValue);

        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
        }
        tail = newNode;

    }

    // PEEK FIRST
    public Object peekFirst() throws Exception {
        Object nodeValue = null;
        if (isEmpty()) {
            throw new Exception("The list is empty ");
        }

        else {
            nodeValue = head.getValue();
            // System.out.println("First value is " + nodeValue);
        }

        return nodeValue;
    }

    // PEEK LAST
    public Object peekLast() throws Exception {
        Object nodeValue = null;
        if (isEmpty()) {
            throw new Exception("The list is empty ");
        } else {
            nodeValue = tail.getValue();
            // System.out.println("Last value is " + nodeValue);
        }
        return nodeValue;

    }

    // REMOVE FIRST
    public Object removeFirst() throws Exception {
        Object nodeValue = null;
        if (isEmpty()) {
            throw new Exception("The list is empty ");
        } else if (head.getNext() == null) {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        } else {
            nodeValue = head.getValue();
            head = head.getNext();
            head.setPrev(null);
        }
        // System.out.println(nodeValue + " removed from the list.");
        return nodeValue;

    }

    // REMOVE LAST
    public Object removeLast() throws Exception {

        Object nodeValue = null;
        if (isEmpty()) {
            throw new Exception("The list is empty ");
        } else if (head.getNext() == null) {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        } else {
            DSAListNode prevNode = tail.getPrev();
            DSAListNode currentNode = tail;

            prevNode.setNext(null);
            tail = prevNode;
            nodeValue = currentNode.getValue();
            // System.out.println(nodeValue + " removed from the list.");
        }
        return nodeValue;
    }

    public void remove(Object key) throws Exception {
        DSAListNode node = head;
        boolean found = false;

        if (isEmpty()) {
            throw new Exception("Empty");
        } else if (head.getValue() == key) {
            removeFirst();
        } else if (tail.getValue() == key) {
            removeLast();
        } else {
            while ((!found) && (node != null)) {
                if (node.getValue() == key) {
                    found = true;
                    node.getNext().setPrev(node.getPrev());
                    node.getPrev().setNext(node.getNext());
                }
                node = node.getNext();
            }
        }
    }

    // CHECK IF THE LIST IS EMPTY
    public boolean isEmpty() {
        boolean empty = head == null;
        return empty;
    }

    // ITERATE
    public void iterateOverList(DSALinkedList theList) {
        Object c;
        for (Object o : theList) {
            c = (Object) o;
            System.out.print(c + " -> ");

        }
    }

    // SERIALIZING
    public void save(DSALinkedList pObject, String pFile) {
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
    public DSALinkedList load(String pFile) throws IllegalArgumentException {
        FileInputStream fileStrm;
        ObjectInputStream objStrm;
        DSALinkedList inObj;
        try {
            fileStrm = new FileInputStream(pFile);
            objStrm = new ObjectInputStream(fileStrm);
            inObj = (DSALinkedList) objStrm.readObject();
            objStrm.close();
            System.out.println("File Loaded.");
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to load object from file");
        }
        return inObj;
    }

}
