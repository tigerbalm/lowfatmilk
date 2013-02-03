package com.dorandoran.infixtopostfix;

import java.util.ArrayList;
import java.util.Iterator;
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

	class MyString {
		StringBuilder string;
		
		public MyString() {
			string = new StringBuilder();			
		}
		
		public MyString append(CharSequence cs) {
			if (string.length() > 0) {
				string.append(" ");
			}
			
			string.append(cs);
			
			return this;
		}

		public MyString append(Object obj) {
			return append(obj.toString());
		}
		
		@Override
		public String toString() {
			return string.toString();
		}
	}
	
	public String toPostfix2(String infix) {
		MyString out = new MyString();
		Stack<Term> stack = new Stack<Term>();
		
		Expression expr = new Expression();
			
		expr.parse(infix);
		
		for (Term term : expr.getTermsInOrder()) {
			if (term instanceof Digit) {
				out.append(term);
			} else {
				if (term instanceof Operator) {
					Operator curOp = (Operator)term;
					
					// TODO: 
					// if empty push
					// else while stack is not empty and not "(",
					//	if top of stack is higher than op then pop and append top to buffer
					//	else break;
					// push op
					if (stack.isEmpty()) {
						stack.push(term);
					} else {										
						while (!(stack.isEmpty()) && !curOp.isHigherPriorityThan(stack.peek())) {
							out.append(stack.pop());
						}
						
						stack.push(term);
					}					
				} else if (term instanceof LeftParen) {
					stack.push(term);
				} else if (term instanceof RightParen) {
					while (!stack.empty()) {
						Term top = stack.pop();
						
						if (top instanceof LeftParen) {
							break;
						}
						
						out.append(top);
					}
				}
			}
		}
		
		return buildString(out, stack);
	}
	
	private String buildString(MyString out, Stack<Term> operators) {
		StringBuilder result = new StringBuilder(out.toString());
		
		while(!operators.empty()) {
			result.append(" ").append(operators.pop());
		}
		
		return result.toString();
	}
}
