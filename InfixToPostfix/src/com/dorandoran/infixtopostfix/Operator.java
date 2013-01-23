package com.dorandoran.infixtopostfix;

public class Operator implements Term {
	String operator;
	
	public Operator(String op) {
		operator = op;
	}
	
	public boolean hasHigherPriorityThan(Term peek) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {
		return operator;
	}
}
