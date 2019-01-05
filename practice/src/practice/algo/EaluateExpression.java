package practice.algo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class EaluateExpression {
	
	public static Double evaluatePostFix(String expr) {
		Double d = 0.0;
		Stack<Double> valStack = new Stack<>();
		for(int i=0; i<expr.length(); i++) {
			Character c = expr.charAt(i);
			if(c ==' ')
				continue;
			if(Character.isDigit(c)) {
				StringBuffer sb = new StringBuffer();
				while(Character.isDigit(expr.charAt(i)) && i < expr.length())
					sb.append(expr.charAt(i++));
				valStack.push(Double.parseDouble(sb.toString()));
				c = expr.charAt(i);
			} else if(isOperator(c)) {
				Double op2 = valStack.pop();
				Double op1 = valStack.pop();
				Double res = perform(op1,op2,c);
				valStack.push(res);
			}
		}
		return valStack.pop();
	}

	private static Double perform(Double op1, Double op2, Character operator) {
		switch (operator){
		case '*' : return op1*op2;
		case '/' : return op1/op2;
		case '%' : return op1%op2;
		case '^' : return Math.pow(op1, op2);
		case '+' : return op1+op2;
		case '-' : return op1-op2;
		}
		return null;
	}

	private static boolean isOperator(Character c) {
		String oprs = "+-*/%^";
		return oprs.indexOf(c) >= 0 ? true : false;
	}
	
	public static void main(String[] args) {
		String ex = ConvertInfixExpr.toPostFix("1-1");
		System.out.println(ex);
		double r = EaluateExpression.evaluatePostFix(ex);
		System.out.println(r);
		HashSet s = new HashSet();
		s.add(10);
	}

}
