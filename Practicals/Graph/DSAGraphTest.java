import java.util.Scanner;

public class DSAGraphTest {
    public static void main(String[] args) throws Exception {
        DSAGraph gr = new DSAGraph();
        Scanner sc = new Scanner(System.in);

        System.out.println("\n~ DSA Graph ~\n");

        int usrInput;

        do {

            System.out.println("1 -> Add vertex\n" +
                    "2 -> Add edge\n" +
                    "3 -> Add value\n" +
                    "4 -> Print vertex values\n" +
                    "5 -> Get vertex count\n" +
                    "6 -> Get edge count\n" +
                    "7 -> Depth First Search\n" +
                    "8 -> Breadth First Search\n" +
                    "9 -> Print as adjacent list\n" +
                    "10 -> Print as adjacent matrix\n" +
                    "11 -> Load file\n" +
                    "12 -> Add edge weight\n" +
                    "13 -> Print edge weights\n" +
                    "0 -> Exit\n");

            usrInput = sc.nextInt();
            System.out.println();

            switch (usrInput) {
                case 1:
                    System.out.print("Enter the vertex label: ");
                    String instVtx = sc.next();
                    gr.addVertex(instVtx);
                    break;

                case 2:
                    System.out.print("Enter the source vertex: ");
                    String src = sc.next();
                    System.out.print("Enter the destination vertex: ");
                    String dst = sc.next();
                    gr.addEdge(src, dst);
                    break;

                case 3:
                    System.out.print("Enter the vertex label: ");
                    String lbl = sc.next();
                    System.out.print("Enter the value: ");
                    String vl = sc.next();
                    gr.addValue(lbl, vl);
                    System.out.println("Value " + vl + " added to the vertex " + lbl + ".");
                    break;

                case 4:
                    System.out.println("Vertex values");
                    gr.printValue();
                    break;

                case 5:
                    System.out.println("Vertex count = " + gr.getVertexCount());
                    break;

                case 6:
                    System.out.println("Edge count = " + gr.getEdgeCount());
                    break;

                case 7:
                    System.out.print("Depth First Search:");
                    gr.depthFirstSearch();
                    break;

                case 8:
                    System.out.print("Breadth First Search:");
                    gr.breadthFirstSearch();
                    break;

                case 9:
                    gr.displayAsList();
                    break;

                case 10:
                    gr.displayAsMatrix();
                    break;

                case 11:
                    System.out.println("Which file do you want to load?\n 1.prac6_1.al\n 2.prac6_2.al");
                    int f = sc.nextInt();
                    if (f == 1)
                        gr.loadFile("prac6_1.al");
                    else if (f == 2)
                        gr.loadFile("prac6_2.al");
                    else
                        System.out.println("Invalid input!");
                    break;

                case 12:
                    System.out.print("Enter the source vertex: ");
                    String s = sc.next();
                    System.out.print("Enter the destination vertex: ");
                    String d = sc.next();
                    System.out.print("Enter the edge weight: ");
                    Object v = sc.next();
                    gr.addEdgeWeight(s, d, v);
                    break;

                case 13:
                    System.out.println("Edge weights:");
                    gr.printEdgeValues();
                    break;

                case 0:
                    System.out.println("~~Thank you~~");
                    break;
            }
            System.out.println();
        } while (usrInput != 0);
        sc.close();
    }
}
