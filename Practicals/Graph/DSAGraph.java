import java.io.*;
import java.util.NoSuchElementException;

public class DSAGraph {
    // PRIVATE CLASS: DSAGraphVertex
    private class DSAGraphVertex {

        private String label;
        private DSALinkedList links;
        private boolean visited;
        private Object value;

        public DSAGraphVertex(String inLabel) {
            label = inLabel;
            links = new DSALinkedList();
        }

        public String getLabel() {
            return label;
        }

        public Object getValue() {
            return value;
        }

        public DSALinkedList getAdjacent() {
            DSALinkedList newLink = new DSALinkedList();
            for (Object o : links) {
                newLink.insertLast(o);
            }
            return links;
        }

        public void addEdge(DSAGraphVertex vertex) {
            links.insertLast(vertex);
        }

        public boolean getVisited() {
            return visited;
        }

        public void setVisited() {
            visited = true;
        }

        public void clearVisited() {
            visited = false;
        }

        public String toString() {
            return label;
        }
    }

    private DSALinkedList vertices, edges;
    private int vCount, eCount;
    private DSAStack stack = new DSAStack();
    private DSAQueue queue = new DSAQueue();
    private String output = " ";

    public DSAGraph() {
        vertices = new DSALinkedList();
        edges = new DSALinkedList();
    }

    // ADDING A VERTEX
    public void addVertex(String inLabel) {
        if (hasVertex(inLabel) == false) {
            vertices.insertLast(new DSAGraphVertex(inLabel));
            vCount++;
        } else
            System.out.println("Vertex " + inLabel + " already there");
    }

    // ADDING A EDGE
    public void addEdge(String inLabel1, String inLabel2) {

        if (!hasVertex(inLabel1)) {
            addVertex(inLabel1);
        }

        if (!hasVertex(inLabel2)) {
            addVertex(inLabel2);
        }

        if (!isAdjacent(inLabel1, inLabel2)) {
            for (Object o : vertices) {
                if (((DSAGraphVertex) o).equals(getVertex(inLabel1))) {
                    getVertex(inLabel1).addEdge(getVertex(inLabel2));
                    // getVertex(inLabel2).addEdge(getVertex(inLabel1));
                    edges.insertLast(new DSAGraphEdge(inLabel1, inLabel2, null));

                    eCount++;
                }
            }
        } else
            System.out.println("This edge already there! " + "(" + inLabel1 + "-" + inLabel2 + ")");
    }

    // ADDING A VERTEX VALUE
    public void addValue(String vertexLbl, Object value) {
        DSAGraphVertex vertex = getVertex(vertexLbl);
        vertex.value = value;
    }

    // PRINTING A VERTEX VALUE
    public void printValue() {
        for (Object o : vertices) {
            System.out.print(((DSAGraphVertex) o).getLabel() + " = " + ((DSAGraphVertex) o).getValue());
            System.out.println();
        }
    }

    // CHECK IS THERE A VERTEX AVAILABLE
    public boolean hasVertex(String vertexLbl) {
        boolean has = false;
        for (Object o : vertices) {
            if (((DSAGraphVertex) o).getLabel().equals(vertexLbl)) {
                has = true;
            }
        }
        return has;
    }

    // RETURN THE VERTEX COUNT
    public int getVertexCount() {
        return vCount;
    }

    // RETURN THE EDGE COUNT
    public int getEdgeCount() {
        return eCount;
    }

    // RETURN THE VERTEX
    public DSAGraphVertex getVertex(String inLabel) {
        DSAGraphVertex v = null;

        if (!hasVertex(inLabel)) {
            throw new NoSuchElementException("Vertex " + inLabel +
                    " Not Found");
        }

        for (Object o : vertices) {
            if (((DSAGraphVertex) o).getLabel().equals(inLabel)) {
                v = (DSAGraphVertex) o;
            }
        }

        return v;

    }

    // RETURN THE VERTEX ADJACENTS
    public DSALinkedList getAdjacent(String inLabel) {
        return getVertex(inLabel).getAdjacent();
    }

    // CHECK IS THERE A ADJACENT
    public boolean isAdjacent(String inLabel1, String inLabel2) {
        boolean adjacent = false;
        for (Object o : (getVertex(inLabel1).getAdjacent())) {
            if (((DSAGraphVertex) o).equals(getVertex(inLabel2))) {
                adjacent = true;
            }
        }
        return adjacent;
    }

    // SETTING ALL THE VERTICES TO NOT VISITED
    public void clearVisited() {
        for (Object o : vertices) {
            ((DSAGraphVertex) o).clearVisited();
        }
    }

    // DISPLAY THE GRAPH AS A LIST
    public void displayAsList() {
        for (Object o : vertices) {
            System.out.print(((DSAGraphVertex) o).getLabel() + " |");
            for (Object oo : ((DSAGraphVertex) o).getAdjacent()) {
                System.out.print("  " + ((DSAGraphVertex) oo).getLabel());
            }
            System.out.println();
        }
    }

    // DISPLAY THE GRAPH AS A MATRIX
    public void displayAsMatrix() {
        DSAGraphVertex[] tempArr = new DSAGraphVertex[vCount];
        int[][] matrix = new int[vCount][vCount];
        int k = 0;
        for (Object o : vertices) {
            tempArr[k] = (DSAGraphVertex) o;
            k++;
        }

        for (int i = 0; i < vCount; i++) {
            for (int j = 0; j < vCount; j++) {
                if (isAdjacent(tempArr[i].getLabel(), tempArr[j].getLabel())) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }

        System.out.print(" ");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(" " + tempArr[i]);
        }

        for (int i = 0; i < vCount; i++) {
            System.out.print("\n" + tempArr[i].getLabel());
            for (int j = 0; j < vCount; j++) {
                System.out.print(" " + matrix[i][j]);
            }
        }
        System.out.println();
    }

    // DEPTH FIRST SEARCH: WRAPPER METHOD
    public void depthFirstSearch() throws Exception {
        clearVisited();
        System.out.println(DFSTraverse((DSAGraphVertex) vertices.peekFirst()));
        output = " ";
    }

    // DEPTH FIRST SEARCH
    private String DFSTraverse(DSAGraphVertex vertex) throws Exception {

        DSAGraphVertex currentVertex;

        stack.push(vertex);
        output += vertex + " ";
        vertex.setVisited();
        for (Object o : vertex.getAdjacent()) {
            currentVertex = (DSAGraphVertex) o;
            if (!currentVertex.getVisited()) {
                DFSTraverse(currentVertex);
            }
        }
        if (!stack.isEmpty()) {
            stack.pop();
        }

        return output;
    }

    // BREADTH FIRST SEARCH: WRAPPER METHOD
    public void breadthFirstSearch() throws Exception {
        clearVisited();
        System.out.println(BFSTraverse((DSAGraphVertex) vertices.peekFirst()));
        output = " ";
    }

    // BREADTH FIRST SEARCH
    private String BFSTraverse(DSAGraphVertex vertex) throws Exception {

        DSAGraphVertex currentVertex;
        if (!vertex.getVisited()) {
            queue.enqueue(vertex);
            output += vertex + " ";
        }
        queue.dequeue();
        vertex.setVisited();
        for (Object o : vertex.getAdjacent()) {
            currentVertex = (DSAGraphVertex) o;
            if (!currentVertex.getVisited()) {
                queue.enqueue(currentVertex);
            }

        }
        if (!queue.isEmpty())
            BFSTraverse((DSAGraphVertex) queue.peek());
        return output;
    }

    // LOADING A FILE
    public void loadFile(String pFile) throws Exception {
        String line;
        FileInputStream stream = null;
        InputStreamReader reader;
        BufferedReader bfrReader;

        try {
            stream = new FileInputStream(pFile);
            reader = new InputStreamReader(stream);
            bfrReader = new BufferedReader(reader);

            while ((line = bfrReader.readLine()) != null) {
                String[] splitArray = line.split(" ");
                addEdge(splitArray[0], splitArray[1]);
            }
            stream.close();
            System.out.println("File Loaded.");
        } catch (IOException e) {
            System.out.println("ERROR While File loading: " + e.getMessage());
        }
    }

    // ADDING EDGE WEIGHTS
    public void addEdgeWeight(String from, String to, Object value) {
        if (!hasVertex(from))
            addVertex(from);
        if (!hasVertex(to))
            addVertex(to);
        if (!isAdjacent(from, to))
            addEdge(from, to);

        for (Object o : edges) {
            if (((DSAGraphEdge) o).getFrom().equals(from) && ((DSAGraphEdge) o).getTo().equals(to)) {
                ((DSAGraphEdge) o).setValue(value);
            }
        }

    }

    // PRINTING EDGE VALUES
    public void printEdgeValues() {
        for (Object o : edges) {
            System.out.println((((DSAGraphEdge) o).getFrom()) + "-" + (((DSAGraphEdge) o).getTo()) + " = "
                    + (((DSAGraphEdge) o).getValue()));
        }

    }

}