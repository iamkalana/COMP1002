import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

public class DSAQueue{

    // CLASS FIELDS
    Queue<Object> queueIB = new LinkedList<Object>();

    public int getCount() {
        return queueIB.size();
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return queueIB.isEmpty();
    }

    // Add values to queue
    public void enqueue(Object value) throws Exception {
        queueIB.add(value);
    }

    // Remove values from Queue
    public Object dequeue() throws Exception {
        if (!isEmpty()) {
            return queueIB.poll();
        } else
            throw new Exception("Queue is empty!");

    }

    // Show the first value in the queue
    public Object peek() throws Exception {
        if (isEmpty())
            throw new Exception("Queue is empty!");
        else {
            return queueIB.peek();
        }
    }

    public void printQueue() {
        Iterator<Object> ite = queueIB.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next() + " ");
        }
        System.out.println();
    }

}