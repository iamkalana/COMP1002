import java.util.Scanner;

public class DSAStackQueueTest {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        try {
            int usrInput;

            System.out.println("1-> Stacks\n" +
                    "2->Queue\n");

            usrInput = sc.nextInt();
            System.out.println();

            switch (usrInput) {
                case 1:
                    DSAStack stk = new DSAStack();
                    int usrInput2;
                    do {
                        System.out.println("1-> Push\n" +
                                "2-> Pop\n" +
                                "3-> top\n" +
                                "4-> Exit\n");
                        System.out.println();

                        usrInput2 = sc.nextInt();
                        switch (usrInput2) {
                            case 1:
                                System.out.println("Enter the value you want to add: ");
                                Object value = sc.next();
                                stk.pushElement(value);
                                System.out.println();
                                System.out.println("Value " + value + " added");
                                stk.printStack();
                                break;
                            case 2:
                                try {
                                    System.out.println();
                                    System.out.println("Value " + stk.popElement() + " removed");
                                    stk.printStack();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                try {
                                    System.out.println();
                                    System.out.println("Top value is " + stk.topElement());
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 4:
                                System.out.println();
                                System.out.println("~~Thank you~~");
                                break;
                        }
                        System.out.println();
                    } while (usrInput2 != 4);
                    break;

                case 2:
                    DSAQueue q = new DSAQueue();
                    int usrInput4;
                    do {
                        System.out.println("1-> Enqueue\n" +
                                "2-> Dequeue\n" +
                                "3-> peek\n" +
                                "4-> Exit\n");
                        System.out.println();

                        usrInput4 = sc.nextInt();
                        switch (usrInput4) {
                            case 1:
                                System.out.println("Enter the value you want to add: ");
                                Object value = sc.next();
                                q.enqueue(value);
                                System.out.println();
                                System.out.println("Value " + value + " added");
                                q.printQueue();
                                break;
                            case 2:
                                try {
                                    System.out.println();
                                    System.out.println("Value " + q.dequeue() + " removed");
                                    q.printQueue();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                try {
                                    System.out.println();
                                    System.out.println("Top value is " + q.peek());
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 4:
                                System.out.println();
                                System.out.println("~~Thank you~~");
                                break;
                        }
                        System.out.println();
                    } while (usrInput4 != 4);
                    break;
                case 4:
                    System.out.println("~~Thank you~~");
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}