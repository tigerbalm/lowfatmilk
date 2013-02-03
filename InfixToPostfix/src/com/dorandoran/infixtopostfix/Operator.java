package com.dorandoran.infixtopostfix;

import com.google.common.base.Objects;

public class Operator implements Term {
	String operator;
		
	public Operator(String op) {
		operator = op;
	}
	
	public boolean isHigherPriorityThan(Term term) {
		if (term instanceof LeftParen || term instanceof RightParen) {
			return true;
		}
		
		Operator otherOp = (Operator)term;
		
		if (equals(otherOp)) {
			return false;
		}
		
		return higherOperator(operator, otherOp.operator);
	}
	
	private boolean higherOperator(String op1, String op2) {
		if (isAddOp(op1)) {
			return false;
		} else if (isAddOp(op2)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isAddOp(String op1) {
		return op1.equals("+") || op1.equals("-");
	}

	@Override
	public String toString() {
		return operator;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(operator);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		Operator other = (Operator) obj;
		
		return Objects.equal(operator, other.operator);
	}
}
