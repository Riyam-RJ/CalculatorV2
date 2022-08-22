import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class DifferentPrecedence {
    public static void main(String[] args) {
        DifferentPrecedence calculator=new DifferentPrecedence();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Math Expression with different precedence (examples: 1+2/4, 2-5/6*5): ");
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
        System.out.print(expression+"= ");
        double result;
        List<Character> opList=new ArrayList<>();
        List<String> sNumList=new ArrayList<>();
        List<Double> dNumList=new ArrayList<>();

        //save operators in list
        for(char c: expression.toCharArray())
            if (c == '+' || c == '-' || c == '*' || c == '/') opList.add(c);

        // search for numbers in expression
        String[] str=expression.split("[-+*/]+");
        sNumList= Arrays.asList(str);
        for(String s: sNumList)
            dNumList.add(Double.parseDouble(s));

        // step-1 iterate to evaluate '/'
        while(opList.contains('/')) {
            int index = opList.indexOf('/');
            double newValue = dNumList.get(index) / dNumList.get(index+1);
            dNumList.set(index, newValue);
            opList.remove(index);
            dNumList.remove(index+1);
        }

        // step-1 iterate to evaluate '*'
        while(opList.contains('*')) {
            int index = opList.indexOf('*');
            double newValue = dNumList.get(index) * dNumList.get(index+1);
            dNumList.set(index, newValue);
            opList.remove(index);
            dNumList.remove(index+1);
        }

        // step-3 evaluate for +,-
        result=dNumList.get(0);
        for(int i=0;i<opList.size();i++){
            if(opList.get(i)=='+')
                result = result + dNumList.get(i+1);
            else
                result = result - dNumList.get(i+1);
        }
        return result;
    }

}
