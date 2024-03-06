public class CircuQ extends DSAQueue {

    // CLASS FIELDS
    private Object[] cirQueue;
    private int count;
    private final int DEFAULT_CAPACITY = 100;

    private int rear ;
    private int front;

    // DEFAULT CONSTRUCTOR
    public CircuQ() {
        cirQueue = new Object[DEFAULT_CAPACITY];
        rear = -1;
        count = 0;

    }

    // ALTERNATIVE CONSTROCTOR
    public CircuQ(int maxCapacity) {
        cirQueue = new Object[maxCapacity];
        rear = -1;
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
        boolean full = count == cirQueue.length;
        return full;
    }

    // Add values to queue
    public void enqueue(Object value) throws Exception {
        if (!isFull()) {
            rear = (rear+1) % cirQueue.length;
            cirQueue[rear] = value;
            count++;

        } else
            throw new Exception("Queue is Full!");
    }

    public Object dequeue() throws Exception {
        if (!isEmpty()) {
            Object data = cirQueue[front];
            cirQueue[front] = null;
            front = (front + 1) % cirQueue.length;
            count--;
            return data;
        } else
            throw new Exception("Queue is empty!");

    }

    // Show the first value in the queue
    public Object peek() throws Exception {
        if (isEmpty())
            throw new Exception("Queue is empty!");
        else {
            return cirQueue[front];
        }
    }

    public void print() {
        for (int i = 0; i < cirQueue.length; i++) {
            System.out.print(cirQueue[i] + " ");
        }
        System.out.println();
    }

}