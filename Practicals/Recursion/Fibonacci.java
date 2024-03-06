import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter the index number: ");
            int usrInput = sc.nextInt();

            if (usrInput >= 0) {
                //System.out.println("Fibonacci number " +usrInput+ " is " + itrFib(usrInput));
                System.out.println("Fibonacci " + usrInput + " is " + rcrFibWrap(usrInput));
            } else {
                System.out.println("Please enter a positive number ");
            }
            

        } catch (Exception e) {
            System.out.println("Input is not valid");
        }

        sc.close();


    }


    //Recursive solution wrapper method
    public static long rcrFibWrap(int a) throws Exception {
        if (a>=0)
            return rcrFib(a);
        else
            throw new Exception("User input is not valid (Must be greater than zero)");
    }


    //Iterative solution
    public static long itrFib(int nn) {
        long fibVal = 0;
        long currVal = 1;
        long lastVal = 0;

        if (nn == 0)
            fibVal = 0;
        else if (nn == 1)
            fibVal = 1;
        else {
            for (int ii = 1; ii < nn; ii++) {
                fibVal = currVal + lastVal;
                lastVal = currVal;
                currVal = fibVal;
            }
        }

        return fibVal;
    }

    //Recursive solution
    private static long rcrFib(int nn) {
        long fibVal = 0;
        if (nn == 0)
            fibVal = 0;
        else if (nn == 1)
            fibVal = 1;
        else {
            fibVal = rcrFib(nn - 1) + rcrFib(nn - 2);
        }
        return fibVal;
    }
}
