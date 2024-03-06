public class DSAHashEntry {
    private String key;
    private Object value;
    private int state;

    // DEFAULT CONSTRUCTOR//
    public DSAHashEntry() {
        key = "";
        value = null;
        state = 0;
    }

    // ALTERNATIVE CONSTRUCTOR//
    public DSAHashEntry(String inKey, Object inValue) {
        key = inKey;
        value = inValue;
        state = 1;
    }

    // GETTERS//
    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public int getState() {
        return state;
    }

    // SETTERS//
    public void setKey(String inKey) {
        key = inKey;
    }

    public void setValue(Object inValue) {
        value = inValue;
    }

    public void setState(int inState) {
        state = inState;
    }

}
