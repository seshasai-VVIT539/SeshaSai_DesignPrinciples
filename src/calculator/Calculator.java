package calculator;
import java.util.Stack; 
public class Calculator { 
		public static int evaluate(String expr) 
		{ 
			char[] expression = expr.toCharArray(); 

			// Stack for numbers in the given expression 
			Stack<Integer> numbers = new Stack<Integer>(); 

			// Stack for Operators in the given expression
			Stack<Character> operator = new Stack<Character>();

			// some variables that are used in the working 
			int s,n1,n2,ans;
			
			
			for (int i = 0; i < expression.length; i++) 
			{ 
				// Current character is a whitespace, skip it 
				if (expression[i] == ' ') 
					continue; 

				// Current character is a number, push it to stack for numbers 
				if (expression[i] >= '0' && expression[i] <= '9') 
				{  
					s=0;
					while (i < expression.length && expression[i] >= '0' && expression[i] <= '9') 
						s=s*10+(expression[i++]-'0'); 
					numbers.push(s); 
				} 

				// Current character is an opening brace, push it to 'operator' 
				else if (expression[i] == '(') 
					operator.push(expression[i]); 

				// Closing brace encountered, solve entire brace 
				else if (expression[i] == ')') 
				{ 
					try
					{
						while (operator.peek() != '(') 
						{
							n2=numbers.pop();
							n1=numbers.pop();
							numbers.push(applyOp(operator.pop(), n1, n2)); 
						}
						operator.pop(); 
					}catch(UnsupportedOperationException E)
					{
						System.out.print("Exception raised \n Divide by zero \nreturned ");
						return 0;
					}catch(Exception e)
					{
						System.out.print("Error occured:shortage of operands or shortage of operators\n returned ");
						return 0;
					}
				} 

				// Current token is an operator. 
				else if (expression[i] == '+' || expression[i] == '-' || 
						expression[i] == '*' || expression[i] == '/') 
				{ 
					// While top of 'operator' has same or greater precedence to current 
					// token, which is an operator. Apply operator on top of 'operator' 
					// to top two elements in numbers stack 
					try
					{
						while (!operator.empty() && hasPrecedence(expression[i], operator.peek())) 
						{
							n2=numbers.pop();
							n1=numbers.pop();
							numbers.push(applyOp(operator.pop(), n1, n2)); 
						}
						// Push current token to 'operator'. 
						operator.push(expression[i]);
					}catch(UnsupportedOperationException E)
					{
						System.out.print("Exception raised \n Divide by zero \nreturned ");
						return 0;
					}catch(Exception e)
					{
						System.out.print("Error occured:shortage of operands or shortage of operators\n returned ");
						return 0;
					}
				}
				else
				{
					System.out.print("Error occured: operator not identified\n returned ");
					return 0;
				}
			} 
			try
			{
				// apply remaining operators  
				while (!operator.empty()) 
				{
					n2=numbers.pop();
					n1=numbers.pop();
					numbers.push(applyOp(operator.pop(), n1, n2)); 
				}
			}catch(UnsupportedOperationException E)
					{
						System.out.print("Exception raised \n Divide by zero \nreturned ");
						return 0;
					}catch(Exception e)
			{
				System.out.print("Error occured:shortage of operands or shortage of operators\nreturned ");
				return 0;
			}
			// Top of 'numbers' contains result, return it 
			ans= numbers.pop(); 
			if(!numbers.empty())
			{
				System.out.print("Invalid expression\n returned ");
				return 0;
			}
			return ans;
		} 

		// Returns true if 'op2' has higher or same precedence as 'op1', 
		// otherwise returns false. 
		public static boolean hasPrecedence(char op1, char op2) 
		{ 
			if (op2 == '(' || op2 == ')') 
				return false; 
			if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) 
				return false; 
			else
				return true; 
		} 

		// methods for performing basic arithmetic operations 
		public static int add(int a,int b)
		{
			return a+b;
		}
		
		public static int subtract(int a,int b)
		{
			return a-b;
		}
		
		public static int multiply(int a,int b)
		{
			return a*b;
		}
		
		public static int divide(int a,int b)
		{
			if(b==0)
			{
				throw new UnsupportedOperationException("Cannot divide by zero");
			}
			return a/b;
		}
		
		public static int applyOp(char op, int a, int b) 
		{ 
			switch (op) 
			{ 
			case '+': 
				return add(a,b); 
			case '-': 
				return subtract(a,b); 
			case '*': 
				return multiply(a,b); 
			case '/':  
				return divide(a,b); 
			} 
			return 0; 
		} 	
	}

