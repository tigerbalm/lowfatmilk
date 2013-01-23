package com.dorandoran.infixtopostfix;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	List<String> postfixList;
	
	char[] stack;
	int stackIndex;
	
	public InfixToPostfix() {
		stack = new char[10];
		stackIndex = 0;
		
		postfixList = new ArrayList<String>();
	}
	
	public String toPostfix2(String infix) {
		StringBuilder out = new StringBuilder();
		Stack<Term> stack = new Stack<Term>();
		
		Expression expr = new Expression();
		expr.parse(infix);
		
		for (Term term : expr.getTermsInOrder()) {
			if (term instanceof Digit) {
				out.append(term).append(" ");
			} else {
				if (term instanceof Operator) {
					Operator curOp = (Operator)term;
					
					if (curOp.hasHigherPriorityThan(stack.peek())) {
						stack.push(term);
					} else {
						out.append(stack.pop()).append(" ");
						stack.push(term);
					}
				} else if (term instanceof LeftParen) {
					
				} else if (term instanceof RightParen) {
					
				}
			}
		}
		
		return buildString(out, stack);
	}
	
	private String buildString(StringBuilder out, Stack<Term> stack2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String toPostfix(String infix) {
		Stack<String> operators = new Stack<String>();
		
		StringBuilder postfix = new StringBuilder();
				
		for (int i = 0; i < infix.length(); ++ i) {
			char c = infix.charAt(i);
			
			if (Character.isWhitespace(c)) {
				continue;
			}
			
			if (Character.isDigit(c)) {
				if (postfix.length() > 0)
					postfix.append(" ");
				
				postfix.append(c);
				
				postfixList.add(String.valueOf(c));
			} else {
				stack[stackIndex ++] = c;
				
				operators.push(String.valueOf(c));
			}
		}
		
		while (stackIndex > 0) {
			if (stackIndex >= 1) {
				postfix.append(" ");
			}
				
			postfix.append(stack[--stackIndex]);
		}
		
		return postfix.toString();
	}
	
}
