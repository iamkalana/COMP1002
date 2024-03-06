public class ShuffQ extends DSAQueue {

    // CLASS FIELDS
    private Object[] shffQueue;
    private int count;
    private final int DEFAULT_CAPACITY = 100;

    private int rear, front;

    // DEFAULT CONSTRUCTOR
    public ShuffQ() {
        shffQueue = new Object[DEFAULT_CAPACITY];
        count = 0;

    }

    // ALTERNATIVE CONSTROCTOR
    public ShuffQ(int maxCapacity) {
        shffQueue = new Object[maxCapacity];
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

    // Check if queue is full
    public boolean isFull() {
        boolean full = count == shffQueue.length;
        return full;
    }

    // Add values to queue
    public void enqueue(Object value) throws Exception {
        if (!isFull()) {
            shffQueue[rear] = value;
            rear = rear + 1;
            count = count + 1;
        } else
            throw new Exception("Queue is Full!");
    }

    // Remove values from Queue
    public Object dequeue() throws Exception {
        if (!isEmpty()) {
            Object data = shffQueue[front];
            count = count - 1;
            rear = rear - 1;
            for (int i = 0; i < (shffQueue.length-1); i++) {
                Object temp = shffQueue[i + 1];
                shffQueue[i] = temp;
                shffQueue[i + 1] = null;
            }

            return data;
        } else
            throw new Exception("Queue is empty!");

    }

    public Object peek() throws Exception {
        if (isEmpty())
            throw new Exception("Queue is empty!");
        else {
            return shffQueue[front];
        }
    }

    public void print() {
        for (int i = 0; i < shffQueue.length; i++) {
            System.out.print(shffQueue[i] + " ");
        }
        System.out.println();
    }
   
}