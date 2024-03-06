public class DSAGraphEdge {
    private String from;
    private String to;
    private String label;
    private Object value;

    public DSAGraphEdge(String fromVertex, String toVertex, String inValue) {
        from = fromVertex;
        to = toVertex;
        value = inValue;
    }

    public String getLabel() {
        return label;
    }

    public Object getValue() {
        return value;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setValue(Object inValue) {
        value = inValue;
    }

    public String toString() {
        return label;
    }
}
