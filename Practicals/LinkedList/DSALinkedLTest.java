import java.util.Scanner;

public class DSALinkedLTest {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        DSALinkedList linkedList = new DSALinkedList();
        System.out.println("\n~ DSA Linked List ~\n");

        int usrInput;

        do {

            System.out.println("1-> Insert first\n" +
                    "2-> Insert last\n" +
                    "3-> Remove first\n" +
                    "4-> Remove last\n" +
                    "5-> Print List\n" +
                    "6-> Write a serialised file\n" +
                    "7-> Read a serialised file\n" +
                    "8-> Peek First\n" +
                    "9-> Peek Last\n" +
                    "10-> Remove\n" +
                    "0-> Exit\n");

            usrInput = sc.nextInt();
            System.out.println();

            switch (usrInput) {
                case 1:
                    System.out.print("Enter the value: ");
                    linkedList.insertFirst(sc.nextInt());
                    break;

                case 2:
                    System.out.print("Enter the value: ");
                    linkedList.insertLast(sc.nextInt());
                    break;

                case 3:
                    linkedList.removeFirst();
                    break;

                case 4:
                    linkedList.removeLast();
                    break;

                case 5:
                    linkedList.iterateOverList(linkedList);
                    break;

                case 6:
                    linkedList.save(linkedList, "serialised_File");
                    break;

                case 7:
                    linkedList = linkedList.load("serialised_File");
                    break;

                case 8:
                    linkedList.peekFirst();
                    break;

                case 9:
                    linkedList.peekLast();
                    break;

                case 10:
                    System.out.print("Enter the value to delete: ");
                    linkedList.remove(sc.nextInt());
                    break;

                case 0:
                    System.out.println("~~Thank you~~");
                    break;
            }
            System.out.println();
            System.out.println();
        } while (usrInput != 0);
        sc.close();
    }

}
