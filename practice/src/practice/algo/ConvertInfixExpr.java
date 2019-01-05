package practice.algo;

import java.util.Stack;

public class ConvertInfixExpr {
	
	public static String toPostFix(String expr) {
		Stack<Character> oprStack = new Stack<>();
		String res = "";
		for(int i=0; i<expr.length(); i++) {
			char c = expr.charAt(i);
			// If token is a operand
			if(c==' ')
				continue;
			if(Character.isLetterOrDigit(c))
				res += c;
			// If token is opening parentheses
			else if(isOpeningParentheses(c)) {
				oprStack.push(c);
			}
			// If token is closing parentheses
			else if(isClosingParentheses(c)) {
				while(!oprStack.isEmpty() && !isOpeningParentheses(oprStack.peek())){
					res += " "+oprStack.pop();
				}
				if(!oprStack.isEmpty() && !isOpeningParentheses(oprStack.peek()))
					return "Invalid Expression";
				else
					oprStack.pop();
			}
			// If token is an operator +-*/^ ...
			else {
				res += " ";
				// if the operator in the stack has higher precedence than the current operator 'c', first pop the operators and append to result and then push the current operator to stack
				// for ^ (exponent) operator, it has to right to left association, the extra condition honors that and pushes it on to stack instead of appending it to result
				while(!oprStack.isEmpty() && oprPrecedence(oprStack.peek()) >= oprPrecedence(c) && !(c=='^' && oprStack.peek() == '^')) {
					res += oprStack.pop()+" ";
				}
				oprStack.push(c);
			}
		}
		while(!oprStack.isEmpty())
			res = res+" "+oprStack.pop();
		return res;	
	}

	private static int oprPrecedence(Character opr) {
		switch (opr)
        {
        case '+':
        case '-':
            return 1;
      
        case '*':
        case '/':
        case '%':
            return 2;
      
        case '^':
            return 9999;
        }
        return -1;
	}

	private static boolean isClosingParentheses(char c) {
		if(c == ')' || c == '}' || c == ']')
			return true;
		return false;
	}

	private static boolean isOpeningParentheses(char c) {
		if(c == '(' || c == '{' || c == '[')
			return true;
		return false;
	}

	public static void main(String[] args) {
		String exp = "a+b*(c^d^e)^(f+g*h)-i";
		String exp1 = "22 * 33 + (5-2)^2";
		System.out.println(ConvertInfixExpr.toPostFix(exp1));
	}

}
