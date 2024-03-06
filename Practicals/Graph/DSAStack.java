public class DSAStack {

    // Class fields
    private DSALinkedList linkedList = new DSALinkedList();
    private int count;

    // DEFAULT CONSTRUCTOR
    public DSAStack() {
        count = 0;
    }

    // ALTERNATIVE CONSTRUCTOR
    public DSAStack(int maxCapacity) {
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

    // Add a value to the stack
    public void push(Object value) throws Exception {

        linkedList.insertLast(value);
        count++;

    }

    // Remove value from the stack
    public Object pop() throws Exception {
        count--;
        return linkedList.removeLast();
    }

    // Show the top value in the stack
    public Object top() throws Exception {
        if (isEmpty())
            throw new Exception("Stack is empty!");
        else {
            return linkedList.peekLast();
        }
    }

    public void print() {
        linkedList.iterateOverList(linkedList);
        System.out.println();
    }

}