import java.util.*;

public class EquationSolver {
    private static StringTokenizer token;

    private static double evaluatePostfix(DSAQueue pfixQ) throws Exception {

        DSAStack opStack = new DSAStack();

        while (0 < pfixQ.getCount()) {
            Object term = pfixQ.dequeue();

            if (Character.isDigit(term.toString().charAt(0))) {
                opStack.push(term);
            } else {

                double i = Double.parseDouble(opStack.pop().toString());
                double ii = Double.parseDouble(opStack.pop().toString());

                switch (term.toString().charAt(0)) {
                    case '+':
                        opStack.push(ii + i);
                        break;

                    case '-':
                        opStack.push(ii - i);
                        break;

                    case '/':
                        opStack.push(ii / i);
                        break;

                    case '*':
                        opStack.push(ii * i);
                        break;
                }
            }

        }
        return Double.parseDouble(opStack.pop().toString());
    }

    public double solve(String equation) throws Exception {
        DSAQueue postfix = parseInfixToPostfix(equation);
        return evaluatePostfix(postfix);
    }

    private static DSAQueue parseInfixToPostfix(String equation) throws Exception {
        DSAQueue postfix = new CircuQ();
        DSAStack opStack = new DSAStack();

        token = new StringTokenizer(equation, " ");

        while (token.hasMoreTokens()) {
            String term = token.nextToken();

            if (term.charAt(0) == '(') {
                opStack.push('(');
            } else if (term.charAt(0) == ')') {
                while (opStack.top().toString().charAt(0) != '(') {
                    postfix.enqueue(opStack.pop());
                }
                opStack.pop();
            } else if (term.charAt(0) == '+' || term.charAt(0) == '-' || term.charAt(0) == '*'
                    || term.charAt(0) == '/') {
                while ((!opStack.isEmpty()) && (opStack.top().toString().charAt(0) != '(')
                        && (Precedence(opStack.top().toString().charAt(0)) >= Precedence(term.charAt(0)))) {
                    postfix.enqueue(opStack.pop());
                }
                opStack.push(term);
            } else {
                postfix.enqueue(term);
            }
        }
        while (!opStack.isEmpty()) {
            postfix.enqueue(opStack.pop());
        }

        return postfix;

    }

    private static int Precedence(char term) {
        if (term == '+' || term == '-') {
            return 1;
        } else if (term == '*' || term == '/') {
            return 2;
        } else {
            throw new IllegalArgumentException("Invalid Precedence");
        }
    }

}
