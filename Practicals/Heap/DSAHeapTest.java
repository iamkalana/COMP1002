import java.util.Scanner;

public class DSAHeapTest {
    public static void main(String[] args) {
        DSAHeap h = new DSAHeap(7000);
        // h.add(1, "nnk");
        // h.add(2, "eer");
        // h.add(4, "hjh");
        // h.add(9, "hjh");
        // h.add(3, "sfd");
        // h.display();
        // h.remove();
        // h.readCSV("RandomNames7000.csv");
        // h.heapSort();
        // h.display();
        // System.out.println();

        System.out.println("\n~ DSA Heap ~\n");

        Scanner sc = new Scanner(System.in);
        int usrInput;

        do {

            System.out.println("1 -> Add value\n" +
                    "2 -> Remove value\n" +
                    "3 -> Display heap values\n" +
                    "4 -> Heap Sort (\"RandomNames7000.csv\")\n" +
                    "0 -> Exit\n");

            usrInput = sc.nextInt();
            System.out.println();

            switch (usrInput) {
                case 1:
                    System.out.print("~ Add value ~\n");
                    System.out.print("Enter the priority number: ");
                    int p = sc.nextInt();
                    System.out.print("Enter the value: ");
                    Object v = sc.next();
                    h.add(p, v);
                    System.out.println(v + " (" + p + ") " + " added. ");
                    break;

                case 2:
                    try {
                        h.remove();
                        System.out.println("Root value removed.");
                    } catch (Exception e) {
                        System.out.println("Not removed! Something went wrong....");
                    }

                    break;

                case 3:
                    System.out.println("~ Heap values ~\n");
                    h.display();
                    break;

                case 4:
                    try {
                        h.readCSV("RandomNames7000.csv");
                        h.heapSort();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("~~Thank you~~");
                    System.out.println();
                    break;
            }
            System.out.println();
        } while (usrInput != 0);
        sc.close();
    }
}
