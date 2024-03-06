public class DSAQueue {

    // CLASS FIELDS
    private DSALinkedList linkedList = new DSALinkedList();
    private int count;

    // DEFAULT CONSTRUCTOR
    public DSAQueue() {
        count = 0;

    }

    public int getCount() {
        return count;
    }

    // Check if queue is empty
    public boolean isEmpty() {
        boolean empty = count == 0;
        return empty;
    }

    // Add values to queue
    public void enqueue(Object value) throws Exception {
        linkedList.insertLast(value);
        count = count + 1;
    }

    // Remove values from Queue
    public Object dequeue() throws Exception {
        count = count - 1;
        return linkedList.removeFirst();

    }

    // Show the first value in the queue
    public Object peek() throws Exception {
        if (isEmpty())
            throw new Exception("Queue is empty!");
        else {
            return linkedList.peekFirst();
        }
    }

    public void print() {
        linkedList.iterateOverList(linkedList);
        System.out.println();
    }

}