import java.util.Scanner;

public class EqSolTestHarness{
    public static void main(String[] args) throws Exception {

        EquationSolver eq = new EquationSolver();
        //System.out.println(eq.solve("( 10.3 * ( 14 + 3.2 ) ) / ( 5 + 2 - 4 * 3 )"));
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the INFIX formula: ");
        String userInput = sc.nextLine();
        System.out.println("Final answer is "+eq.solve(userInput));
        sc.close();
    }
}