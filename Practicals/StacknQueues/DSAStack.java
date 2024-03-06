public class DSAStack {

    // Class fields
    private Object[] stack;
    private int count;
    private final int DEFAULT_CAPACITY = 100;

    // DEFAULT CONSTRUCTOR
    public DSAStack() {
        stack = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    // ALTERNATIVE CONSTRUCTOR
    public DSAStack(int maxCapacity) {
        stack = new Object[maxCapacity];
        count = 0;
    }

    public int getCount() {
        return count;
    }

    // Check if stack is empty
    public boolean isEmpty() {
        boolean empty = count == 0;
        return empty;
    }

    // Check if stack is full
    public boolean isFull() {
        boolean full = count == stack.length;
        return full;
    }

    // Add a value to the stack
    public void push(Object value) throws Exception {
        if (isFull())
            throw new Exception("Stack is full!");
        else {
            stack[count] = value;
            count++;
        }
    }

    // Remove value from the stack
    public Object pop() throws Exception {
        Object topVal = top();
        count--;
        return topVal;
    }

    // Show the top value in the stack
    public Object top() throws Exception {
        if (isEmpty())
            throw new Exception("Stack is empty!");
        else {
            return stack[count - 1];
        }
    }

    public void print() {
        System.out.print("Stack: ");
        for (int i = 0; i < count; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

}