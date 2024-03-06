public class UnitTestDSALinkedList {
    public static void main(String[] args) throws Exception {
        String GREEN = "\u001B[32m";
        String DEFAULT = "\033[0m";
        DSALinkedList testList = new DSALinkedList();
        System.out.println(GREEN + "\n--------------------START OF TESTING--------------------" + DEFAULT);
        createSampleList(testList);
        testInsert(testList);
        testRemove(testList);
        System.out.println(GREEN + "---------------------END OF TESTING---------------------\n" + DEFAULT);

    }

    public static void createSampleList(DSALinkedList pList) {
        for (int i = 1; i < 10; i++) {
            pList.insertLast(i);
        }
    }

    public static void testInsert(DSALinkedList pList) {
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Inserting first:");
        pList.insertFirst(0);
        pList.iterateOverList(pList);
        System.out.println("\nexpected:");
        System.out.println("0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 ->");
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Inserting last:");
        pList.insertLast(10);
        pList.iterateOverList(pList);
        System.out.println("\nexpected:");
        System.out.println("0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 ->");
    }

    public static void testRemove(DSALinkedList pList) throws Exception {
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Remove first:");
        pList.removeFirst();
        pList.iterateOverList(pList);
        System.out.println("\nexpected:");
        System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 ->");
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Remove last:");
        pList.removeLast();
        pList.iterateOverList(pList);
        System.out.println("\nexpected:");
        System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 ->");
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("Remove by value: (7)");
        pList.remove(7);
        pList.iterateOverList(pList);
        System.out.println("\nexpected:");
        System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 8 -> 9 ->");
        System.out.println("\n--------------------------------------------------------\n");
    }
}
