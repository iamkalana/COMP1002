import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an integer number: ");
        try {
            int usrInput = sc.nextInt();

            System.out.println("Factorial of "+ usrInput + " is "+itrFact(usrInput));
            //System.out.println("Factorial of " + usrInput + " is " + rcrFctWrap(usrInput));

        } catch (Exception e) {
            System.out.println(e);
        }

        sc.close();

    }


    //Recursive solution wrapper method
    public static long rcrFctWrap(int a){
        if (a < 0)
            throw new IllegalArgumentException("Input value should be positive");
        else
            return rcrFact(a);
    }

    //Iterative solution
    public static long itrFact(int n) {

        long fact = 1;
        for (int ii = n; ii >= 2; ii--)
            fact *= ii;
        return fact;
    }

    //Recursive solution
    private static long rcrFact(int n) {
        long fact;
        if (n == 0)
            fact = 1;
        else
            fact = n * rcrFact(n - 1);
        return fact;
    }

}
