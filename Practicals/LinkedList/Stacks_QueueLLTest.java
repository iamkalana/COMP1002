import java.util.Scanner;

public class Stacks_QueueLLTest {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        int usrInput;

        System.out.println("1-> Stacks\n" +
                "2-> Queue\n" +
                "3-> Exit\n");

        usrInput = sc.nextInt();
        System.out.println();

        switch (usrInput) {
            case 1:
                System.out.println("~ Stack with linked list ~");
                DSAStack stk = new DSAStack();
                int usrInput2;
                do {
                    System.out.println("1-> Push\n" +
                            "2-> Pop\n" +
                            "3-> top\n" +
                            "4-> print\n" +
                            "5-> Exit\n");
                    System.out.println();

                    usrInput2 = sc.nextInt();
                    switch (usrInput2) {
                        case 1:
                            System.out.println("Enter the value you want to add: ");
                            int value = sc.nextInt();
                            stk.push(value);
                            System.out.println();
                            stk.print();
                            break;
                        case 2:
                            System.out.println();
                            stk.pop();
                            break;
                        case 3:
                            System.out.println();
                            System.out.println("Top value is " + stk.top());
                            break;
                        case 4:
                            stk.print();
                            System.out.println();
                            break;
                        case 5:
                            System.out.println();
                            System.out.println("~~Thank you~~");
                            break;
                    }
                    System.out.println();
                } while (usrInput2 != 5);
                break;
            case 2:
                System.out.println("~ Queue with linked list ~");
                DSAQueue que = new DSAQueue();
                int usrInput3;
                do {
                    System.out.println("1-> Enqueue\n" +
                            "2-> Dequeue\n" +
                            "3-> peek\n" +
                            "4-> Print\n" +
                            "5-> Exit\n");
                    System.out.println();

                    usrInput3 = sc.nextInt();
                    switch (usrInput3) {
                        case 1:
                            System.out.println("Enter the value you want to add: ");
                            int value = sc.nextInt();
                            que.enqueue(value);
                            System.out.println();

                            break;
                        case 2:
                            System.out.println();
                            que.dequeue();
                            break;
                        case 3:
                            System.out.println();
                            que.peek();
                            break;
                        case 4:
                            que.print();
                            System.out.println();
                            break;
                        case 5:
                            System.out.println("~~Thank you~~");
                            break;
                    }
                    System.out.println();
                } while (usrInput3 != 5);
                break;
            case 3:
                System.out.println("~~Thank you~~");
                break;
        }
    }
}