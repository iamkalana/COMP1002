import java.util.Iterator;
import java.util.Stack;

public class DSAStack {

    // Class fields
    Stack<Object> stackIB = new Stack<Object>();

    public int getCount() {
        return stackIB.capacity();
    }

    // Check if stack is empty
    public boolean isEmptyStack() {
        return stackIB.empty();
    }

    // Add a value to the stack
    public void pushElement(Object value) throws Exception {
        stackIB.push(value);
    }

    // Remove value from the stack
    public Object popElement() throws Exception {
        if (!isEmptyStack()) {
            return stackIB.pop();
        } else
            throw new Exception("Stack is empty!");
    }

    // Show the top value in the stack
    public Object topElement() throws Exception {
        if (!isEmptyStack()) {
            return stackIB.peek();
        } else
            throw new Exception("Stack is empty!");
    }

    public void printStack() {
        Iterator<Object> iterator = stackIB.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + " ");
        }
        System.out.println();
    }

}