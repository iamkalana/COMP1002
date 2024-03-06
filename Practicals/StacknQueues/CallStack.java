public class CallStack extends DSAStack {

    private static DSAStack obj;
    private static int recLvl;

    public CallStack(){
        obj = new DSAStack();
        recLvl = 0;
    }

    public long rcrFact(int n) throws Exception {
        long fact;
        if (n == 0) {
            recLvl++;
            fact = 1;
        } else {
            recLvl++;
            obj.push("RecLvl:" + recLvl + "  n:" + n + " ");
            obj.print();
            fact = n * rcrFact(n - 1);
            obj.pop();
            obj.print();
        }
        return fact;
    }

}