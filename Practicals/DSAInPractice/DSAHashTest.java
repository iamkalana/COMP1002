import java.util.Scanner;

public class DSAHashTest {
    public static void main(String[] args) {

        DSAHashTable h = new DSAHashTable();

        Scanner sc = new Scanner(System.in);
        int usrInput;

        do {

            System.out.println("1 -> Add value\n" +
                    "2 -> get value\n" +
                    "3 -> remove value\n" +
                    "4 -> Print hash table\n" +
                    "5 -> Read file\n" +
                    "6 -> Write file\n" +
                    "0 -> Exit\n");

            usrInput = sc.nextInt();
            System.out.println();

            switch (usrInput) {
                case 1:
                    System.out.print("~ Add value ~\n");
                    System.out.print("Enter the key: ");
                    String inKey = sc.next();
                    System.out.print("Enter the value: ");
                    Object inValue = sc.next();
                    try {
                        h.putValue(inKey, inValue);
                        System.out.println(inValue + " added to key " + inKey);
                    } catch (Exception e) {
                        System.out.println("Key " + inKey + " not found");
                    }

                    break;

                case 2:
                    System.out.print("~ get value ~\n");
                    System.out.print("Enter the key: ");
                    String gtKey = sc.next();
                    try {
                        System.out.println("Value of " + gtKey + " is " + h.getValue(gtKey));
                    } catch (Exception e) {
                        System.out.println("Key " + gtKey + " not found");
                    }
                    break;

                case 3:
                    System.out.print("~ remove value ~\n");
                    System.out.print("Enter the key: ");
                    String rmKey = sc.next();
                    try {
                        h.removeValue(rmKey);
                    } catch (Exception e) {
                        System.out.println("Not removed! Key could not be found");
                    }

                    break;

                case 4:
                    System.out.println("~ Hash table ~\n");
                    h.display();
                    break;

                case 5:
                    h.loadFile("RandomNames7000.csv");
                    break;

                case 6:
                    h.saveFile("output.csv");
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
