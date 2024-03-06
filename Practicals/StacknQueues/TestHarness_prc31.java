import java.util.Scanner;

public class TestHarness_prc31 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        int usrInput;

        System.out.println("1-> Stacks\n" +
                "2-> Shuffling Queue\n" +
                "3-> Circular Queue\n" +
                "4-> Exit\n");

        usrInput = sc.nextInt();
        System.out.println();

        switch (usrInput) {
            case 1:
                System.out.println("Enter the stack size: ");
                DSAStack stk = new DSAStack(sc.nextInt());
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
                            int value = sc.nextInt();
                            stk.push(value);
                            System.out.println();
                            System.out.println("Value " + value + " added");
                            stk.print();
                            break;
                        case 2:
                            System.out.println();
                            System.out.println("Value " + stk.pop() + " removed");
                            stk.print();
                            break;
                        case 3:
                            System.out.println();
                            System.out.println("Top value is " + stk.top());
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
                System.out.println("Enter the Queue size: ");
                DSAQueue shff = new ShuffQ(sc.nextInt());
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
                            int value = sc.nextInt();
                            shff.enqueue(value);
                            System.out.println();
                            System.out.println("Value " + value + " added");
                            shff.print();
                            break;
                        case 2:
                            System.out.println();
                            System.out.println("Value " + shff.dequeue() + " removed");
                            shff.print();
                            break;
                        case 3:
                            System.out.println();
                            System.out.println("Top value is " + shff.peek());
                            break;
                        case 4:
                            System.out.println();
                            System.out.println("~~Thank you~~");
                            break;
                    }
                    System.out.println();
                } while (usrInput4 != 4);
                break;
            case 3:
                System.out.println("Enter the Queue size: ");
                DSAQueue cir = new CircuQ(sc.nextInt());
                int usrInput5;
                do {
                    System.out.println("1-> Enqueue\n" +
                            "2-> Dequeue\n" +
                            "3-> peek\n" +
                            "4-> Exit\n");
                    System.out.println();

                    usrInput5 = sc.nextInt();
                    switch (usrInput5) {
                        case 1:
                            System.out.println("Enter the value you want to add: ");
                            int value = sc.nextInt();
                            cir.enqueue(value);
                            System.out.println();
                            System.out.println("Value " + value + " added");
                            cir.print();
                            break;
                        case 2:
                            System.out.println();
                            System.out.println("Value " + cir.dequeue() + " removed");
                            cir.print();
                            break;
                        case 3:
                            System.out.println();
                            System.out.println("Top value is " + cir.peek());
                            break;
                        case 4:
                            System.out.println();
                            System.out.println("~~Thank you~~");
                            break;
                    }
                    System.out.println();
                } while (usrInput5 != 4);
                break;
            case 4:
                System.out.println("~~Thank you~~");
                break;
        }
        sc.close();
    }
}