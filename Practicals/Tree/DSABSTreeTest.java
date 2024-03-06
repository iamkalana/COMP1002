import java.util.Scanner;

public class DSABSTreeTest {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        DSABinarySearchTree tree = new DSABinarySearchTree();
        System.out.println("\n~ DSA Binary Search Tree ~\n");

        int usrInput;

        do {

            System.out.println("1 -> Insert\n" +
                    "2 -> Find\n" +
                    "3 -> Delete\n" +
                    "4 -> Display\n" +
                    "5 -> Height\n" +
                    "6 -> Balance as a percentage\n" +
                    "7 -> Minimum key & Maximum key\n" +
                    "8 -> Write a serialised file\n" +
                    "9 -> Read a serialised file\n" +
                    "10-> Write a CSV file\n" +
                    "11-> Read a CSV file\n" +
                    "0 -> Exit\n");

            usrInput = sc.nextInt();
            System.out.println();

            switch (usrInput) {
                case 1:
                    System.out.print("Enter the key: ");
                    String instKey = sc.next();
                    System.out.print("Enter the value: ");
                    Object data = sc.next();
                    tree.insert(instKey, data);
                    break;

                case 2:
                    System.out.print("Enter the key to find: ");
                    String fndKey = sc.next();
                    System.out.println("Value of key " + fndKey + " = " + tree.find(fndKey));

                    break;

                case 3:
                    System.out.print("Enter the key to delete: ");
                    String dltKey = sc.next();
                    tree.delete(dltKey);
                    System.out.println("Key " + dltKey + " deleted");
                    break;

                case 4:
                    System.out.println("i-> Inorder\n" +
                            "p-> Preorder\n" +
                            "o-> Postorder\n");
                    char ipoDisplay = sc.next().charAt(0);
                    tree.display(ipoDisplay);
                    break;

                case 5:
                    System.out.println("Height of the tree is " + tree.height());
                    break;

                case 6:
                    System.out.println("Balance percentage of the tree is " + tree.balance() + " %");
                    break;

                case 7:
                    System.out.println("Minimum key of the tree = " + tree.minKey());
                    System.out.println("Maximum key of the tree = " + tree.maxKey());
                    break;

                case 8:
                    tree.save(tree);
                    break;
                case 9:
                    tree = tree.load();
                    break;
                case 10:
                    tree.writeCSV();
                    break;
                case 11:
                    tree.readCSV("inorder.csv");
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
