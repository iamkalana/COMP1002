public class UnitTestNavigate {
    static Navigate test = new Navigate();
    static String GREEN = "\u001B[32m";
    static String RED = "\u001B[31m";
    static String DEFAULT = "\033[0m";

    public static void main(String[] args) {
        try {
            System.out.println(GREEN + "\n--------------------START OF TESTING--------------------" + DEFAULT);
            testLoadFile();
            testGetLocation();
            testIsAdjacent();
            testReadJourneyFile();
            testPrintPaths();
            testSaveFile();
            testSaveNetWork();
            testLoadNetWork();
            System.out.println(GREEN + "---------------------END OF TESTING---------------------\n" + DEFAULT);

        } catch (Exception e) {
            System.out.println("Something went wrong:(");
            e.printStackTrace();
        }

    }

    public static void testLoadFile() throws Exception {
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Test loadFile:");
        test.loadFile("TestData.txt");
        System.out.println("expected:");
        System.out.println("TestData.txt File Loaded.");
        System.out.println("\n--------------------------------------------------------\n");
    }

    public static void testGetLocation() {
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Test Get Location:");
        try {
            System.out.println(test.getLocation("B"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("expected:");
        System.out.println("B");
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Test Get Location:");
        try {
            System.out.println(test.getLocation("G"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("expected:");
        System.out.println("Location not found! G");
    }

    public static void testIsAdjacent() throws Exception {
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Test Get Adjacent:");
        System.out.println(test.isAdjacent("A", "B"));
        System.out.println("expected:");
        System.out.println("true");
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Test Get Adjacent:");
        System.out.println(test.isAdjacent("A", "C"));
        System.out.println("expected:");
        System.out.println("false");
    }

    public static void testReadJourneyFile() throws Exception {
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Test Read Journey File:");
        test.readJourneyFile("TestJourney.txt");
        System.out.println("expected:");
        System.out.println("TestJourney.txt File Loaded.");
    }

    public static void testPrintPaths() throws Exception {
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Test Print Paths:");
        test.clearPaths();
        test.clearLists();
        test.getJourney("A", "C", "no", "2");
        test.genRoutes();
        System.out.println();
        test.printRoutes();
        System.out.println();
        System.out.println("expected:");
        System.out.println("~ Filtered Routes ~\n" + "A -> B -> C ->\n" + "Total distance = 13.0\n"
                + "Security levels = 1,2 1,2\n" + "Barriers = stairs stairs");
    }

    public static void testSaveFile() {
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Test Save File:");
        test.saveFile("TestSave.txt");
        System.out.println("\nexpected:");
        System.out.println("File (TestSave.txt) saved successfully.\n" + "Route count: 1");
    }

    public static void testSaveNetWork() {
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Test Save Network:");
        test.saveNetwork(test, "testNet");
        System.out.println("expected:");
        System.out.println("Network saved.");
    }

    public static void testLoadNetWork() {
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Test Load Network:");
        test = test.loadNetwork("testNet");
        System.out.println("expected:");
        System.out.println("Network Loaded.");
        System.out.println("\n--------------------------------------------------------\n");
    }
}
