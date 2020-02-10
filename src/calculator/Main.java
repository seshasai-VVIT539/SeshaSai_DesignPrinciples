package calculator;
import java.util.Scanner;
public class Main
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter expression to be evaluated (seperate operators and operands with spaces)");
		String expression=sc.nextLine();
		System.out.println(Calculator.evaluate(expression));
	}
}