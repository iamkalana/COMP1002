import java.util.InputMismatchException;
import java.util.Scanner;

public class ConvrtNumbrs {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String usrCmd = "";

        System.out.println("Convert decimal value to any base ");

        try {
            do {

                System.out.println("Enter your Decimal value: ");
                int inputVal = sc.nextInt();

                System.out.println("Enter your base value: ");
                int base = sc.nextInt();

                if (inputVal == 0)
                    System.out.println(0);
                else if ((base > 1) && (base < 17))
                    System.out.println(convertWrap(inputVal, base));
                else
                    System.out.println("Base value should be between 2 and 16");


                System.out.println("");
                System.out.println("Do you want to try another number? y: Yes  n: No");

                usrCmd = sc.next();

            } while (usrCmd.equals("y"));

            System.out.println("Thank you");

        } catch (InputMismatchException e) {
            System.out.println("Wrong user input! ");
        }

        sc.close();

    }

    //Wrapper method
    public static String convertWrap(int pwNumber, int pwBase){
        if ((pwNumber < 0)||(pwBase < 0))
            throw new IllegalArgumentException("Decimal value and base value should be positive");
        else
            return convert(pwNumber,pwBase);
    }

    private static String convert(int pNumber, int pBase) {

        String result;

        if (pNumber == 0)
            result = "";
        else
            result = (convert(pNumber / pBase, pBase) + (pNumber % pBase));

        return result;

    }
}

