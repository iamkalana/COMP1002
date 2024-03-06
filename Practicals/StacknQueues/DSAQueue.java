abstract class DSAQueue {

    // CLASS FIELDS
    private Object[] queue;
    private int count;
    private final int DEFAULT_CAPACITY = 100;

    private int rear, front;

    // DEFAULT CONSTRUCTOR
    public DSAQueue() {
        queue = new Object[DEFAULT_CAPACITY];
        count = 0;

    }

    // ALTERNATIVE CONSTRUCTOR
    public DSAQueue(int maxCapacity) {
        queue = new Object[maxCapacity];
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
        boolean full = count == queue.length;
        return full;
    }

    // Add values to queue
    public void enqueue(Object value) throws Exception {
        if (!isFull()) {
            queue[rear] = value;
            rear = rear + 1;
            count = count + 1;
        } else
            throw new Exception("Queue is Full!");
    }

    // Remove values from Queue
    public Object dequeue() throws Exception {
        if (!isEmpty()) {
            Object data = queue[front];
            front = front + 1;
            count = count - 1;
            return data;
        } else
            throw new Exception("Queue is empty!");

    }

    // Show the first value in the queue
    public Object peek() throws Exception {
        if (isEmpty())
            throw new Exception("Queue is empty!");
        else {
            return queue[front];
        }
    }

    public void print() {
        for (int i = 0; i < queue.length; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }

}