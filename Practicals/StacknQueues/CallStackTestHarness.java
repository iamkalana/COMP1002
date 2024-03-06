import java.util.Scanner;

public class CallStackTestHarness{
  
    public static void main(String[] args) throws Exception {

        CallStack call = new CallStack();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an integer number: ");
        System.out.println("Factorial = " + call.rcrFact(sc.nextInt()));
        sc.close();
        
    }

}