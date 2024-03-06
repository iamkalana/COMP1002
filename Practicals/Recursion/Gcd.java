import java.util.InputMismatchException;
import java.util.Scanner;

public class Gcd {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Calculate the Greatest Common Denominator of two numbers ");
            System.out.println("Enter your first number: ");
            int x = sc.nextInt();
            System.out.println("Enter your Second number: ");
            int y = sc.nextInt();

            System.out.println("GCD of " + x + " & " + y + " is " + gcdWrap(x, y));

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! ");
        }

        sc.close();

    }

    //GCD recursive solution wrapper method
    public static int gcdWrap(int pwX, int pwY) {
        int gcdVal = 0;
        if (pwY == 0)
            gcdVal = pwX;
        else
            gcdVal = findGcd(pwX, pwY);
        return gcdVal;

    }

    //Find gcd by recursive algorithm
    private static int findGcd(int pX, int pY) {
        int gcd = 1;
        if (pY == 0)
            gcd = pX;
        else
            gcd = findGcd(pY, pX % pY);
        return gcd;
    }

}
