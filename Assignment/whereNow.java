import java.util.Scanner;

public class whereNow {
    static Scanner sc = new Scanner(System.in);
    static Navigate n = new Navigate();

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {

            System.out.println("Interactive mode: java whereNow -i\n"
                    + "Silent mode: java whereNow -s <map file> <journey file> <save file>");

        } else {

            String cmnd = args[0];

            try {
                if (cmnd.equals("-i") && args.length == 1) {
                    interactiveMode();
                } else if (cmnd.equals("-s") && args.length == 4) {
                    silentMode(args[1], args[2], args[3]);
                } else {
                    System.out.println("Valid command line arguments not found!");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong:(");
            }
        }
    }

    public static void interactiveMode() throws Exception {

        System.out.println("\n~ Interactive Mode ~");

        int usrInput;

        do {
            System.out.println();
            System.out.println("= NAVIGATION MENU =\n");
            System.out.println("1 -> Load map file\n" +
                    "2 -> Node operations\n" +
                    "3 -> Edge operations\n" +
                    "4 -> Parameter tweaks\n" +
                    "5 -> Display graph\n" +
                    "6 -> Display world\n" +
                    "7 -> Enter journey details\n" +
                    "8 -> Generate routes\n" +
                    "9 -> Display routes\n" +
                    "10 -> Save network\n" +
                    "11 -> Load network\n" +
                    "0 -> Exit\n");

            usrInput = sc.nextInt();
            System.out.println();

            switch (usrInput) {
                case 1:
                    System.out.print("Enter the map file name: ");
                    String pFile = sc.next();
                    n.loadFile(pFile);
                    break;

                case 2:
                    nodeOperations();
                    break;

                case 3:
                    edgeOperations();
                    break;

                case 4:
                    try {
                        System.out.println("Enter the current distance unit(m or km): ");
                        String fUnit = sc.next();
                        System.out.println("Enter the prefered distance unit(m or km): ");
                        String tUnit = sc.next();
                        n.changeUnit(fUnit, tUnit);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        n.displayAsMatrix();
                        System.out.println("\nSave this to a file? (yes: y / no: n)");
                        char save = sc.next().charAt(0);
                        if (save == 'y') {
                            n.saveMatrix("matrix.txt");
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        n.printWorld();
                        System.out.println("\nSave this to a file? (yes: y / no: n)");
                        char save = sc.next().charAt(0);
                        if (save == 'y') {
                            n.saveWorld("world.txt");
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    System.out.print("Enter the start location: ");
                    String from = sc.next();
                    System.out.print("Enter the destination location: ");
                    String to = sc.next();
                    System.out
                            .print("Enter the barriers to avoid (ex: stairs,construction / if No, just type \"no\"): ");
                    String barr = sc.next();
                    System.out.print("Enter the your security levels (ex: 1,2 / if No, just type \"no\"): ");
                    String sec = sc.next();

                    n.getJourney(from, to, barr, sec);

                    break;

                case 8:
                    n.genRoutes();
                    break;

                case 9:
                    try {
                        n.printRoutes();
                        System.out.println("\nSave this to a file? (yes: y / no: n)");
                        char save = sc.next().charAt(0);
                        if (save == 'y') {
                            n.saveFile("RouteDisplayed.txt");
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    n.clearPaths();
                    n.clearLists();
                    break;

                case 10:
                    try {
                        System.out.println("Enter a name for the network: ");
                        String saveNet = sc.next();
                        n.saveNetwork(n, saveNet);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 11:
                    try {
                        System.out.println("Enter the network name: ");
                        String loadNet = sc.next();
                        n = n.loadNetwork(loadNet);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("~~Thank you~~");
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
            System.out.println();
        } while (usrInput != 0);
        sc.close();
    }

    public static void silentMode(String mapFile, String journeyFile, String saveFile) {
        System.out.println("\n~ Silent Mode ~");
        try {
            n.loadFile(mapFile);
            n.readJourneyFile(journeyFile);
            n.saveFile(saveFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sc.close();
    }

    public static void nodeOperations() {

        System.out.println("\n~ Node operations ~\n");

        System.out.println("1 -> Find location\n" +
                "2 -> Add new location\n" +
                "3 -> Delete location\n" +
                "4 -> Update location\n");

        int noUsrInput = sc.nextInt();
        System.out.println();

        switch (noUsrInput) {
            case 1:
                System.out.print("Enter the location name: ");
                String fNode = sc.next();
                if (n.hasLocation(fNode)) {
                    System.out.println("Location found.");
                } else {
                    System.out.println("Location not found.");
                }
                break;
            case 2:
                System.out.print("Enter the location name: ");
                String newNode = sc.next();
                try {
                    n.addLocation(newNode);
                    System.out.println("Location added successfully.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                break;
            case 3:
                System.out.print("Enter the location name to delete: ");
                String dltNode = sc.next();
                try {
                    n.removeLocation(dltNode);
                    System.out.println("Location removed successfully.");
                } catch (Exception e) {
                    e.getMessage();
                }

                break;
            case 4:
                System.out.print("Enter the current location name: ");
                String currN = sc.next();
                System.out.print("Enter the new location name: ");
                String newN = sc.next();
                try {
                    n.updateLocation(currN, newN);
                    System.out.println("Location updated successfully.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                break;

            default:
                System.out.println("Invalid input!");
                break;
        }
    }

    public static void edgeOperations() throws Exception {

        System.out.println("\n~ Edge operations ~\n");

        System.out.println("1 -> Find edge\n" +
                "2 -> Add new edge\n" +
                "3 -> Delete edge\n" +
                "4 -> Update edge\n");

        int eoUsrInput = sc.nextInt();
        System.out.println();

        switch (eoUsrInput) {
            case 1:
                System.out.print("Enter the source location: ");
                String from = sc.next();
                System.out.print("Enter the destination location: ");
                String to = sc.next();
                try {
                    if (n.isAdjacent(from, to)) {
                        System.out.println("path found.");
                    } else {
                        System.out.println("path not found.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                System.out.print("Enter the source location: ");
                String aFrom = sc.next();
                System.out.print("Enter the destination location: ");
                String aTo = sc.next();
                System.out.print("Enter the distance: ");
                int dist = sc.nextInt();
                System.out.print("Enter the barrier: ");
                System.out.println("\nif No, just type \"no\".");
                System.out.println("if there are multiple, enter them with commas. (ex: stairs,construction)");
                String barrier = sc.next();
                System.out.print("Enter the security levels: ");
                System.out.println("\nif No, just type \"no\".");
                System.out.println("if there are multiple, enter them with commas. (ex: 1,2 or 1,2,3)");
                String sec = sc.next();

                try {
                    n.addPath(aFrom, aTo, dist, sec, barrier);
                    System.out.println("Path added successfully.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                break;
            case 3:
                System.out.print("Enter the source location to delete path: ");
                String dltFrom = sc.next();
                System.out.print("Enter the destination location to delete path: ");
                String dltTo = sc.next();
                try {
                    n.removePath(dltFrom, dltTo);
                    System.out.println("Path removed successfully.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                System.out.print("Enter the source location: ");
                String uFrom = sc.next();
                System.out.print("Enter the destination location: ");
                String uTo = sc.next();
                System.out.print("Enter the previous distance: ");
                int preDist = sc.nextInt();

                System.out.println("\n////////////// NEW DATA //////////////\n");

                System.out.print("Enter the new distance: ");
                int nDist = sc.nextInt();
                System.out.print("Enter the new barriers: ");
                System.out.println("\nif No, just type \"no\".");
                System.out.println("if there are multiple, enter them with commas. (ex: stairs,construction)");
                String nBarrier = sc.next();
                System.out.print("Enter the new security levels: ");
                System.out.println("\nif No, just type \"no\".");
                System.out.println("if there are multiple, enter them with commas. (ex: 1,2 or 1,2,3)");
                String nSec = sc.next();

                try {
                    n.updatePath(uFrom, uTo, preDist, nDist, nSec, nBarrier);
                    System.out.println("Path updated successfully.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                break;

            default:
                System.out.println("Invalid input!");
                break;
        }
    }
}