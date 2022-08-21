import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class SamePrecedence {

    public static void main(String[] args) {
        SamePrecedence calculator=new SamePrecedence();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Math Expression with same precedence (examples: 1+2-4, 2*5/6*5): ");
        String expression = input.nextLine();
        System.out.print(calculator.evaluate(expression));
    }
    public double evaluate(String expression){
        // remove spaces from expression
        expression=expression.replaceAll(" ", "");
        // Error Handling-1 ... Check if the length is less than 3 characters
        if (expression.length() < 3) {
            throw new IllegalArgumentException("Error: Illegal Input Math Expression!");
        }
        double result;
        List<Character> opList=new ArrayList<>();
        List<String> sNumList=new ArrayList<>();
        List<Double> dNumList=new ArrayList<>();

        //save operators in list
        for(char c: expression.toCharArray())
            if (c == '+' || c == '-' || c == '*' || c == '/') opList.add(c);

        // check operators for precedence
        if((opList.contains('+')||opList.contains('-')) &&
                (opList.contains('*')||opList.contains('/')))
            throw new IllegalArgumentException("Error: different precedence!");

        // search for numbers in expression
        String[] str=expression.split("[-+*/]+");
        sNumList= Arrays.asList(str);
        for(String s: sNumList)
            dNumList.add(Double.parseDouble(s));
        result=dNumList.get(0);

        for(int index=0;index<opList.size();index++) {
            switch (opList.get(index)) {
                case '+' -> result = result + dNumList.get(index+1);
                case '-' -> result = result - dNumList.get(index+1);
                case '*' -> result = result * dNumList.get(index+1);
                case '/' -> result = result / dNumList.get(index+1);
            }
        }
        return result;
    }
}
