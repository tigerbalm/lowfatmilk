package com.dorandoran.infixtopostfix;

public class Digit implements Term {
	String value;
	
	public Digit(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
